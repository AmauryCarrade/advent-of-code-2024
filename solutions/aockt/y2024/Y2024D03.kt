package aockt.y2024

import io.github.jadarma.aockt.core.Solution

object Y2024D03 : Solution {
    override fun partOne(input: String): Int = """mul\((\d+?),(\d+?)\)""".toRegex()
        .findAll(input)
        .map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
        .sum()

    override fun partTwo(input: String): Int {
        var mulActive = true

        return """(mul|do|don't)\(([\d,]+?)?\)""".toRegex()
            .findAll(input)
            .map { when (it.groupValues[1]) {
                "do" -> { mulActive = true; 0 }

                "don't" -> { mulActive = false; 0 }

                "mul" -> {
                    if (mulActive)
                        it.groupValues[2]
                            .split(",")
                            .let { it.first().toInt() * it.last().toInt() }
                    else 0
                }

                else -> 0
            } }
            .sum()
    }
}
