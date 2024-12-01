package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import org.springframework.data.util.StreamUtils
import java.util.stream.Collectors
import kotlin.math.abs
import kotlin.math.absoluteValue

object Y2024D01 : Solution {

    private fun parse(input: String): Pair<List<Int>, List<Int>> {
        return input.lines().stream()
            .map { line -> line.split(" ") }
            .map { lineParts -> Pair(lineParts.first().toInt(), lineParts.last().toInt()) }
            .collect(Collectors.teeing(
                Collectors.mapping ({ p -> p.first }, Collectors.toList()),
                Collectors.mapping ({ p -> p.second }, Collectors.toList())
            ) { a, b -> Pair(a, b) })
    }

    override fun partOne(input: String): Int = parse(input)
        .let { StreamUtils.zip(it.first.sorted().stream(), it.second.sorted().stream()) { a, b -> Pair(a, b) } }
        .map { pair -> abs(pair.first - pair.second) }
        .reduce { a, b -> a + b }
        .get().absoluteValue

    override fun partTwo(input: String): Int = parse(input).run {
        first.stream()
            .map { l -> l * second.count { v -> v == l } }
            .reduce { a, b -> a + b }
            .get()
    }
}
