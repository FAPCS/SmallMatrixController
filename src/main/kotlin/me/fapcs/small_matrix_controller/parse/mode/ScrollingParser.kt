package me.fapcs.small_matrix_controller.parse.mode

import me.fapcs.shared.module.general.model.Color
import me.fapcs.shared.module.matrix.small.model.ScrollingMode
import me.fapcs.small_matrix_controller.model.Matrix
import me.fapcs.small_matrix_controller.model.MatrixFrame
import me.fapcs.small_matrix_controller.model.MatrixPixel
import me.fapcs.small_matrix_controller.model.SingleMatrix
import me.fapcs.small_matrix_controller.parse.simple.StringParser

object ScrollingParser {

    fun parse(mode: ScrollingMode, width: Int): Matrix {
        val line = StringParser.parse(
            mode.text,
            StringParser.OverflowStrategy.ADD,
            StringParser.CenterStrategy.LEFT.result()
        )

        val frames = mutableListOf<MatrixFrame>()
        val frame = Array(5) { Array(width) { false } }

        val highestX = line.keys.maxBy { it.first }.first
        var currentX = 0

        if (mode.loop) {
            for (y in 0 until 5) {
                for (x in 0 until width) frame[y][x] = line[
                    (if (mode.direction == ScrollingMode.Direction.RIGHT_TO_LEFT) x else highestX - width + x) to y
                ] ?: false
            }

            currentX = if (mode.direction == ScrollingMode.Direction.RIGHT_TO_LEFT) width else highestX - width - 1

            frames.add(parseFrame(frame, mode.color, width))
        }

        while ((currentX in 0..highestX) || (!mode.loop && frame.any { column -> column.any { it } })) {
            for (y in 0 until 5) {
                if (mode.direction == ScrollingMode.Direction.RIGHT_TO_LEFT) {
                    for (x in 0 until width - 1) frame[y][x] = frame[y][x + 1]
                    frame[y][width - 1] = line[currentX to y] ?: false
                } else {
                    for (x in width - 1 downTo 1) frame[y][x] = frame[y][x - 1]
                    frame[y][0] = line[currentX to y] ?: false
                }
            }

            currentX += if (mode.direction == ScrollingMode.Direction.RIGHT_TO_LEFT) 1 else -1

            frames.add(parseFrame(frame, mode.color, width))
        }

        return SingleMatrix(frames, 10, if (mode.loop) 100 else 1000)
    }

    private fun parseFrame(frame: Array<Array<Boolean>>, color: Color, width: Int) = MatrixFrame(
        frame.mapIndexed { y, column ->
            column.mapIndexed { x, value ->
                MatrixPixel(x to y, if (value) color else Color.BLACK)
            }
        }.flatten(),
        width
    )

}