package com.fmi.plagiarism.algorithm

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DistanceFinder {
    @Autowired
    private lateinit var tokenizer: Tokenizer

    private fun computeLevenshteinDistance(source: String, target: String): Int {
        if (source.isEmpty() || target.isEmpty()) return 0
        if (source == target) return source.length
        val sourceWordCount = source.length
        val targetWordCount = target.length
        if (sourceWordCount == 0) return targetWordCount
        if (targetWordCount == 0) return sourceWordCount
        val distance = Array(sourceWordCount + 1) { IntArray(targetWordCount + 1) }
        run {
            var i = 0
            while (i <= sourceWordCount) {
                distance[i][0] = i++
            }
        }
        var j = 0
        while (j <= targetWordCount) {
            distance[0][j] = j++
        }
        for (i in 1..sourceWordCount) {
            for (j in 1..targetWordCount) {
                val cost = if (target[j - 1] == source[i - 1]) 0 else 1
                distance[i][j] =
                    Math.min(Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1), distance[i - 1][j - 1] + cost)
            }
        }
        return distance[sourceWordCount][targetWordCount]
    }

    private fun calculateSimilarity(source: String?, target: String?): Double {
        if (source == null || target == null) return 0.0
        if (source.isEmpty() || target.isEmpty()) return 0.0
        if (source == target) return 1.0
        val stepsToSame = computeLevenshteinDistance(source, target)
        return 1.0 - stepsToSame.toDouble() / Math.max(source.length, target.length).toDouble()
    }

    fun findPercentageOfPlagiarism(source: String, target: String): Double =
        calculateSimilarity(
            tokenizer.stem(
                tokenizer.tokenize(source)
            ), tokenizer.stem(
                tokenizer.tokenize(target)
            )
        )
}