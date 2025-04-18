package io.appwrite

import kotlinx.datetime.Clock
import kotlin.random.Random

/**
 * Helper class to generate ID strings for resources.
 */
class ID {
    companion object {
        private fun toHex(number: Long, width: Int): String {
            return number.toString(16).padStart(width, '0')
        }

        private fun hexTimestamp(): String {
            val now = Clock.System.now()
            val sec = now.epochSeconds
            val usec = (now.nanosecondsOfSecond / 1000) % 1000

            return toHex(sec, 8) + toHex(usec.toLong(), 5)
        }

        fun custom(id: String): String = id

        fun unique(padding: Int = 7): String {
            val baseId = hexTimestamp()
            val randomPadding = (1..padding)
                .map { Random.nextInt(0, 16).toString(16) }
                .joinToString("")

            return baseId + randomPadding
        }
    }
}
