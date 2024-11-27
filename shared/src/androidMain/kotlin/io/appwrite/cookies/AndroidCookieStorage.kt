package io.appwrite.cookies

import android.content.Context
import android.os.Build
import io.appwrite.cookies.stores.SharedPreferencesCookieStore
import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.http.Cookie
import io.ktor.http.Url
import io.ktor.http.toURI
import java.net.HttpCookie

class AndroidCookieStorage(context: Context) : CookiesStorage {
    private val cookieStore = SharedPreferencesCookieStore(
        preferences = context.getSharedPreferences("cookies", Context.MODE_PRIVATE)
    )

    override suspend fun get(requestUrl: Url): List<Cookie> {
        return cookieStore.get(requestUrl.toURI()).map { it.toKtorCookie() }
    }

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        val httpCookie = cookie.toHttpCookie()
        cookieStore.add(requestUrl.toURI(), httpCookie)

        // Sync with WebKit
        android.webkit.CookieManager.getInstance().run {
            val hostUrl = "${if (cookie.secure) "https" else "http"}://${cookie.domain}"
            setCookie(hostUrl, httpCookie.toSetCookieString())
            flush()
        }
    }

    override fun close() {
        cookieStore.removeAll()
        android.webkit.CookieManager.getInstance().removeAll()
    }

    private fun Cookie.toHttpCookie(): HttpCookie = HttpCookie(name, value).apply {
        domain = this@toHttpCookie.domain
        path = this@toHttpCookie.path
        secure = this@toHttpCookie.secure
        maxAge = this@toHttpCookie.maxAge?.toLong() ?: -1L
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            isHttpOnly = this@toHttpCookie.httpOnly
        }
    }

    private fun HttpCookie.toKtorCookie(): Cookie = Cookie(
        name = name,
        value = value,
        domain = domain,
        path = path ?: "/",
        secure = secure,
        httpOnly = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) isHttpOnly else false,
        maxAge = maxAge.toInt()
    )
}