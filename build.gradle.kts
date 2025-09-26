plugins {
    `java-library`
    `maven-publish`
    signing
    id("org.owasp.dependencycheck") version "9.2.0"
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

group = "com.chargebee"
version = "4.0.0-beta.1"
description = "Next-gen Java client library for ChargeBee API"

// Project metadata
val projectUrl = "https://github.com/chargebee/chargebee-java"
val projectLicense = "MIT License"
val projectLicenseUrl = "https://opensource.org/licenses/MIT"

// Team details
data class Developer(val id: String, val name: String, val email: String, val timezone: String = "+5:30")

val teamMembers = listOf(
    Developer("dx", "DX Team", "dx@chargebee.com")
)

extra["projectUrl"] = projectUrl
extra["projectLicense"] = projectLicense  
extra["projectLicenseUrl"] = projectLicenseUrl
extra["teamMembers"] = teamMembers

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.13.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:5.19.0")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

// Relax javadoc strictness for beta builds
tasks.withType<Javadoc> {
    // Disable doclint to avoid failing on minor doc issues
    (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
    isFailOnError = false
}

tasks.test {
    useJUnitPlatform()
}

// OWASP Dependency Check configuration
dependencyCheck {
    format = org.owasp.dependencycheck.reporting.ReportGenerator.Format.ALL.toString()
    analyzedTypes = listOf("jar")
    suppressionFile = "owasp-suppression.xml"
    failBuildOnCVSS = 7.0f
}

// Publishing configuration
publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            
            pom {
                name.set(project.name)
                description.set(project.description)
                url.set(extra["projectUrl"] as String)
                
                licenses {
                    license {
                        name.set(extra["projectLicense"] as String)
                        url.set(extra["projectLicenseUrl"] as String)
                    }
                }
                
                developers {
                    @Suppress("UNCHECKED_CAST")
                    val developers = extra["teamMembers"] as List<Developer>
                    developers.forEach { dev ->
                        developer {
                            id.set(dev.id)
                            name.set(dev.name)
                            email.set(dev.email)
                            timezone.set(dev.timezone)
                        }
                    }
                }
                
                scm {
                    connection.set("scm:git:git://github.com/chargebee/chargebee-java.git")
                    developerConnection.set("scm:git:ssh://github.com:chargebee/chargebee-java.git")
                    url.set(extra["projectUrl"] as String)
                }
            }
        }
    }
}

// Signing configuration
signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["maven"])
}

// Nexus publishing configuration
nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}
