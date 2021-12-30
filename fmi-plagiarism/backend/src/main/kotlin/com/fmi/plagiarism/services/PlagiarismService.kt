package com.fmi.plagiarism.services

import com.fmi.plagiarism.algorithm.DistanceFinder
import com.fmi.plagiarism.model.DataFiles
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class PlagiarismService : Base() {
    @Autowired
    private lateinit var dataFilesService: DataFilesService

    @Autowired
    private lateinit var distanceFinder: DistanceFinder

    fun runCheckOnSelectedFiles(selectedDataFiles: List<DataFiles>) {
        val allDataFiles: List<DataFiles> = dataFilesService.fetchAllDataFiles()

        selectedDataFiles.forEach { selectedFile ->
            dataFilesService.fetchOneRecordById(selectedFile.id)?.apply {
                verified = "Y"
                plagiarismRate = allDataFiles.filter {
                    it.id != selectedFile.id
                }.map { comparingFile ->
                    distanceFinder.findPercentageOfPlagiarism(selectedFile.fileContent!!, comparingFile.fileContent!!) * 100
                }.maxOrNull()?.toBigDecimal() ?: BigDecimal.ZERO
            }?.update()
        }
    }
}