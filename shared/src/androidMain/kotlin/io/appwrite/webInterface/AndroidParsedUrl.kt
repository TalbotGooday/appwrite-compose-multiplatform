package io.appwrite.webInterface

import android.net.Uri

class AndroidParsedUrl(private val uri: Uri) : ParsedUrl{
    override fun toString(): String = uri.toString()
}
