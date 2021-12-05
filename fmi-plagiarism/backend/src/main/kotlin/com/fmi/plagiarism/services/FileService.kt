package com.fmi.plagiarism.services

import org.springframework.stereotype.Service

@Service
class FileService {
    fun extractTextFromFileByteArray(file: ByteArray): String = String(file).replace("\\s".toRegex(), "")
}