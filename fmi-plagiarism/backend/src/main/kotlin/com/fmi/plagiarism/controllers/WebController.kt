package com.fmi.plagiarism.controllers

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api")
class WebController {

    @GetMapping("/test")
    fun test() = "working"
}