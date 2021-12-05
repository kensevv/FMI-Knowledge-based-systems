import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0-M1"
    kotlin("plugin.spring") version "1.6.0-M1"
    kotlin("plugin.serialization") version "1.6.0-M1"
}

group = "com.fmi"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.jooq:jooq:3.15.3")
    implementation("org.jooq:jooq-kotlin:3.15.3")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("org.liquibase:liquibase-core")
    implementation(project(":jooq-db"))
    implementation(project(":frontend"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("com.oracle.database.jdbc:ojdbc8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    developmentOnly("org.springframework.boot:spring-boot-devtools:2.5.5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("org.apache.poi:poi:5.1.0")
    implementation("org.apache.poi:poi-ooxml:5.1.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val processResources by tasks.existing {
    dependsOn(copyFrontend)
}

val bootJar by tasks.existing(Jar::class) {
    this.archiveBaseName.set("new-plagiarism")
    this.archiveVersion.set("")
}

val copyFrontend by tasks.registering(Copy::class) {
    dependsOn(":frontend:buildFrontend")
    val inputDir = File("${rootDir}/frontend/dist")
    val outputDir = File("${rootDir}/backend/src/main/resources/static")
    outputDir.apply {
        if (this.exists()) {
            deleteRecursively()
        }
        mkdir()
    }
    from(inputDir)
    into(outputDir)
}
