# RestClient Utils

_Utility functions for RestClient_

## Gradle prerequisite

In your gradle file

_Follow [this guide](https://github.com/testersen/no.ghpkg) on how to set up your environment for GitHub packages._

```kt
plugins {
	id("no.ghpkg") version "0.3.3"
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
