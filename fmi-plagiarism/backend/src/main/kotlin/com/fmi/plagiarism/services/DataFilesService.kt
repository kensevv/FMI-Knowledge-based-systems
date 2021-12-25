package com.fmi.plagiarism.services

import com.fmi.plagiarism.jooq.tables.records.DataFilesRecord
import com.fmi.plagiarism.jooq.tables.references.DATA_FILES
import com.fmi.plagiarism.model.DataFiles
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class DataFilesService : Base() {

    private fun DataFilesRecord.mapToInternalModel() =
        this.into(DataFiles::class.java).copy(
            fileContent = String(this.text!!)
        )

    fun fetchAllDataFiles(): List<DataFiles> = db.selectFrom(DATA_FILES).fetch().map {
        it.mapToInternalModel()
    }

    fun fetchDataFilesById(id: String): DataFiles? =
        db.selectFrom(DATA_FILES).where(DATA_FILES.ID.eq(id)).fetchOne()?.mapToInternalModel()

    fun createNewDataFileRecord(fileContent: String, fileName: String?) = db.newRecord(
        DATA_FILES, DataFilesRecord(
            id = UUID.randomUUID().toString(),
            fileName = fileName ?: "Unknown",
            uploadDate = LocalDate.now(),
            text = fileContent.toByteArray(),
            verified = "N",
            plagiarismRate = null
        )
    ).insert()

    fun fetchOneRecordById(uuid: String) = db.fetchOne(DATA_FILES, DATA_FILES.ID.eq(uuid))
}