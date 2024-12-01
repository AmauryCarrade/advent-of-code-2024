package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import java.util.stream.Collectors
import kotlin.math.abs

object Y2024D01 : Solution {

    private fun parse(input: String): Pair<List<Int>, List<Int>> {
        return input.lines().stream()
            .map { line -> line.split(" ") }
            .map { lineParts -> lineParts.first().toInt() to lineParts.last().toInt() }
            .collect(
                Collectors.teeing(
                    Collectors.mapping({ p -> p.first }, Collectors.toList()),
                    Collectors.mapping({ p -> p.second }, Collectors.toList())
                ) { a, b -> a to b })
    }

    override fun partOne(input: String): Int = parse(input)
        .let {
            it.first.sorted()
                .zip(it.second.sorted())
                .sumOf { (a, b) -> abs(a - b) }
        }

    override fun partTwo(input: String): Int = parse(input).run {
        first.stream()
            .map { l -> l * second.count { v -> v == l } }
            .reduce { a, b -> a + b }
            .get()
    }
}
