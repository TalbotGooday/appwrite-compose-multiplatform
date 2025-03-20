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
 * Log
 */
@Serializable
data class Log(
    /**
     * Event name.
     */
    @SerialName("event")
    val event: String,

    /**
     * User ID.
     */
    @SerialName("userId")
    val userId: String,

    /**
     * User Email.
     */
    @SerialName("userEmail")
    val userEmail: String,

    /**
     * User Name.
     */
    @SerialName("userName")
    val userName: String,

    /**
     * API mode when event triggered.
     */
    @SerialName("mode")
    val mode: String,

    /**
     * IP session in use when the session was created.
     */
    @SerialName("ip")
    val ip: String,

    /**
     * Log creation date in ISO 8601 format.
     */
    @SerialName("time")
    val time: String,

    /**
     * Operating system code name. View list of [available options](https://github.com/appwrite/appwrite/blob/master/docs/lists/os.json).
     */
    @SerialName("osCode")
    val osCode: String,

    /**
     * Operating system name.
     */
    @SerialName("osName")
    val osName: String,

    /**
     * Operating system version.
     */
    @SerialName("osVersion")
    val osVersion: String,

    /**
     * Client type.
     */
    @SerialName("clientType")
    val clientType: String,

    /**
     * Client code name. View list of [available options](https://github.com/appwrite/appwrite/blob/master/docs/lists/clients.json).
     */
    @SerialName("clientCode")
    val clientCode: String,

    /**
     * Client name.
     */
    @SerialName("clientName")
    val clientName: String,

    /**
     * Client version.
     */
    @SerialName("clientVersion")
    val clientVersion: String,

    /**
     * Client engine name.
     */
    @SerialName("clientEngine")
    val clientEngine: String,

    /**
     * Client engine name.
     */
    @SerialName("clientEngineVersion")
    val clientEngineVersion: String,

    /**
     * Device name.
     */
    @SerialName("deviceName")
    val deviceName: String,

    /**
     * Device brand name.
     */
    @SerialName("deviceBrand")
    val deviceBrand: String,

    /**
     * Device model name.
     */
    @SerialName("deviceModel")
    val deviceModel: String,

    /**
     * Country two-character ISO 3166-1 alpha code.
     */
    @SerialName("countryCode")
    val countryCode: String,

    /**
     * Country name.
     */
    @SerialName("countryName")
    val countryName: String,

)

