package omok.controller

import omok.model.OmokGameState
import omok.model.entity.Point
import omok.view.input.InputView
import omok.view.output.OutputView

class OmokController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun startOmok() {
        outputView.printStartGuide()
        runOmok(OmokGameState(), inputView::getStonePoint)
    }

    private tailrec fun runOmok(
        omokGameState: OmokGameState,
        inputPoint: () -> Point,
    ) {
        if (omokGameState.isFinished()) {
            afterOmok(omokGameState)
            return
        }
        outputView.printTurn(omokGameState.turn.board, omokGameState.turn.color())
        val newGameState = omokGameState.run(inputPoint())
        return runOmok(newGameState, inputPoint)
    }

    private fun afterOmok(omokGameState: OmokGameState) {
        outputView.printWinner(omokGameState.turn.board, omokGameState.turn.color())
    }
}