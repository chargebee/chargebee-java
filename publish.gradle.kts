import java.security.MessageDigest

// Publishing and deployment configuration
apply(plugin = "maven-publish")
apply(plugin = "signing")

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            
            pom {
                name.set(project.name)
                description.set(project.description)
                url.set(project.extra["projectUrl"] as String)
                
                licenses {
                    license {
                        name.set(project.extra["projectLicense"] as String)
                        url.set(project.extra["projectLicenseUrl"] as String)
                    }
                }
                
                developers {
                    @Suppress("UNCHECKED_CAST")
                    val teamMembers = project.extra["teamMembers"] as List<*>
                    teamMembers.forEach { member ->
                        val devData = member as? Map<*, *>
                        if (devData != null) {
                            developer {
                                id.set(devData["id"]?.toString() ?: "")
                                name.set(devData["name"]?.toString() ?: "")
                                email.set(devData["email"]?.toString() ?: "")
                                timezone.set(devData["timezone"]?.toString() ?: "+5:30")
                            }
                        }
                    }
                }
                
                scm {
                    connection.set("scm:git:git://github.com/chargebee/chargebee-java.git")
                    developerConnection.set("scm:git:ssh://github.com:chargebee/chargebee-java.git")
                    url.set(project.extra["projectUrl"] as String)
                }
            }
        }
    }
}

configure<SigningExtension> {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(the<PublishingExtension>().publications["maven"])
}

// Generate checksums for artifacts
tasks.register("generateChecksums") {
    dependsOn("jar", "sourcesJar", "javadocJar", "generatePomFileForMavenPublication")
    
    doLast {
        val libsDir = layout.buildDirectory.dir("libs").get().asFile
        val pubDir = layout.buildDirectory.dir("publications/maven").get().asFile
        val artifactId = project.name
        val ver = project.version.toString()
        
        fun generateChecksum(file: File, algorithm: String) {
            val digest = MessageDigest.getInstance(algorithm)
            file.inputStream().use { input ->
                val buffer = ByteArray(8192)
                var read = input.read(buffer)
                while (read > 0) {
                    digest.update(buffer, 0, read)
                    read = input.read(buffer)
                }
            }
            val checksum = digest.digest().joinToString("") { byte: Byte -> "%02x".format(byte) }
            File(file.parentFile, "${file.name}.${algorithm.lowercase()}").writeText(checksum)
        }
        
        // Generate checksums for jars
        listOf(
            File(libsDir, "${artifactId}-${ver}.jar"),
            File(libsDir, "${artifactId}-${ver}-sources.jar"),
            File(libsDir, "${artifactId}-${ver}-javadoc.jar")
        ).forEach { file: File ->
            if (file.exists()) {
                generateChecksum(file, "MD5")
                generateChecksum(file, "SHA1")
            }
        }
        
        // Generate checksums for POM
        val pomFile = File(pubDir, "pom-default.xml")
        if (pomFile.exists()) {
            generateChecksum(pomFile, "MD5")
            generateChecksum(pomFile, "SHA1")
        }
    }
}

// Create bundle for Central Portal
tasks.register<Zip>("createCentralPortalBundle") {
    group = "publishing"
    description = "Creates a bundle (zip) for uploading to Sonatype Central Portal"
    
    dependsOn("generateChecksums", "signMavenPublication")
    
    val groupPath = project.group.toString().replace('.', '/')
    val artifactId = project.name
    val ver = project.version.toString()
    val targetPath = "${groupPath}/${artifactId}/${ver}/"
    
    // Include main jar, signature, and checksums
    from(layout.buildDirectory.dir("libs")) {
        include("${artifactId}-${ver}.jar")
        include("${artifactId}-${ver}.jar.asc")
        include("${artifactId}-${ver}.jar.md5")
        include("${artifactId}-${ver}.jar.sha1")
        into(targetPath)
    }
    
    // Include sources jar, signature, and checksums
    from(layout.buildDirectory.dir("libs")) {
        include("${artifactId}-${ver}-sources.jar")
        include("${artifactId}-${ver}-sources.jar.asc")
        include("${artifactId}-${ver}-sources.jar.md5")
        include("${artifactId}-${ver}-sources.jar.sha1")
        into(targetPath)
    }
    
    // Include javadoc jar, signature, and checksums
    from(layout.buildDirectory.dir("libs")) {
        include("${artifactId}-${ver}-javadoc.jar")
        include("${artifactId}-${ver}-javadoc.jar.asc")
        include("${artifactId}-${ver}-javadoc.jar.md5")
        include("${artifactId}-${ver}-javadoc.jar.sha1")
        into(targetPath)
    }
    
    // Include POM, signature, and checksums
    from(layout.buildDirectory.dir("publications/maven")) {
        include("pom-default.xml")
        include("pom-default.xml.asc")
        include("pom-default.xml.md5")
        include("pom-default.xml.sha1")
        into(targetPath)
        rename("pom-default.xml", "${artifactId}-${ver}.pom")
        rename("pom-default.xml.asc", "${artifactId}-${ver}.pom.asc")
        rename("pom-default.xml.md5", "${artifactId}-${ver}.pom.md5")
        rename("pom-default.xml.sha1", "${artifactId}-${ver}.pom.sha1")
    }
    
    archiveBaseName.set("${artifactId}-${ver}")
    archiveVersion.set("")
    archiveClassifier.set("bundle")
    destinationDirectory.set(layout.buildDirectory.dir("distributions"))
}

// Upload bundle to Central Portal
tasks.register<Exec>("publishToCentralPortal") {
    group = "publishing"
    description = "Uploads the bundle to Sonatype Central Portal"
    
    dependsOn("createCentralPortalBundle")
    
    val username = project.findProperty("sonatypeUsername") as String? ?: System.getenv("SONATYPE_USERNAME")
    val password = project.findProperty("sonatypePassword") as String? ?: System.getenv("SONATYPE_PASSWORD")
    val ver = project.version.toString()
    val artifactId = project.name
    val bundleFile = layout.buildDirectory.file("distributions/${artifactId}-${ver}-bundle.zip").get().asFile
    
    commandLine(
        "curl", "-s", "-w", "\nHTTP Status: %{http_code}\n",
        "-X", "POST",
        "-H", "accept: application/json",
        "-H", "Content-Type: multipart/form-data",
        "-F", "bundle=@${bundleFile.absolutePath}",
        "-u", "${username}:${password}",
        "https://central.sonatype.com/api/v1/publisher/upload?name=${artifactId}-${ver}&publishingType=USER_MANAGED"
    )
}

