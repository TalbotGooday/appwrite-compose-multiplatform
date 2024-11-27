package io.appwrite.models

import io.ktor.utils.io.core.Closeable
import kotlin.collections.Collection
import kotlin.reflect.KClass

data class RealtimeSubscription(
    private val close: () -> Unit
) : Closeable {
    override fun close() = close.invoke()
}

data class RealtimeCallback(
    val channels: Collection<String>,
    val payloadClass: KClass<*>,
    val callback: (RealtimeResponseEvent<*>) -> Unit
)

open class RealtimeResponse(
    val type: String,
    val data: Any
)

data class RealtimeResponseEvent<T>(
    val events: Collection<String>,
    val channels: Collection<String>,
    val timestamp: String,
    var payload: T
)

enum class RealtimeCode(val value: Int) {
    POLICY_VIOLATION(1008),
    NORMAL_CLOSURE(1000),
    UNKNOWN_ERROR(-1)
}