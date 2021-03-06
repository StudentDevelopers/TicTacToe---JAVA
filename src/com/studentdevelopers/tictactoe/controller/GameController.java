package com.studentdevelopers.tictactoe.controller;

import com.studentdevelopers.tictactoe.model.Game;
import com.studentdevelopers.tictactoe.model.GameState;
import com.studentdevelopers.tictactoe.model.board.CellState;
import com.studentdevelopers.tictactoe.model.helpers.Observer;
import com.studentdevelopers.tictactoe.view.FinishedGameDialog;
import com.studentdevelopers.tictactoe.view.GameDisplay;
import com.studentdevelopers.tictactoe.view.operators.ButtonOperator;
import com.studentdevelopers.tictactoe.view.operators.CellOperator;

import static com.studentdevelopers.tictactoe.model.GameBuilder.buildAPersonVSPersonGame;
import static com.studentdevelopers.tictactoe.model.GameState.TIE;
import static com.studentdevelopers.tictactoe.model.GameState.WON;

public class GameController {

    private Game game;
    private final GameDisplay gameDisplay;
    private final ButtonOperator buttonOperator;

    public GameController() {
        game = buildAPersonVSPersonGame();
        buttonOperator = defineButtonOperator();
        gameDisplay = new GameDisplay(cellOperator(), buttonOperator);
    }

    private CellOperator cellOperator() {
        return new CellOperator() {
            @Override
            public CellState getStateForCell(int id) {
                return game.board().cells()[id - 1].state();
            }

            @Override
            public void addObserverToCell(int id, Observer observer) {
                game.board().cells()[id - 1].addObserver(observer);
            }

            @Override
            public void markCell(int id) {
                game.markCell(id);
                if (isFinished())
                    new FinishedGameDialog(finishGameText(), buttonOperator);
            }
        };
    }

    private boolean isFinished() {
        GameState state = game.state();
        return state == WON || state == TIE;
    }

    private String finishGameText() {
        if (game.state() == TIE) return "TIE";
        return game.winner() == game.playerA().figure() ?
                textToWinner(1) : textToWinner(2);
    }

    private String textToWinner(int id) {
        return "The player " + id + " is the winner";
    }

    private ButtonOperator defineButtonOperator() {
        return new ButtonOperator() {
            @Override
            public void resetGame() {
                game.resetGame();
                gameDisplay.restart();
            }

            @Override
            public void exitGame() {
                System.exit(0);
            }
        };
    }
}
