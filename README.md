# RestClient Utils

_Utility functions for RestClient_

This module provides some utility functions for Kotlin when
it comes to working with Spring Boot's new RestClient.

## Gradle prerequisite

In your gradle file

_Follow [this guide](https://github.com/telenornorway/sh.tnn) on how to set up your environment for GitHub packages._

<!-- @formatter:off -->
```kt
// build.gradle.kts
plugins {
    id("sh.tnn") version "0.2.0"
}

repositories {
    mavenCentral()
    // provided by the sh.tnn plugin
    telenor.public()
}

dependencies {
    implementation("no.telenor.kt:restclient-utils:0.3.0")
}
```
<!-- @formatter:on -->

## Usage

The source code itself is the best documentation, if you are wondering what this library does,
just take a look at the source code to find everything you need.

<!-- @formatter:off -->
```kt
import no.telenor.kt.restclient.*

client.patch("https://jsonplaceholder.typicode.com/todos/{todoId}", todoId)
    .json(changes)
    .retrieveJson()
```
<!-- @formatter:on -->
