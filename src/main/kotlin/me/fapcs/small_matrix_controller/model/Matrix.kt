package me.fapcs.small_matrix_controller.model

import me.fapcs.small_matrix_controller.parse.mode.SplitParser

interface Matrix : Iterator<Pair<MatrixFrame, Long>>

data class SplitMatrix(
    val left: Matrix,
    val right: Matrix
) : Matrix {

    private lateinit var leftFrame: Pair<MatrixFrame, Long>
    private var lastLeftFrame = 0L

    private lateinit var rightFrame: Pair<MatrixFrame, Long>
    private var lastRightFrame = 0L

    override fun hasNext() = true

    override fun next(): Pair<MatrixFrame, Long> {
        if (lastLeftFrame == 0L) {
            leftFrame = left.next()
            lastLeftFrame = System.currentTimeMillis()

            rightFrame = right.next()
            lastRightFrame = System.currentTimeMillis()
        }

        if (leftFrame.second - (System.currentTimeMillis() - lastLeftFrame) <= 0) {
            leftFrame = left.next()
            lastLeftFrame = System.currentTimeMillis()
        }

        if (rightFrame.second - (System.currentTimeMillis() - lastRightFrame) <= 0) {
            rightFrame = right.next()
            lastRightFrame = System.currentTimeMillis()
        }

        val frame = SplitParser.combine(leftFrame.first, rightFrame.first)
        return frame to 100
    }

}

data class SingleMatrix(val frames: List<MatrixFrame>, val fps: Long, val lastFrameTime: Long) : Matrix {

    private var frameCount = 0

    override fun hasNext() = true

    override fun next(): Pair<MatrixFrame, Long> {
        val frame = frames[frameCount]

        val time = if (frameCount == frames.lastIndex) lastFrameTime else 1000 / fps
        frameCount = (frameCount + 1) % frames.size

        return frame to time
    }

}

data class StaticMatrix(val frame: MatrixFrame) : Matrix {

    override fun hasNext() = true

    override fun next() = frame to -1L

}