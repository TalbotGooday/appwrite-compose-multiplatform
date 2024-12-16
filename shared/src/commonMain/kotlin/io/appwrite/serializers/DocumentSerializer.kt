package io.appwrite.serializers

import io.appwrite.extensions.json
import io.appwrite.models.Document
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

class DocumentSerializer<T>(private val dataSerializer: KSerializer<T>) : KSerializer<Document<T>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Document") {
        element("\$id", String.serializer().descriptor)
        element("\$collectionId", String.serializer().descriptor)
        element("\$databaseId", String.serializer().descriptor)
        element("\$createdAt", String.serializer().descriptor)
        element("\$updatedAt", String.serializer().descriptor)
        element("\$permissions", ListSerializer(DynamicLookupSerializer).descriptor)
        element("data", dataSerializer.descriptor)
    }

    override fun deserialize(decoder: Decoder): Document<T> {
        val jsonObject = decoder.decodeSerializableValue(JsonObject.serializer())

        // Extract system fields ($ prefixed)
        val id = jsonObject["\$id"]?.jsonPrimitive?.content ?: ""
        val collectionId = jsonObject["\$collectionId"]?.jsonPrimitive?.content ?: ""
        val databaseId = jsonObject["\$databaseId"]?.jsonPrimitive?.content ?: ""
        val createdAt = jsonObject["\$createdAt"]?.jsonPrimitive?.content ?: ""
        val updatedAt = jsonObject["\$updatedAt"]?.jsonPrimitive?.content ?: ""
        val permissions = jsonObject["\$permissions"]?.let {
            json.decodeFromJsonElement<List<Any>>(it)
        } ?: listOf()

        // Create data object from remaining fields
        val dataObject = buildJsonObject {
            jsonObject.forEach { (key, value) ->
                if (key.startsWith("$")) {
                    // Remove $ prefix for system fields
                    put(key.substring(1), value)
                } else {
                    put(key, value)
                }
            }
        }

        return Document(
            id = id,
            collectionId = collectionId,
            databaseId = databaseId,
            createdAt = createdAt,
            updatedAt = updatedAt,
            permissions = permissions,
            data = json.decodeFromJsonElement(dataSerializer, dataObject)
        )
    }

    override fun serialize(encoder: Encoder, value: Document<T>) {
        val dataJson = json.encodeToJsonElement(dataSerializer, value.data).jsonObject
        val combined = buildJsonObject {
            put("\$id", value.id)
            put("\$collectionId", value.collectionId)
            put("\$databaseId", value.databaseId)
            put("\$createdAt", value.createdAt)
            put("\$updatedAt", value.updatedAt)
            put("\$permissions", json.encodeToJsonElement(value.permissions))

            // Add all data fields to root
            dataJson.forEach { (key, value) ->
                put(key, value)
            }
        }

        return encoder.encodeSerializableValue(JsonObject.serializer(), combined)
    }
}