package model

import java.util.*
import kotlin.collections.ArrayList

class Genome {
    private val random = Random()

    private var dna: ArrayList<Int> = ArrayList()

    constructor(length: Int) {
        (1..length).forEach { _ ->
            dna.add(if (random.nextBoolean()) 1 else 0)
        }
    }

    constructor(dnaP1: List<Int>, dnaP2: List<Int>) {
        dna.addAll(dnaP1)
        dna.addAll(dnaP2)
    }

    fun getFitness(cargos: ArrayList<Cargo>, weightLimit: Int): Int {
        require(cargos.size == dna.size) { " ERROR: Cargo size and dna size must have equal size" }

        var value = 0
        var weight = 0
        cargos.forEachIndexed { index, cargo ->
            if (dna[index] == 1) {
                weight += cargo.weight
                value += cargo.value
            }
            if (weight > weightLimit) return 0;
        }
        return value
    }

    fun singlePointCrossover(other: Genome): Array<Genome> =
        when {
            random.nextInt(100) > 90 -> arrayOf(this, other)
            else -> {
                val crossOverIdx = random.nextInt(dna.size)
                arrayOf(
                    Genome(dna.subList(0, crossOverIdx), other.dna.subList(crossOverIdx, other.dna.size)),
                    Genome(other.dna.subList(0, crossOverIdx), dna.subList(crossOverIdx, dna.size))
                )
            }
        }


    fun mutate() {
        if (random.nextInt(100) > 90) {
            dna = ArrayList(dna.map {
                when {
                    random.nextBoolean() -> if (it == 0) 1 else 0
                    else -> it
                }
            })
        }
    }

    override fun toString(): String = dna.toString()
}