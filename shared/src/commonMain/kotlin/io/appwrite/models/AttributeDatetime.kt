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
 * AttributeDatetime
 */
@Serializable
data class AttributeDatetime(
    /**
     * Attribute Key.
     */
    @SerialName("key")
    val key: String,

    /**
     * Attribute type.
     */
    @SerialName("type")
    val type: String,

    /**
     * Attribute status. Possible values: `available`, `processing`, `deleting`, `stuck`, or `failed`
     */
    @SerialName("status")
    val status: String,

    /**
     * Error message. Displays error generated on failure of creating or deleting an attribute.
     */
    @SerialName("error")
    val error: String,

    /**
     * Is attribute required?
     */
    @SerialName("required")
    val required: Boolean,

    /**
     * Is attribute an array?
     */
    @SerialName("array")
    var array: Boolean?,

    /**
     * Attribute creation date in ISO 8601 format.
     */
    @SerialName("\$createdAt")
    val createdAt: String,

    /**
     * Attribute update date in ISO 8601 format.
     */
    @SerialName("\$updatedAt")
    val updatedAt: String,

    /**
     * ISO 8601 format.
     */
    @SerialName("format")
    val format: String,

    /**
     * Default value for attribute when not provided. Only null is optional
     */
    @SerialName("default")
    var default: String?,

)

