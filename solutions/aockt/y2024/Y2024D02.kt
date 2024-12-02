package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import kotlin.math.absoluteValue
import kotlin.math.sign

object Y2024D02 : Solution {
    private fun parse(input: String): List<List<Int>> = input.lines()
        .map { it.split(" ").map { it.toInt() } }

    private fun isSafe(report: List<Int>): Boolean {
        val reportDiffs = report.zipWithNext { a, b -> b - a }
        return reportDiffs.map { it.sign }.toSet().size == 1 && reportDiffs.all { it.absoluteValue in 1..3 }
    }

    override fun partOne(input: String): Int = parse(input).count { isSafe(it) }

    override fun partTwo(input: String): Int = parse(input).count {
        report -> isSafe(report) || report.indices.map { i -> isSafe(report.filterIndexed { index, _ -> index != i }) }.any { it }
    }
}
