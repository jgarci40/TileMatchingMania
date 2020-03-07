package edu.uci.Inf122.TileMatchingMania.GUI.Drawable;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TextSquare implements Drawable {
    private int boxSize;
    private Font font;
    private String text;

    public static final String DEFAULT_FONT_FAMILY = "TimesRoman";
    public static final int DEFAULT_FONT_STYLE = Font.PLAIN;
    public static final int DEFAULT_FONT_SIZE = 256;
    public static final String DEFAULT_TEXT = "";

    public TextSquare() {
        this(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE, DEFAULT_TEXT);
    }

    public TextSquare(String fontFamily, int fontStyle, int fontSize,  String text) {
        this(new Font(fontFamily, fontStyle, fontSize),
                text);
    }

    public TextSquare(Font font, String text) {
        this.font = font;
        this.boxSize = font.getSize();
        this.text = text;
    }

    public TextSquare(TextSquare ts) {
        this(ts.font, ts.text);
    }

    public Image draw() {
        BufferedImage bufImg = new BufferedImage(boxSize, boxSize, 1);
        Graphics2D g2d = bufImg.createGraphics();
        FontRenderContext frc =
                new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(text, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (boxSize / 2) - (rWidth / 2) - rX;
        int b = (boxSize / 2) - (int)(rHeight * 0.45) - rY;

        g2d.setFont(font);
        g2d.drawString(text, a, b);
        g2d.dispose();

        return bufImg;
    }
}
