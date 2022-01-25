package edc.common.file

import java.io.File

const val MiB = 1048576

fun checkFileSize(file: File, limit: Long = 1048576): Boolean {
    return file.length() <= limit
}