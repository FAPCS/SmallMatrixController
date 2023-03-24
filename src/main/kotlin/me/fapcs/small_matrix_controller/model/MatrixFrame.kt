package me.fapcs.small_matrix_controller.model

import me.fapcs.shared.module.general.model.Color

data class MatrixFrame(val pixels: List<MatrixPixel>, val width: Int) {

    val mirrored by lazy {
        pixels.map { MatrixPixel(width - it.position.first - 1 to it.position.second, it.color) }
    }

    var useMirrored = false

    fun getPixel(position: MatrixPosition) =
        if (useMirrored) mirrored.firstOrNull { it.position == position }
        else pixels.firstOrNull { it.position == position }

    fun getPixel(x: Int, y: Int) = getPixel(x to y)

    fun getPixel(position: MatrixPosition, def: Color) = getPixel(position) ?: MatrixPixel(position, def)

    fun getPixel(x: Int, y: Int, def: Color) = getPixel(x to y, def)

    fun getPixelColor(position: MatrixPosition) = getPixel(position)?.color

    fun getPixelColor(x: Int, y: Int) = getPixelColor(x to y)

    fun getPixelColor(position: MatrixPosition, def: Color) = getPixelColor(position) ?: def

    fun getPixelColor(x: Int, y: Int, def: Color) = getPixelColor(x to y, def)

    companion object {

        val emptyFrame by lazy {
            MatrixFrame(
                (0 until 41).flatMap { x ->
                    (0 until 5).map { y -> MatrixPixel(x to y, Color.BLACK) }
                },
                41
            )
        }

    }

}