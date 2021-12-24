package com.fmi.plagiarism.model

import java.time.LocalDate

data class DataFiles(
    val id: String,
    val fileName: String,
    val uploadDate: LocalDate,
    val fileContent: String? = null,
    val verified: String? = null,
    val plagiarismDetected: String? = null
)