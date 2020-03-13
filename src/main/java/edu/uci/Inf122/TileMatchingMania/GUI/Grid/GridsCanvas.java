package edu.uci.Inf122.TileMatchingMania.GUI.Grid;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/*
 * A container class that will hold the grid structure which will 
 * be visually rendered. This class is the visual half to the game
 * state information found in GameGrid.
 */
public class GridsCanvas extends JPanel {
    private int rows;
    private int cols;
    private int boxSize;
    private Drawable[][] grid;

    /*
     * GridCanvas constructor.
     * 
     * @param rows The amount of rows found in GameGrid.
     * @param cols The amount of columns found in GameGrid.
     * @param boxSize The dimension for a single box tile.
     */
    public GridsCanvas(int rows, int cols, int boxSize) {
        int w = cols * boxSize;
        int h = rows * boxSize;
        setPreferredSize(new Dimension(w, h));
        setSize(w, h);
        this.rows = rows;
        this.cols = cols;
        this.boxSize = boxSize;
        setBorder(null);
    }

    /*
     * Set the drawable grid which is the visual representation of GameGrid.
     * 
     * @param grid A 2D array containing drawable types.
     */
    public void setGrid(Drawable[][] grid) {
        if (grid.length != rows || grid[0].length != cols) {
            throw new Error("rows or cols not equal");
        }

        this.grid = grid;
    }

    /*
     * Render all drawable components onto device desktop.
     */
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
