package io.appwrite.cookies

import io.ktor.http.Cookie
import kotlinx.serialization.Serializable

@Serializable
data class SerializableCookie(
    val name: String,
    val value: String,
    val domain: String? = null,
    val path: String = "/",
    val maxAge: Int? = null,
    val secure: Boolean = false,
    val httpOnly: Boolean = false,
    val expiration: Long = System.currentTimeMillis() + (maxAge?.times(1000L) ?: 2592000000L)
) {
    fun toCookie(): Cookie = Cookie(
        name = name,
        value = value,
        domain = domain,
        path = path,
        maxAge = maxAge,
        secure = secure,
        httpOnly = httpOnly
    )

    companion object {
        fun fromCookie(cookie: Cookie): SerializableCookie = SerializableCookie(
            name = cookie.name,
            value = cookie.value,
            domain = cookie.domain,
            path = cookie.path ?: "/",
            maxAge = cookie.maxAge,
            secure = cookie.secure,
            httpOnly = cookie.httpOnly
        )
    }
}
