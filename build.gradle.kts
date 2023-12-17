plugins {
    id("java")
}

group = "fr.xanode.imtparischain"
version = "1.0"

repositories {
    mavenCentral()
    maven { url = uri("https://artifacts.consensys.net/public/maven/maven") }
    maven { url = uri("https://dl.cloudsmith.io/public/libp2p/jvm-libp2p/maven/") }
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://repo.maven.apache.org/maven2") }
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.28")
    implementation("org.bouncycastle:bcprov-jdk18on:1.77")
    implementation("org.slf4j:slf4j-reload4j:2.0.9")
    implementation("io.libp2p:jvm-libp2p:1.0.1-RELEASE")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}