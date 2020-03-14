package edu.uci.Inf122.TileMatchingMania.GUI;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends Panel {
    JLabel label;

    public ScorePanel() {
        label = new JLabel("0");
        add(label);
        setVisible(true);
    }

    public void setScore(int i) {
        label.setText(i + "");
    }
}
