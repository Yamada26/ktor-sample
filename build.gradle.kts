plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenCentral()
}

dependencies {
    // ---- Ktor ----
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.jackson)
    implementation("io.ktor:ktor-server-status-pages-jvm:3.3.0")

    // ---- Logging ----
    implementation("io.github.oshai:kotlin-logging-jvm:6.0.9")
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.call.id)

    // ---- Database (Exposed + JDBC) ----
    implementation("org.jetbrains.exposed:exposed-core:0.61.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.61.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.61.0")
    implementation("org.postgresql:postgresql:42.7.8")
    implementation("org.xerial:sqlite-jdbc:3.50.3.0")

    // ---- Testing ----
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.ktor.server.test.host)

    // ✅ Kotest
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("io.kotest:kotest-property:5.9.1")

    // ✅ Mock
    testImplementation("io.mockk:mockk:1.14.6")
}

tasks.test {
    useJUnitPlatform()
}
