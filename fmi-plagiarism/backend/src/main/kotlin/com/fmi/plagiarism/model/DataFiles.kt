package com.fmi.plagiarism.model

import java.time.LocalDate

data class DataFiles(
    val id: String? = null,
    val fileName: String? = null,
    val uploadDate: LocalDate? = null,
    val text: String? = null,
    val verified: String? = null,
    val plagiarismDetected: String? = null
)