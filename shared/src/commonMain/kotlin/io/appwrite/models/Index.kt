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
 * Index
 */
@Serializable
data class Index(
    /**
     * Index Key.
     */
    @SerialName("key")
    val key: String,

    /**
     * Index type.
     */
    @SerialName("type")
    val type: String,

    /**
     * Index status. Possible values: `available`, `processing`, `deleting`, `stuck`, or `failed`
     */
    @SerialName("status")
    val status: String,

    /**
     * Error message. Displays error generated on failure of creating or deleting an index.
     */
    @SerialName("error")
    val error: String,

    /**
     * Index attributes.
     */
    @SerialName("attributes")
    val attributes: List<String>,

    /**
     * Index orders.
     */
    @SerialName("orders")
    var orders: List<String>?,

    /**
     * Index creation date in ISO 8601 format.
     */
    @SerialName("\$createdAt")
    val createdAt: String,

    /**
     * Index update date in ISO 8601 format.
     */
    @SerialName("\$updatedAt")
    val updatedAt: String,

)

