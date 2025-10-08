plugins {
    `java-library`
    `maven-publish`
    signing
    id("org.owasp.dependencycheck") version "9.2.0"
}

group = "com.chargebee"
version = "4.0.0-beta.1"
description = "Next-gen Java client library for ChargeBee API"

// Project metadata
val projectUrl = "https://github.com/chargebee/chargebee-java"
val projectLicense = "MIT License"
val projectLicenseUrl = "https://opensource.org/licenses/MIT"

// Team details (stored as maps for use in publish.gradle.kts)
val teamMembers = listOf(
    mapOf(
        "id" to "dx",
        "name" to "DX Team",
        "email" to "dx@chargebee.com",
        "timezone" to "+5:30"
    )
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

// Apply publishing and deployment configuration
apply(from = "publish.gradle.kts")
