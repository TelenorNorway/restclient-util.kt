# RestClient Utils

_Utility functions for RestClient_

This module provides some utility functions for Kotlin when
it comes to working with Spring Boot's new RestClient.

## Gradle prerequisite

In your gradle file

_Follow [this guide](https://github.com/telenornorway/sh.tnn) on how to set up your environment for GitHub packages._

```kt
plugins {
	id("sh.tnn") version "0.2.0"
}
```

## Usage

<!-- @formatter:off -->
```kt
import no.telenor.kt.restclient.*

client.patch("https://jsonplaceholder.typicode.com/todos/{todoId}", todoId)
  .json(changes)
  .retrieveJson()
```
<!-- @formatter:on -->
