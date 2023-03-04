package me.fapcs.small_matrix_controller.display

import kotlinx.coroutines.*
import me.fapcs.shared.communication.CommunicationHandler
import me.fapcs.shared.module.general.model.Color
import me.fapcs.shared.module.matrix.small.model.Side
import me.fapcs.shared.module.matrix.small.model.SmallMatrixMode
import me.fapcs.shared.module.stripe.model.LedStripe
import me.fapcs.shared.module.stripe.packet.SetLedsPacket
import me.fapcs.small_matrix_controller.model.MatrixFrame
import me.fapcs.small_matrix_controller.parse.MatrixParser

object MatrixDisplay {

    private val scope = CoroutineScope(Dispatchers.IO)

    private var frontJob: Job? = null
    private var backJob: Job? = null

    fun show(side: Side, mode: SmallMatrixMode) {
        when (side) {
            Side.FRONT -> if (frontJob?.isActive == true) frontJob?.cancel()
            Side.BACK -> if (backJob?.isActive == true) backJob?.cancel()
        }

        val job = scope.launch {
            val matrix = MatrixParser.parse(mode)

            for (frame in matrix) {
                showFrame(side, frame.first)

                try {
                    delay(frame.second)
                } catch (e: Exception) {
                    break
                }
            }
        }

        when (side) {
            Side.FRONT -> frontJob = job
            Side.BACK -> backJob = job
        }
    }

    fun disable(side: Side) {
        when (side) {
            Side.FRONT -> {
                if (frontJob?.isActive == true) frontJob?.cancel()
                showFrame(side, MatrixFrame.emptyFrame)
            }
            Side.BACK -> {
                if (backJob?.isActive == true) backJob?.cancel()
                showFrame(side, MatrixFrame.emptyFrame)
            }
        }
    }

    private fun showFrame(side: Side, frame: MatrixFrame) {
        val ledCommands = mutableMapOf<Int, Color>()

        frame.useMirrored = (side == Side.FRONT)

        frame.pixels.forEach { pixel ->
            val number = when (side) {
                // TODO: Verify if this is correct (Wait for soldering)
                Side.FRONT -> pixel.position.first + pixel.position.second * 41
                Side.BACK -> 410 - pixel.position.first - pixel.position.second * 41
            }

            ledCommands[number] = pixel.color
        }

        ledCommands.entries
            .map { it.key to it.value }
            .groupBy { it.second }
            .mapValues { it.value.map { pair -> pair.first } }
            .forEach { (color, entries) ->
                CommunicationHandler.send(
                    SetLedsPacket.create(
                        LedStripe.MATRIX,
                        color,
                        entries.toIntArray()
                    )
                )
            }
    }


}