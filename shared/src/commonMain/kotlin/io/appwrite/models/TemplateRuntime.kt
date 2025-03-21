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
 * Template Runtime
 */
@Serializable
data class TemplateRuntime(
    /**
     * Runtime Name.
     */
    @SerialName("name")
    val name: String,

    /**
     * The build command used to build the deployment.
     */
    @SerialName("commands")
    val commands: String,

    /**
     * The entrypoint file used to execute the deployment.
     */
    @SerialName("entrypoint")
    val entrypoint: String,

    /**
     * Path to function in VCS (Version Control System) repository
     */
    @SerialName("providerRootDirectory")
    val providerRootDirectory: String,

)

