package io.appwrite.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.net.URLConnection
import java.nio.file.Files
import java.io.File

@RequiresApi(Build.VERSION_CODES.O)
actual fun guessMimeType(input: String): String {
    val file = File(input)
    return Files.probeContentType(file.toPath()) ?:
    URLConnection.guessContentTypeFromName(file.name) ?: ""
}
