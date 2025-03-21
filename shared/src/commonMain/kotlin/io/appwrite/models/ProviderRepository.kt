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
 * ProviderRepository
 */
@Serializable
data class ProviderRepository(
    /**
     * VCS (Version Control System) repository ID.
     */
    @SerialName("id")
    val id: String,

    /**
     * VCS (Version Control System) repository name.
     */
    @SerialName("name")
    val name: String,

    /**
     * VCS (Version Control System) organization name
     */
    @SerialName("organization")
    val organization: String,

    /**
     * VCS (Version Control System) provider name.
     */
    @SerialName("provider")
    val provider: String,

    /**
     * Is VCS (Version Control System) repository private?
     */
    @SerialName("xprivate")
    val xprivate: Boolean,

    /**
     * Auto-detected runtime suggestion. Empty if getting response of getRuntime().
     */
    @SerialName("runtime")
    val runtime: String,

    /**
     * Last commit date in ISO 8601 format.
     */
    @SerialName("pushedAt")
    val pushedAt: String,

)

