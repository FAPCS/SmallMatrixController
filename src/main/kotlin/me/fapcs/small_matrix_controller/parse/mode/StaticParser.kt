package me.fapcs.small_matrix_controller.parse.mode

import me.fapcs.shared.module.general.model.Color
import me.fapcs.shared.module.matrix.small.model.StaticMode
import me.fapcs.small_matrix_controller.model.MatrixFrame
import me.fapcs.small_matrix_controller.model.MatrixPixel
import me.fapcs.small_matrix_controller.model.StaticMatrix
import me.fapcs.small_matrix_controller.parse.simple.StringParser

object StaticParser {

    fun parse(mode: StaticMode, width: Int) = StaticMatrix(
        MatrixFrame(
            StringParser.parse(
                mode.text,
                StringParser.OverflowStrategy.TRUNCATE,
                StringParser.CenterStrategy.CENTER.with(width)
            ).map { (position, value) ->
                MatrixPixel(
                    position,
                    if (value) mode.color else Color(0, 0, 0)
                )
            },
            width
        )
    )

}