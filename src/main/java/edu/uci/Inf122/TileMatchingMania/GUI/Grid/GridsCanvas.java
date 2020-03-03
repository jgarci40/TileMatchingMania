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


//                for(int i = 0; i < rows; i++) {
//            for(int j = 0; j < cols; j++) {
//                RGBSquare rgb = new RGBSquare((5 * i) % 255, (7 * j) % 255, (10 * i * j) % 255, boxSize, boxSize);
//                BufferedImage img = (BufferedImage)rgb.draw();
//                g.drawImage(img, boxSize * j, boxSize * i,null);
//            }
//        }
//        int i;
//        int width = getSize().width;
//        int height = getSize().height;
//
//        // draw the rows
//        int rowHt = height / (rows);
//        for (i = 0; i < rows; i++)
//            g.drawLine(0, i * rowHt, width, i * rowHt);
//
//        // draw the columns
//        int rowWid = width / (cols);
//        for (i = 0; i < cols; i++)
//            g.drawLine(i * rowWid, 0, i * rowWid, height);
//        TextSquare rgb = new TextSquare("Serif", Font.PLAIN, 256, 256, 256, "2");
//        BufferedImage bufImg = (BufferedImage)rgb.draw();
//        g.drawImage(bufImg, 0 ,0, 100, 100, null);
//    }
    }

}