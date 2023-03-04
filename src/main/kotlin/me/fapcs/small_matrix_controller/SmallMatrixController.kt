package me.fapcs.small_matrix_controller

import me.fapcs.shared.communication.CommunicationHandler
import me.fapcs.shared.module.matrix.small.packet.DisableSmallMatrixSidePacket
import me.fapcs.shared.module.matrix.small.packet.UpdateSmallMatrixPacket
import me.fapcs.small_matrix_controller.display.MatrixDisplay

object SmallMatrixController {

    @JvmStatic
    fun main(args: Array<String>) {
        CommunicationHandler.receive<UpdateSmallMatrixPacket> { MatrixDisplay.show(it.side, it.mode) }
        CommunicationHandler.receive<DisableSmallMatrixSidePacket> { MatrixDisplay.disable(it.side) }
    }

}