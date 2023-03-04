package me.fapcs.small_matrix_controller.parse.mode

import me.fapcs.shared.module.matrix.small.model.SplitMode
import me.fapcs.small_matrix_controller.model.Matrix
import me.fapcs.small_matrix_controller.model.MatrixFrame
import me.fapcs.small_matrix_controller.model.MatrixPixel
import me.fapcs.small_matrix_controller.model.SplitMatrix
import me.fapcs.small_matrix_controller.parse.MatrixParser

object SplitParser {

    fun parse(mode: SplitMode, width: Int): Matrix {
        if (width != 41) throw IllegalArgumentException("Split mode only supports 41 width")

        return SplitMatrix(
            MatrixParser.parse(mode.left, 20),
            MatrixParser.parse(mode.right, 20)
        )
    }

    fun combine(left: MatrixFrame, right: MatrixFrame): MatrixFrame {
        if (left.width != 20 || right.width != 20) throw IllegalArgumentException("Split mode only supports 2x20 width")

        val joined = mutableListOf<MatrixPixel>()
        joined.addAll(left.pixels)
        joined.addAll(right.pixels.map {
            MatrixPixel(
                (it.position.first + 21 to it.position.second),
                it.color
            )
        })

        return MatrixFrame(joined, 41)
    }


}