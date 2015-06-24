package com.studentdevelopers.tictactoe.view;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;

public class GameDisplay extends JFrame {

    private Map<String, CellButtonOperator> operationsMap;
    private BoardDisplay boardDisplay;

    public GameDisplay(Map<String, CellButtonOperator> operationsMap)  {
        super("Tic-Tac-Toe");
        this.operationsMap = operationsMap;
        gameSetUp();
        addComponents();
        setVisible(true);
    }

    private void setUpGame() {
        setMinimumSize(new Dimension(800, 600));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void addComponents() {
        boardDisplay = new BoardDisplay(this.operationsMap);
        add(boardDisplay, CENTER);
        add(new MenuPanel(), EAST);
    }

    public void restart() {
        //TODO refactor this shit
        remove(boardDisplay);
        boardDisplay = new BoardDisplay(this.operationsMap);
        add(boardDisplay, CENTER);
        refreshView();
    }

    private void refreshView() {
        revalidate();
        repaint();
    }
}
