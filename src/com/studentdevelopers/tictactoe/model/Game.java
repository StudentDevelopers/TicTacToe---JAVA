package com.studentdevelopers.tictactoe.model;

import com.studentdevelopers.tictactoe.model.board.Board;
import com.studentdevelopers.tictactoe.model.helpers.Observer;
import com.studentdevelopers.tictactoe.model.player.Figure;
import com.studentdevelopers.tictactoe.model.player.Player;
import com.studentdevelopers.tictactoe.model.winchecker.StateChecker;

import static com.studentdevelopers.tictactoe.model.GameState.RUNNING;

public class Game implements Observer {

    private final PlayersPair playersPair;
    private Player currentPlayer;
    private GameState state;

    public Game(PlayersPair playersPair) {
        this.playersPair = playersPair;
        this.currentPlayer = this.playersPair.playerA();
        this.state = RUNNING;
        this.board().addObserver(this);
    }

    public void markCell(int cellID) {
        currentPlayer.markCell(cellID);
    }

    public GameState state() {
        return this.state;
    }

    public String boardToString() {
        return board().toString();
    }

    public Board board() {
        return playersPair.board();
    }

    public Player playerA() {
        return playersPair.playerA();
    }

    public Player playerB() {
        return playersPair.playerB();
    }

    public Figure winner() {
        return currentPlayer.figure();
    }

    @Override
    public void update() {
        state = StateChecker.getUpdatedGameStateFor(board());
        if (state != RUNNING) return;
        currentPlayer = (currentPlayer == playerA()) ? playerB() : playerA();
    }

    public void resetGame() {
        board().resetBoard();
        state = RUNNING;
    }
}
