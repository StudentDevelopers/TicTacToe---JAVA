package com.studentdevelopers.tictactoe.model.board;

import com.studentdevelopers.tictactoe.model.helpers.Observable;
import com.studentdevelopers.tictactoe.model.helpers.Observer;
import com.studentdevelopers.tictactoe.model.player.Figure;

public class Cell extends Observable implements Markable {

    private CellState state;

    public Cell() {
        this.state = CellState.EMPTY;
    }

    @Override
    public void markWith(Figure figure) {
        if (this.state() != CellState.EMPTY) return;
        this.state = figure.equals(Figure.CIRCLE) ? CellState.CIRCLE : CellState.CROSS;
        notifyObservers();
    }

    @Override
    public String toString() {
        return state.toString();
    }

    public CellState state() {
        return this.state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
