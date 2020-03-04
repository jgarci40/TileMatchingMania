package edu.uci.Inf122.TileMatchingMania.GUI.Grid;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GridsCanvas extends JPanel {
    private int rows;
    private int cols;
    private int boxSize;
    private Drawable[][] grid;

    public GridsCanvas(int rows, int cols, int boxSize) {
        int w = cols * boxSize;
        int h = rows * boxSize;
        setPreferredSize(new Dimension(w, h));
        setSize(w, h);
        this.rows = rows;
        this.cols = cols;
        this.boxSize = boxSize;
    }

    public void setGrid(Drawable[][] grid) {
        if (grid.length != rows || grid[0].length != cols) {
            throw new Error("rows or cols not equal");
        }

        this.grid = grid;
    }

    public void paint(Graphics g) {
        if(grid != null) {
            int i = 0;
            for(Drawable[] rows : grid) {
                int j = 0;
                for(Drawable col : rows) {
                    BufferedImage img = (BufferedImage)col.draw();
                    g.drawImage(img, boxSize * j, boxSize * i,null);
                    j++;
                }
                i++;
            }
        }
    }
}
