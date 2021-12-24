package com.fmi.plagiarism.services

import com.fmi.plagiarism.model.DataFiles
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlagiarismService : Base() {
    @Autowired
    private lateinit var dataFilesService: DataFilesService

    fun runCheckOnSelectedFiles(selectedDataFiles: List<DataFiles>) {
        val allDataFiles: List<DataFiles> = dataFilesService.fetchAllDataFiles()
        selectedDataFiles.forEach {
            dataFilesService.fetchOneRecordById(it.id)?.apply {
                verified = "Y"
                plagiarismDetected = if (getPlagiarismPercentageForFile(it, allDataFiles) > 50) "Y" else "N"
            }?.update()
        }
    }

    private fun getPlagiarismPercentageForFile(file: DataFiles, allDataFiles: List<DataFiles>): Double {
        return 60.0
    }

}