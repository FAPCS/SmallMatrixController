package me.fapcs.small_matrix_controller.parse

import me.fapcs.shared.module.matrix.small.model.*
import me.fapcs.small_matrix_controller.parse.mode.ScrollingParser
import me.fapcs.small_matrix_controller.parse.mode.SplitParser
import me.fapcs.small_matrix_controller.parse.mode.StaticParser
import me.fapcs.small_matrix_controller.parse.mode.SwitchingParser

object MatrixParser {

    fun parse(mode: SmallMatrixMode, width: Int = 41) = when (mode) {
        is StaticMode -> StaticParser.parse(mode, width)
        is ScrollingMode -> ScrollingParser.parse(mode, width)
        is SwitchingMode -> SwitchingParser.parse(mode, width)
        is SplitMode -> SplitParser.parse(mode, width)
    }

}