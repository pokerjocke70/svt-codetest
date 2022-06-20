import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "org.sundqvist.svt"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

configurations.all {
	exclude("org.apache.groovy")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.github.microutils:kotlin-logging-jvm:2.1.23")
	implementation("org.springframework.retry:spring-retry:1.3.3")
	implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.kotest:kotest-assertions-core-jvm:5.3.0")
	testImplementation("io.kotest:kotest-runner-junit5:5.3.0")
	testImplementation("io.kotest.extensions:kotest-extensions-wiremock:1.0.3")
	testImplementation("io.mockk:mockk:1.12.4")
	testImplementation("io.rest-assured:rest-assured:5.1.1")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
