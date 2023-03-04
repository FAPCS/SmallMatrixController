package me.fapcs.small_matrix_controller.parse.simple

import kotlin.String

object StringParser {

    fun parse(
        string: String,
        overflowStrategy: OverflowStrategy,
        centerStrategy: SizedCenterStrategy
    ): Map<Pair<Int, Int>, Boolean> {
        val matrixPositions = string
            .toCharArray()
            .map { LetterParser.parse(it) }
            .mapIndexed { index, letter ->
                letter
                    .mapIndexed { y, column ->
                        column
                            .mapIndexed { x, value ->
                                ((x + index * 6) to y) to value
                            }
                    }
                    .flatten()
            }
            .flatten()
            .toMutableList()

        if (overflowStrategy == OverflowStrategy.TRUNCATE)
            matrixPositions.removeIf { (position, _) -> position.first >= centerStrategy.width }

        val offset = when (centerStrategy.strategy) {
            CenterStrategy.LEFT -> 0
            CenterStrategy.RIGHT -> centerStrategy.width - matrixPositions.maxOf { (position, _) -> position.first }
            CenterStrategy.CENTER -> (centerStrategy.width - matrixPositions.maxOf { (position, _) -> position.first }) / 2
        }

        return matrixPositions.associate { (position, value) ->
            (position.first + offset to position.second) to value
        }
    }

    enum class OverflowStrategy {
        TRUNCATE,
        ADD
    }

    enum class CenterStrategy {
        LEFT,
        RIGHT,
        CENTER;

        fun with(width: Int) = SizedCenterStrategy(this, width)

        fun result() = SizedCenterStrategy(this, 0)

    }

    data class SizedCenterStrategy(
        val strategy: CenterStrategy,
        val width: Int
    )

}