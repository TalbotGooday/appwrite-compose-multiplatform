package io.appwrite

import io.appwrite.cookies.IosCookieStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import platform.Foundation.NSBundle
import platform.UIKit.UIDevice

actual class Client(
    endpoint: String = "https://cloud.appwrite.io/v1",
    endpointRealtime: String? = null,
    selfSigned: Boolean = false,
) : BaseClient<Client>(endpoint, endpointRealtime) {
    actual override val coroutineContext = Job() + Dispatchers.Default

    private val appVersion: String by lazy {
        NSBundle.mainBundle.infoDictionary?.get("CFBundleShortVersionString") as? String ?: ""
    }

    private val bundleId: String by lazy {
        NSBundle.mainBundle.bundleIdentifier ?: ""
    }

    val iosCookieStorage = IosCookieStorage()

    init {
        httpClient = createHttpClient(selfSigned, iosCookieStorage)
        headers = mutableMapOf(
            "content-type" to "application/json",
            "x-sdk-name" to "KMP",
            "x-sdk-platform" to "",
            "x-sdk-language" to "kmp",
            "x-sdk-version" to "0.0.0-SNAPSHOT",
            "x-appwrite-response-format" to "1.6.0"
          )
    }

    actual fun setSelfSigned(value: Boolean): Client {
        httpClient = createHttpClient(value, iosCookieStorage)
        return this
    }
}
