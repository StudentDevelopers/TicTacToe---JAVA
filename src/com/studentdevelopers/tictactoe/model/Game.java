package com.studentdevelopers.tictactoe.model;

import com.studentdevelopers.tictactoe.model.board.Board;
import com.studentdevelopers.tictactoe.model.board.CellState;
import com.studentdevelopers.tictactoe.model.player.Player;

public class Game implements Observer {

    private final PlayersPair playersPair;
    private Player currentPlayer;
    private GameState state;

    public Game(PlayersPair playersPair) {
        this.playersPair = playersPair;
        this.currentPlayer = this.playersPair.playerA();
        this.state = GameState.RUNNING;
        this.board().addObserver(this);
    }

    public void markCell(int cellID) {
        currentPlayer.markCell(cellID);
    }

    public GameState gameState() {
        return this.state;
    }

    public String boardToString() {
        return board().toString();
    }

    private Board board() {
        return playersPair.board();
    }

    public Player playerA() {
        return playersPair.playerA();
    }

    public Player playerB() {
        return playersPair.playerB();
    }

    public String winner() {
        return "PlayerA";
    }

    @Override
    public void update() {
        if (WinChecker.isThereWinnerInBoard(board())) state = GameState.WON;

        currentPlayer = (currentPlayer == playerA()) ? playerB() : playerA();
    }
}
