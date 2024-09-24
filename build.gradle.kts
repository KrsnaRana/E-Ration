plugins {
    id("java")
}

group = "org.harsh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    runtimeOnly("mysql:mysql-connector-java:8.0.+")
}

tasks.test {
    useJUnitPlatform()
}