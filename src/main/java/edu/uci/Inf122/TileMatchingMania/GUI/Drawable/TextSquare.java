package edu.uci.Inf122.TileMatchingMania.GUI.Drawable;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TextSquare implements Drawable {
    private int width;
    private int height;
    private Font font;
    private String text;

    public static final String DEFAULT_FONT_FAMILY = "TimesRoman";
    public static final int DEFAULT_FONT_STYLE = Font.PLAIN;
    public static final int DEFAULT_FONT_SIZE = 256;
    public static final int DEFAULT_WIDTH = 256;
    public static final int DEFAULT_HEIGHT = 256;
    public static final String DEFAULT_TEXT = "";

    public TextSquare() {
        this(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_TEXT);
    }

    public TextSquare(String fontFamily, int fontStyle, int fontSize, int width, int height, String text) {
        this(new Font(fontFamily, fontStyle, fontSize),
                width,
                height,
                text);
    }

    public TextSquare(Font font, int width, int height, String text) {
        this.font = font;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public TextSquare(TextSquare ts) {
        this(ts.font, ts.width, ts.height, ts.text);
    }

    public Image draw() {
        BufferedImage bufImg = new BufferedImage(width, height, 1);
        Graphics2D g2d = bufImg.createGraphics();
        FontRenderContext frc =
                new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(text, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (width / 2) - (rWidth / 2) - rX;
        int b = (height / 2) - (int)(rHeight * 0.45) - rY;

        g2d.setFont(font);
        g2d.setColor(new Color(255, 255,255));
        g2d.drawString(text, a, b);
        g2d.dispose();

        return bufImg;
    }
}
