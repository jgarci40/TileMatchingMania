package edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RGBSquare implements Drawable {
    public int r;
    public int b;
    public int g;
    public int width;
    public int height;
    public static final int DEFAULT_WIDTH = 256;
    public static final int DEFAULT_HEIGHT = 256;
    public static final int DEFAULT_R = 0;
    public static final int DEFAULT_G = 0;
    public static final int DEFAULT_B = 0;

    public RGBSquare() {
        this(DEFAULT_R, DEFAULT_G, DEFAULT_B, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public RGBSquare(int r, int g, int b, int width, int height) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.width = width;
        this.height = height;
    }

    public RGBSquare(RGBSquare rgbs) {
        this(rgbs.r, rgbs.g, rgbs.b, rgbs.width, rgbs.height);
    }

    public Image draw() {
        BufferedImage bufImg = new BufferedImage(width, height, 1);
        Graphics2D g2d = bufImg.createGraphics();
        g2d.setColor(new Color(r, g, b));
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
        return bufImg;
    }
}
