package model

import java.util.*

class Ship(numberOfCargos: Int, cargos: ArrayList<Cargo>, wLimit: Int) {
    private var genomes: ArrayList<Genome> = ArrayList()
    private var cargos: ArrayList<Cargo> = ArrayList()
    private var weightLimit: Int = 0

    private val r = Random()

    init {
        this.weightLimit = wLimit
        this.cargos = cargos
        (1..numberOfCargos).forEach { _ -> genomes.add(Genome(cargos.size)) }
    }

    private fun selection(cullingLimit: Int) {
        require(cullingLimit > 0 && cullingLimit <= genomes.size) { " Wrong culling limit value provided! " }
        this.genomes = ArrayList(this.genomes.sortedByDescending { it.getFitness(cargos, weightLimit) }
            .subList(0, cullingLimit))
    }

    fun nextGeneration(cullingLimit: Int) {
        require(cullingLimit <= genomes.size) { " ERROR: Culling limit must be smaller than to the number of individuals " }
        var cullingLimit1 = cullingLimit

        val nextGenomes = ArrayList<Genome>()
        genomes.forEach { it.mutate() }
        selection(cullingLimit)

        while (cullingLimit1 * 2 > 0) {
            var p1Idx = 0
            var p2Idx = 0
            while (p1Idx == p2Idx) {
                val numIndividuals = genomes.size
                p1Idx = r.nextInt(numIndividuals)
                p2Idx = r.nextInt(numIndividuals)
            }
            nextGenomes.addAll(genomes[p1Idx].singlePointCrossover(genomes[p2Idx]))
            --cullingLimit1
        }
        this.genomes = nextGenomes
    }

    fun getCurrentBest() = genomes.maxByOrNull { it.getFitness(this.cargos, weightLimit) }!!


    fun stop(bestFitness: Int): Boolean {
        if (genomes.find { it.getFitness(cargos, weightLimit) == bestFitness } != null) return true
        genomes.forEach {
            if (it.getFitness(cargos, weightLimit) != genomes.first().getFitness(cargos, weightLimit)) return false
        }
        return true
    }

    override fun toString() = "$genomes \n ${
        genomes.map {
            it.getFitness(cargos, weightLimit)
        }
    }\n"
}