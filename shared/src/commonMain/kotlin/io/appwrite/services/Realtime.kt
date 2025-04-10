package io.appwrite.services

import io.appwrite.Client
import io.appwrite.Service
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.forEachAsync
import io.appwrite.extensions.fromJson
import io.appwrite.extensions.getSerializer
import io.appwrite.extensions.json
import io.appwrite.extensions.jsonCast
import io.appwrite.extensions.toJson
import io.appwrite.models.RealtimeCallback
import io.appwrite.models.RealtimeCode
import io.appwrite.models.RealtimeResponse
import io.appwrite.models.RealtimeResponseEvent
import io.appwrite.models.RealtimeSubscription
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.KSerializer
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

class Realtime(client: Client) : Service(client), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private companion object {
        private const val TYPE_ERROR = "error"
        private const val TYPE_EVENT = "event"
        private const val DEBOUNCE_MILLIS = 1L

        private var webSocketSession: DefaultClientWebSocketSession? = null
        private var activeChannels = mutableSetOf<String>()
        private var activeSubscriptions = mutableMapOf<Int, RealtimeCallback>()

        private var subCallDepth = 0
        private var reconnectAttempts = 0
        private var subscriptionsCounter = 0
        private var reconnect = true
    }

    private fun createSocket() {
        launch(Dispatchers.IO) {
            try {

                if (activeChannels.isEmpty()) {
                    reconnect = false
                    closeSocket()
                    return@launch
                }

                val queryParams = buildString {
                    append("project=${client.config["project"]}")
                    activeChannels.forEach {
                        append("&channels[]=$it")
                    }
                }

                if (webSocketSession != null) {
                    reconnect = false
                    closeSocket()
                }

                webSocketSession = client.httpClient.webSocketSession {
                    url("${client.endpointRealtime}/realtime?$queryParams")
                }

                reconnectAttempts = 0  // Reset attempts on successful connection
                webSocketSession?.let { session ->
                    for (frame in session.incoming) {
                        when (frame) {
                            is Frame.Text -> handleMessage(frame.readText())
                            else -> {}
                        }
                    }
                }

            } catch (e: Exception) {
                handleFailure(e)
            } finally {
                handleClosing(RealtimeCode.NORMAL_CLOSURE.value, "Connection closed")
            }
        }
    }

    @Throws(Throwable::class)
    suspend fun closeSocket() {
        webSocketSession?.close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, ""))
        webSocketSession = null
    }

    private fun getTimeout() = when {
        reconnectAttempts < 5 -> 1000L
        reconnectAttempts < 15 -> 5000L
        reconnectAttempts < 100 -> 10000L
        else -> 60000L
    }

    fun subscribe(
        channels: List<String>,
        callback: (RealtimeResponseEvent<Any>) -> Unit,
    ) = subscribe(
        channels = channels,
        Any::class,
        callback = callback
    )

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> subscribe(
        channels: List<String>,
        payloadType: KClass<T>,
        payloadSerializer: KSerializer<T>? = null,
        callback: (RealtimeResponseEvent<T>) -> Unit,
    ): RealtimeSubscription {
        val counter = subscriptionsCounter++

        activeChannels.addAll(channels)

        val actualSerializer = payloadSerializer ?: getSerializer(payloadType)

        activeSubscriptions[counter] = RealtimeCallback(
            channels.toList(),
            payloadType,
            actualSerializer,
            callback as (RealtimeResponseEvent<*>) -> Unit
        )

        launch {
            subCallDepth++
            delay(DEBOUNCE_MILLIS)
            if (subCallDepth == 1) {
                createSocket()
            }
            subCallDepth--
        }

        return RealtimeSubscription {
            activeSubscriptions.remove(counter)
            cleanUp(channels)
        }
    }

    private fun cleanUp(channels: List<String>) {
        activeChannels.removeAll { channel ->
            if (!channels.contains(channel)) {
                return@removeAll false
            }
            activeSubscriptions.values.none { callback ->
                callback.channels.contains(channel)
            }
        }
    }

    @Throws(Throwable::class)
    suspend fun handleMessage(text: String) {
        val message = text.fromJson(RealtimeResponse::class)
        when (message.type) {
            TYPE_ERROR -> handleResponseError(message)
            TYPE_EVENT -> handleResponseEvent(message)
        }
    }

    private fun handleResponseError(message: RealtimeResponse) {
        throw message.data.jsonCast<AppwriteException>()
    }

    @Throws(Throwable::class)
    suspend fun handleResponseEvent(message: RealtimeResponse) {
        val mapSerializer = getSerializer(Map::class)
        val event = json.decodeFromString(
            RealtimeResponseEvent.serializer(mapSerializer),
            message.data.toJson()
        )
        if (event.channels.isEmpty()) {
            return
        }
        if (!event.channels.any { activeChannels.contains(it) }) {
            return
        }
        activeSubscriptions.values.forEachAsync { subscription ->
            if (event.channels.any { subscription.channels.contains(it) }) {
                val payloadSerializer =
                    subscription.payloadSerializer

                val eventWithPayloadClass = json.decodeFromString(
                    RealtimeResponseEvent.serializer(payloadSerializer),
                    message.data.toJson()
                )
                subscription.callback(eventWithPayloadClass)
            }
        }
    }

    @Throws(Throwable::class)
    suspend fun handleClosing(code: Int, reason: String) {
        if (!reconnect || code == RealtimeCode.POLICY_VIOLATION.value) {
            reconnect = true
            return
        }

        val timeout = getTimeout()
        println("Realtime disconnected. Re-connecting in ${timeout / 1000} seconds.")

        delay(timeout)
        reconnectAttempts++
        createSocket()
    }

    private fun handleFailure(error: Throwable) {
        error.printStackTrace()
        launch {
            delay(getTimeout())
            reconnectAttempts++
            createSocket()
        }
    }
}
