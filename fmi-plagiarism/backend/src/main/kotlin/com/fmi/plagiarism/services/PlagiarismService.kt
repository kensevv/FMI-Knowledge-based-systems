package com.fmi.plagiarism.services

import com.fmi.plagiarism.model.DataFiles
import liquibase.pro.packaged.it
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class PlagiarismService : Base() {
    @Autowired
    private lateinit var dataFilesService: DataFilesService

    fun runCheckOnSelectedFiles(selectedDataFiles: List<DataFiles>) {
        val allDataFiles: List<DataFiles> = dataFilesService.fetchAllDataFiles()

        selectedDataFiles.forEach { selectedFile ->
            dataFilesService.fetchOneRecordById(selectedFile.id)?.apply {
                verified = "Y"
                plagiarismRate = allDataFiles.filter {
                    it.id != selectedFile.id
                }.map { comparingFile ->
                    getPlagiarismRate(selectedFile, comparingFile)
                }.maxOrNull()?.toBigDecimal() ?: BigDecimal.ZERO
            }?.update()
        }
    }

    private fun getPlagiarismRate(selectedFile: DataFiles, comparingFile: DataFiles): Double {
        return 60.0
    }
}