import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.16" apply false
	id("io.spring.dependency-management") version "1.1.3" apply false
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22" apply false
}

allprojects {
	group = "com.example"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			jvmTarget = "11"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

}

subprojects {
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.springframework.kafka:spring-kafka")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.springframework.kafka:spring-kafka-test")
	}
}
