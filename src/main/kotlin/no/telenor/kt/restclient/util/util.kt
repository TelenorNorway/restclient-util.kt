package no.telenor.kt.restclient.util

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClient.RequestBodySpec
import org.springframework.web.client.RestClient.RequestHeadersSpec
import org.springframework.web.client.RestClient.RequestHeadersUriSpec
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.client.body
import org.springframework.web.util.UriBuilder
import java.net.URI

// --- request body utility---

@Suppress("UNNECESSARY_NOT_NULL_ASSERTION")
fun RequestBodySpec.json(value: Any) = this
	.body(value)
	.contentType(MediaType.APPLICATION_JSON)!!

fun RequestBodySpec.header(header: String, value: Any) : RequestBodySpec = this
	.header(header, value.toString())

fun RequestBodySpec.headers(vararg headersWithValue: Pair<String, Any>) : RequestBodySpec = this
	.headers { httpHeaders -> headersWithValue.forEach { httpHeaders.add(it.first, it.second.toString()) }}

// --- response body utility ---

inline fun <reified T : Any> RequestHeadersSpec<*>.retrieveJsonOrNull(): T? = this
	.accept(MediaType.APPLICATION_JSON)
	.retrieve()
	.body<T>()

inline fun <reified T : Any> RequestHeadersSpec<*>.retrieveJson(): T = retrieveJsonOrNull()!!

fun RequestHeadersSpec<*>.retrieveNone() {
	this.retrieve()
}

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

// --- url builder ---

@Target(AnnotationTarget.TYPE)
@DslMarker
annotation class UrlBuilderDsl

typealias UrlBuilder = (@UrlBuilderDsl UriBuilder).() -> Unit

@Suppress("UNNECESSARY_NOT_NULL_ASSERTION")
private fun <S : RequestHeadersSpec<S>> RequestHeadersUriSpec<S>.url(uri: URI) =
	this.uri(uri)!!

@Suppress("UNNECESSARY_NOT_NULL_ASSERTION")
private fun <S : RequestHeadersSpec<S>> RequestHeadersUriSpec<S>.url(builder: UrlBuilder, url: String) =
	this.uri(url) { it.also(builder).build() }!!

@Suppress("UNNECESSARY_NOT_NULL_ASSERTION")
private fun <S : RequestHeadersSpec<S>> RequestHeadersUriSpec<S>.url(
	builder: UrlBuilder,
	url: String,
	vars: Array<out Pair<String, Any>>
) = this.uri(url) { it.also(builder).build(vars.toMap()) }!!

@Suppress("UNNECESSARY_NOT_NULL_ASSERTION")
private fun <S : RequestHeadersSpec<S>> RequestHeadersUriSpec<S>.url(
	builder: UrlBuilder,
	url: String,
	vars: Array<out Any>
) = this.uri(url) { it.also(builder).build(*vars) }!!

@Suppress("UNNECESSARY_NOT_NULL_ASSERTION")
private fun <S : RequestHeadersSpec<S>> RequestHeadersUriSpec<S>.url(
	builder: UrlBuilder,
	url: String,
	vars: Map<String, *>
) = this.uri(url) { it.also(builder).build(vars) }!!

// --- get ---

fun RestClient.get(uri: URI) =
	get().url(uri)!!

fun RestClient.get(uri: String, builder: UrlBuilder = {}) =
	get().url(builder, uri)!!

fun RestClient.get(uri: String, vararg uriVariables: Pair<String, Any>, builder: UrlBuilder = {}) =
	get().url(builder, uri, uriVariables)!!

fun RestClient.get(uri: String, vararg uriVariables: Any, builder: UrlBuilder = {}) =
	get().url(builder, uri, uriVariables)!!

fun RestClient.get(uri: String, uriVariables: Map<String, *>, builder: UrlBuilder = {}) =
	get().url(builder, uri, uriVariables)!!

// --- head ---

fun RestClient.head(uri: URI) =
	head().url(uri)!!

fun RestClient.head(uri: String, builder: UrlBuilder = {}) =
	head().url(builder, uri)!!

fun RestClient.head(uri: String, vararg uriVariables: Pair<String, Any>, builder: UrlBuilder = {}) =
	head().url(builder, uri, uriVariables)!!

fun RestClient.head(uri: String, vararg uriVariables: Any, builder: UrlBuilder = {}) =
	head().url(builder, uri, uriVariables)!!

fun RestClient.head(uri: String, uriVariables: Map<String, *>, builder: UrlBuilder = {}) =
	head().url(builder, uri, uriVariables)!!

// --- post ---

fun RestClient.post(uri: URI) =
	post().url(uri)!!

fun RestClient.post(uri: String, builder: UrlBuilder = {}) =
	post().url(builder, uri)!!

fun RestClient.post(uri: String, vararg uriVariables: Pair<String, Any>, builder: UrlBuilder = {}) =
	post().url(builder, uri, uriVariables)!!

fun RestClient.post(uri: String, vararg uriVariables: Any, builder: UrlBuilder = {}) =
	post().url(builder, uri, uriVariables)!!

fun RestClient.post(uri: String, uriVariables: Map<String, *>, builder: UrlBuilder = {}) =
	post().url(builder, uri, uriVariables)!!

// --- put ---

fun RestClient.put(uri: URI) =
	put().url(uri)!!

fun RestClient.put(uri: String, builder: UrlBuilder = {}) =
	put().url(builder, uri)!!

fun RestClient.put(uri: String, vararg uriVariables: Pair<String, Any>, builder: UrlBuilder = {}) =
	put().url(builder, uri, uriVariables)!!

fun RestClient.put(uri: String, vararg uriVariables: Any, builder: UrlBuilder = {}) =
	put().url(builder, uri, uriVariables)!!

fun RestClient.put(uri: String, uriVariables: Map<String, *>, builder: UrlBuilder = {}) =
	put().url(builder, uri, uriVariables)!!

// --- patch ---

fun RestClient.patch(uri: URI) =
	patch().url(uri)!!

fun RestClient.patch(uri: String, builder: UrlBuilder = {}) =
	patch().url(builder, uri)!!

fun RestClient.patch(uri: String, vararg uriVariables: Pair<String, Any>, builder: UrlBuilder = {}) =
	patch().url(builder, uri, uriVariables)!!

fun RestClient.patch(uri: String, vararg uriVariables: Any, builder: UrlBuilder = {}) =
	patch().url(builder, uri, uriVariables)!!

fun RestClient.patch(uri: String, uriVariables: Map<String, *>, builder: UrlBuilder = {}) =
	patch().url(builder, uri, uriVariables)!!

// --- delete ---

fun RestClient.delete(uri: URI) =
	delete().url(uri)!!

fun RestClient.delete(uri: String, builder: UrlBuilder = {}) =
	delete().url(builder, uri)!!

fun RestClient.delete(uri: String, vararg uriVariables: Pair<String, Any>, builder: UrlBuilder = {}) =
	delete().url(builder, uri, uriVariables)!!

fun RestClient.delete(uri: String, vararg uriVariables: Any, builder: UrlBuilder = {}) =
	delete().url(builder, uri, uriVariables)!!

fun RestClient.delete(uri: String, uriVariables: Map<String, *>, builder: UrlBuilder = {}) =
	delete().url(builder, uri, uriVariables)!!
