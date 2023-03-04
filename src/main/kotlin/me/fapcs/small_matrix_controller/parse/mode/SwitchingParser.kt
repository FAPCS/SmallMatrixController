package me.fapcs.small_matrix_controller.parse.mode

import me.fapcs.shared.module.general.model.Color
import me.fapcs.shared.module.matrix.small.model.SwitchingMode
import me.fapcs.small_matrix_controller.model.MatrixFrame
import me.fapcs.small_matrix_controller.model.MatrixPixel
import me.fapcs.small_matrix_controller.model.SingleMatrix
import me.fapcs.small_matrix_controller.parse.simple.StringParser

object SwitchingParser {

    fun parse(mode: SwitchingMode, width: Int) = SingleMatrix(
            mode.texts.map { frame ->
                MatrixFrame(
                    StringParser.parse(
                        frame,
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
            },
            1,
            1000
        )

}