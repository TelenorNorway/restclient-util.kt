plugins {
	kotlin("jvm") version "1.9.21"
	id("sh.tnn") version "0.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.springframework.boot") version "3.2.0"
	`maven-publish`
}

group = "no.telenor.kt"

repositories.mavenCentral()

dependencies.compileOnly("org.springframework:spring-web")

kotlin.jvmToolchain(21)

tasks.named("bootJar") {
	enabled = false
}

publishing {
	repositories.github.actions()
	publications.create<MavenPublication>("maven") {
		from(components["kotlin"])
	}
}
