package com.fmi.plagiarism.services

import com.fmi.plagiarism.jooq.tables.records.DataFilesRecord
import com.fmi.plagiarism.jooq.tables.references.DATA_FILES
import com.fmi.plagiarism.model.DataFiles
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class DataFilesService : Base() {

    fun fetchAllDataFiles(): List<DataFiles> = db.selectFrom(DATA_FILES).fetchInto(DataFiles::class.java)

    fun fetchDataFilesById(id: String): DataFiles? =
        db.selectFrom(DATA_FILES).where(DATA_FILES.ID.eq(id)).fetchOneInto(DataFiles::class.java)

    fun fetchAllVerifiedDataFiles(): List<DataFiles> =
        db.selectFrom(DATA_FILES).where(DATA_FILES.VERIFIED.eq("Y")).fetchInto(DataFiles::class.java)

    fun fetchAllNonVerifiedDataFiles(): List<DataFiles> =
        db.selectFrom(DATA_FILES).where(DATA_FILES.VERIFIED.eq("N")).fetchInto(DataFiles::class.java)

    fun fetchAllPlagiarismDetectedDataFiles(): List<DataFiles> =
        db.selectFrom(DATA_FILES).where(DATA_FILES.PLAGIARISM_DETECTED.eq("Y")).fetchInto(DataFiles::class.java)

    fun fetchAllPlagiarismNotDetectedDataFiles(): List<DataFiles> =
        db.selectFrom(DATA_FILES).where(DATA_FILES.VERIFIED.eq("N")).fetchInto(DataFiles::class.java)

    fun createNewDataFileRecord(text: String, fileName: String?) = db.newRecord(
        DATA_FILES, DataFilesRecord(
            id = UUID.randomUUID().toString(),
            fileName = fileName ?: "Unknown",
            uploadDate = LocalDate.now(),
            text = text,
            verified = "N",
            plagiarismDetected = null
        )
    ).insert()
}