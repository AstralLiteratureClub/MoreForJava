plugins {
    id("java")
    id("maven-publish")
}

allprojects {
    group = "bet.astral"
    version = "1.0.2"
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.0.0")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "bet.astral"
            artifactId = "more4j"

            from(components["java"])
        }
    }
}