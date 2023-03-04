package me.fapcs.small_matrix_controller.model

import me.fapcs.shared.module.general.model.Color

data class MatrixPixel(val position: MatrixPosition, val color: Color)

typealias MatrixPosition = Pair<Int, Int>