package io.appwrite.fileOperations

import java.io.RandomAccessFile

actual fun readFileSize(path: String): Long {
    return RandomAccessFile(path, "r").use { it.length() }
}

actual fun readFileChunk(path: String, offset: Long, buffer: ByteArray, length: Int) {
    RandomAccessFile(path, "r").use { file ->
        file.seek(offset)
        file.read(buffer, 0, length)
    }
}

actual fun readFileBytes(path: String, offset: Long, length: Long): ByteArray {
    return RandomAccessFile(path, "r").use { file ->
        file.seek(offset)
        ByteArray(length.toInt()).also { buffer ->
            file.read(buffer, 0, length.toInt())
        }
    }
}
