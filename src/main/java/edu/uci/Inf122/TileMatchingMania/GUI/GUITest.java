package edu.uci.Inf122.TileMatchingMania.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.RGBSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.Grid.GridsCanvas;

import javax.swing.*;

public class GUITest extends JFrame {
    public GUITest() {
        int rows = 76;
        int cols = 46;
        int boxSize = 32;
        Drawable[][] drawables = new Drawable[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                RGBSquare rgb = new RGBSquare((5 * i) % 255, (7 * j) % 255, (10 * i * j) % 255, boxSize, boxSize);
                drawables[i][j] = rgb;
            }
        }

        GridsCanvas xyz = new GridsCanvas(rows, cols, boxSize);
        xyz.setGrid(drawables);
        add(xyz);
        pack();
    }

    public static void main(String[] a) {
        new GUITest().setVisible(true);
    }
}
