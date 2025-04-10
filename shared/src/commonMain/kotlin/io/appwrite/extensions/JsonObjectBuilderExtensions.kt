package io.appwrite.extensions

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.put

fun JsonObjectBuilder.addToJsonObject(key: String, value: Any?) {
    when (value) {
        is String -> put(key, value)
        is Number -> put(key, value)
        is Boolean -> put(key, value)
        is List<*> -> {
            val jsonArray = JsonArray(value.map { item ->
                when (item) {
                    is String -> JsonPrimitive(item)
                    is Number -> JsonPrimitive(item)
                    is Boolean -> JsonPrimitive(item)
                    is Enum<*> -> JsonPrimitive(item.name)
                    null -> JsonNull
                    else -> JsonPrimitive(item.toString())
                }
            })
            put(key, jsonArray)
        }

        is Enum<*> -> put(key, JsonPrimitive(value.name))
        null -> put(key, JsonNull)
        else -> put(key, JsonPrimitive(value.toString()))
    }
}
