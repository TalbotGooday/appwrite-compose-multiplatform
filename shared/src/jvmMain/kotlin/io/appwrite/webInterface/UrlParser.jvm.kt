package io.appwrite.webInterface

import java.net.URI

actual class UrlParser {
    actual fun parse(url: String): ParsedUrl = JvmParsedUrl(URI(url))

    actual fun getQueryParameter(url: String, name: String): String? {
        val uri = URI(url)
        val query = uri.query ?: return null
        return query.split('&')
            .map { it.split('=', limit = 2) }
            .find { it[0] == name }
            ?.getOrNull(1)
    }

    actual fun getHost(url: String): String =
        URI(url).host ?: throw IllegalArgumentException("Invalid URL")
}
