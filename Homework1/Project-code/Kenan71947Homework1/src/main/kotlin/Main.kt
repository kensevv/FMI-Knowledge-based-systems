import model.Cargo
import model.Genome
import model.Ship
import java.io.File

fun main(args: Array<String>) {
    print("Please input ship's weight capacity (weight limit) : ")
    val weightLimit = readLine()?.trim()?.toIntOrNull()
    require(weightLimit != null) { "Wrong input" }

    val cargoList = readDataInputFromTxtFile("test_data_weights_values.txt")

    println("Calculating . . .")
    val result = getBestOptimalAnswer(ArrayList(cargoList), weightLimit)
    println("Answer: $result")

    println("Press enter to close.")
    readLine()
}

private fun getBestOptimalAnswer(cargoList: ArrayList<Cargo>, weightLimit: Int): Genome {
    val numberOfIndividuals = cargoList.size * 10
    val epochs = getFactorial(cargoList.size) / 2

    val bestValue = cargoList.sumOf { it.value }

    val p = Ship(numberOfIndividuals, cargoList, weightLimit)
    (0..epochs).forEach { _ ->
        p.nextGeneration(numberOfIndividuals / 2)
        if (p.stop(bestValue)) return p.getCurrentBest()
    }
    return p.getCurrentBest()
}

private fun readDataInputFromTxtFile(filename: String): List<Cargo> {
    val dataFile = File(filename)
    val lines = dataFile.readLines()
    require(lines.isNotEmpty() && lines.size >= 2)
    { "Wrong data input file format. First line - values, second line - weights." }

    val values = lines[0].split(",").map { it.trim().toIntOrNull() }
    val weights = lines[1].split(",").map { it.trim().toIntOrNull() }

    val totalCargo = values.size

    require(values.size == weights.size) { " The number of values and weights should be the same! " }
    return (0 until totalCargo).map {
        require(values[it] != null && weights[it] != null) { "Not all weights entered are integers! Fix the data.txt file." }
        Cargo(values[it]!!, weights[it]!!)
    }
}

private fun getFactorial(num: Int): Int = if (num >= 1) {
    num * getFactorial(num - 1)
} else {
    1
}
