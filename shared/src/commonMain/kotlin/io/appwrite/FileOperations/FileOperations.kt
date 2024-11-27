package io.appwrite.FileOperations

expect fun readFileSize(path: String): Long
expect fun readFileChunk(path: String, offset: Long, buffer: ByteArray, length: Int)
expect fun readFileBytes(path: String, offset: Long, length: Long): ByteArray