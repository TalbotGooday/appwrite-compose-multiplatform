package io.appwrite.models

import io.appwrite.extensions.jsonCast
import io.appwrite.extensions.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

/**
 * Subscriber
 */
@Serializable
data class Subscriber(
    /**
     * Subscriber ID.
     */
    @SerialName("\$id")
    val id: String,

    /**
     * Subscriber creation time in ISO 8601 format.
     */
    @SerialName("\$createdAt")
    val createdAt: String,

    /**
     * Subscriber update date in ISO 8601 format.
     */
    @SerialName("\$updatedAt")
    val updatedAt: String,

    /**
     * Target ID.
     */
    @SerialName("targetId")
    val targetId: String,

    /**
     * Target.
     */
    @SerialName("target")
    @Contextual
    val target: Target,

    /**
     * Topic ID.
     */
    @SerialName("userId")
    val userId: String,

    /**
     * User Name.
     */
    @SerialName("userName")
    val userName: String,

    /**
     * Topic ID.
     */
    @SerialName("topicId")
    val topicId: String,

    /**
     * The target provider type. Can be one of the following: `email`, `sms` or `push`.
     */
    @SerialName("providerType")
    val providerType: String,

)

