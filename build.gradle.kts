plugins {
	kotlin("jvm") version "1.9.21"
	id("no.ghpkg") version "0.3.3"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.springframework.boot") version "3.2.0"
	`maven-publish`
}

group = "no.telenor.kt"
version = versioning.environment()

repositories.mavenCentral()

dependencies.compileOnly("org.springframework:spring-web")

kotlin.jvmToolchain(17)

tasks.named("bootJar") {
	enabled = false
}

publishing {
	repositories.github.actions()
	publications.create<MavenPublication>("maven") {
		from(components["kotlin"])
	}
}
