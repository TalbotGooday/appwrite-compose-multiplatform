package io.appwrite

import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import kotlin.coroutines.resume

actual class WebAuthComponent {
    companion object {
        private var suspended = false
        private val callbacks = mutableMapOf<String, ((Result<String>) -> Unit)?>()
    }

    actual suspend fun authenticate(
        url: String,
        callbackUrlScheme: String,
        onComplete: ((Result<String>) -> Unit)?
    ) = suspendCancellableCoroutine { continuation ->
        callbacks[callbackUrlScheme] = onComplete

        val nsUrl = NSURL.URLWithString(url) ?: run {
            cleanUp()
            continuation.resume(Unit)
            return@suspendCancellableCoroutine
        }

        UIApplication.sharedApplication.openURL(nsUrl)
        suspended = true

        continuation.invokeOnCancellation {
            cleanUp()
        }
    }

    actual fun onCallback(scheme: String, url: String) {
        callbacks.remove(scheme)?.invoke(Result.success(url))
        suspended = false
    }

    private fun cleanUp() {
        callbacks.forEach { (_, callback) ->
            callback?.invoke(Result.failure(IllegalStateException("User cancelled login")))
        }
        callbacks.clear()
        suspended = false
    }
}