package edu.uci.Inf122.TileMatchingMania.GUI;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends Panel {
    JLabel label;

    public ScorePanel() {
        label = new JLabel("Score: 0");
        add(label);
        setVisible(true);
    }

    public void setScore(int i, boolean gameOver) {
        if (gameOver) {
            label.setForeground(Color.RED);
            label.setText("Score: " + i + " GAME OVER!");
        }
        else {
            label.setText("Score: " + i);
        }
    }
}
