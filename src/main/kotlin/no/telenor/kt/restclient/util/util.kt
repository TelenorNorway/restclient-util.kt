// IntelliJ is on drugs
@file:Suppress("UNNECESSARY_NOT_NULL_ASSERTION")

package no.telenor.kt.restclient.util

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClient.RequestHeadersSpec
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.client.body
import org.springframework.web.util.UriBuilder
import java.net.URI
import java.util.function.Function

fun RestClient.RequestBodySpec.json(value: Any) = this
	.body(value)
	.contentType(MediaType.APPLICATION_JSON)!!

inline fun <reified T : Any> RequestHeadersSpec<*>.retrieveJson(): T? = this
	.accept(MediaType.APPLICATION_JSON)
	.retrieve()
	.body<T>()

fun RequestHeadersSpec<*>.retrieveNone() = this
	.accept(MediaType.APPLICATION_JSON)
	.retrieve()!!

inline fun <reified ResponseType : Any> RequestHeadersSpec<*>.retrieveEntity(): ResponseEntity<ResponseType> =
	try {
		val response = this.accept(MediaType.APPLICATION_JSON).retrieve()
		@Suppress("UNCHECKED_CAST")
		when (ResponseType::class) {
			Unit::class, Void::class -> response.toBodilessEntity() as ResponseEntity<ResponseType>
			else -> response.toEntity(ResponseType::class.java)
		}
	} catch (ex: RestClientResponseException) {
		val response = ResponseEntity.status(ex.statusCode).headers(ex.responseHeaders)
		when (ResponseType::class) {
			Unit::class, Void::class -> response.build()
			else -> response.body(ex.getResponseBodyAs(ResponseType::class.java))
		}
	}

fun RestClient.get(uri: String) = get().uri(uri)!!
fun RestClient.get(uri: URI) = get().uri(uri)!!
fun RestClient.get(uri: String, vararg uriVariables: Pair<String, Any>) = get().uri(uri, uriVariables.toMap())!!
fun RestClient.get(uri: String, vararg uriVariables: Any) = get().uri(uri, *uriVariables)!!
fun RestClient.get(uri: String, uriVariables: Map<String, *>) = get().uri(uri, uriVariables)!!
fun RestClient.get(uri: String, uriFunction: Function<UriBuilder, URI>) = get().uri(uri, uriFunction)!!
fun RestClient.get(uriFunction: Function<UriBuilder, URI>) = get().uri(uriFunction)!!

fun RestClient.head(uri: String) = head().uri(uri)!!
fun RestClient.head(uri: URI) = head().uri(uri)!!
fun RestClient.head(uri: String, vararg uriVariables: Pair<String, Any>) = head().uri(uri, uriVariables.toMap())!!
fun RestClient.head(uri: String, vararg uriVariables: Any) = head().uri(uri, *uriVariables)!!
fun RestClient.head(uri: String, uriVariables: Map<String, *>) = head().uri(uri, uriVariables)!!
fun RestClient.head(uri: String, uriFunction: Function<UriBuilder, URI>) = head().uri(uri, uriFunction)!!
fun RestClient.head(uriFunction: Function<UriBuilder, URI>) = head().uri(uriFunction)!!

fun RestClient.post(uri: String) = post().uri(uri)!!
fun RestClient.post(uri: URI) = post().uri(uri)!!
fun RestClient.post(uri: String, vararg uriVariables: Pair<String, Any>) = post().uri(uri, uriVariables.toMap())!!
fun RestClient.post(uri: String, vararg uriVariables: Any) = post().uri(uri, *uriVariables)!!
fun RestClient.post(uri: String, uriVariables: Map<String, *>) = post().uri(uri, uriVariables)!!
fun RestClient.post(uri: String, uriFunction: Function<UriBuilder, URI>) = post().uri(uri, uriFunction)!!
fun RestClient.post(uriFunction: Function<UriBuilder, URI>) = post().uri(uriFunction)!!

fun RestClient.put(uri: String) = put().uri(uri)!!
fun RestClient.put(uri: URI) = put().uri(uri)!!
fun RestClient.put(uri: String, vararg uriVariables: Pair<String, Any>) = put().uri(uri, uriVariables.toMap())!!
fun RestClient.put(uri: String, vararg uriVariables: Any) = put().uri(uri, *uriVariables)!!
fun RestClient.put(uri: String, uriVariables: Map<String, *>) = put().uri(uri, uriVariables)!!
fun RestClient.put(uri: String, uriFunction: Function<UriBuilder, URI>) = put().uri(uri, uriFunction)!!
fun RestClient.put(uriFunction: Function<UriBuilder, URI>) = put().uri(uriFunction)!!

fun RestClient.patch(uri: String) = patch().uri(uri)!!
fun RestClient.patch(uri: URI) = patch().uri(uri)!!
fun RestClient.patch(uri: String, vararg uriVariables: Pair<String, Any>) = patch().uri(uri, uriVariables.toMap())!!
fun RestClient.patch(uri: String, vararg uriVariables: Any) = patch().uri(uri, *uriVariables)!!
fun RestClient.patch(uri: String, uriVariables: Map<String, *>) = patch().uri(uri, uriVariables)!!
fun RestClient.patch(uri: String, uriFunction: Function<UriBuilder, URI>) = patch().uri(uri, uriFunction)!!
fun RestClient.patch(uriFunction: Function<UriBuilder, URI>) = patch().uri(uriFunction)!!

fun RestClient.delete(uri: String) = delete().uri(uri)!!
fun RestClient.delete(uri: URI) = delete().uri(uri)!!
fun RestClient.delete(uri: String, vararg uriVariables: Pair<String, Any>) = delete().uri(uri, uriVariables.toMap())!!
fun RestClient.delete(uri: String, vararg uriVariables: Any) = delete().uri(uri, *uriVariables)!!
fun RestClient.delete(uri: String, uriVariables: Map<String, *>) = delete().uri(uri, uriVariables)!!
fun RestClient.delete(uri: String, uriFunction: Function<UriBuilder, URI>) = delete().uri(uri, uriFunction)!!
fun RestClient.delete(uriFunction: Function<UriBuilder, URI>) = delete().uri(uriFunction)!!
