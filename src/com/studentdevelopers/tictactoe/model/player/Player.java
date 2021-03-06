package com.studentdevelopers.tictactoe.model.player;

import com.studentdevelopers.tictactoe.model.board.Board;
import com.studentdevelopers.tictactoe.model.board.Markable;

public class Player {

    private final Board board;
    private final Figure figure;

    public Player(Board board, Figure figure) {
        this.board = board;
        this.figure = figure;
    }

    public void markCell(int cellID) {
        cell(cellID).markWith(figure);
    }

    private Markable cell(int cellID) {
        return board.cell(cellID);
    }

    public Board board() {
        return this.board;
    }

    public Figure figure() {
        return this.figure;
    }
}
