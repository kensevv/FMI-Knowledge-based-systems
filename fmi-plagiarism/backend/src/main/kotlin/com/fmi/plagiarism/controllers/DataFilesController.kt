package com.fmi.plagiarism.controllers

import com.fmi.plagiarism.services.DataFilesService
import com.fmi.plagiarism.services.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api")
class DataFilesController {

    @Autowired
    private lateinit var dataFilesService: DataFilesService

    @Autowired
    private lateinit var fileService: FileService

    @GetMapping("/all")
    fun getAllDataFiles() = dataFilesService.fetchAllDataFiles()

    @GetMapping("/verified")
    fun getAllVerifiedDataFiles() = dataFilesService.fetchAllVerifiedDataFiles()

    @GetMapping("/non-verified")
    fun getAllNonVerifiedDataFiles() = dataFilesService.fetchAllNonVerifiedDataFiles()

    @GetMapping("/plagiarism-detected")
    fun getAllPlagiarismDetectedDataFiles() = dataFilesService.fetchAllPlagiarismDetectedDataFiles()

    @GetMapping("/plagiarism-not-detected")
    fun getAllPlagiarismNotDetectedDataFiles() = dataFilesService.fetchAllPlagiarismNotDetectedDataFiles()

    @PostMapping("/file-upload")
    fun handleFileUpload(
        @RequestPart file: ByteArray,
        @RequestHeader(required = false, value = "File-Name") fileName: String?
    ) = dataFilesService.createNewDataFileRecord(fileService.extractTextFromFileByteArray(file), fileName)

}