package com.fmi.plagiarism.algorithm

import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class Tokenizer {
    private val commonWords =
        listOf("at", "with", "because", "for", "was", "were", "is", "are", "a", "an", "of", "we", "do")

    fun tokenize(text: String): List<String> =
        text.split("(\\s+)|([.,!?:;'\\\"\\'-])")
            .filter { it != "" }
            .map { it.lowercase() }

    fun stem(words: List<String>): String =
        words.stream()
            .filter { word: String -> !commonWords.contains(word) }
            .collect(Collectors.joining(" "))
}