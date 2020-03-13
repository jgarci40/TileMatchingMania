package edu.uci.Inf122.TileMatchingMania.GUI.Drawable;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/*
 * A tile will be able to hold text that will be rendered in a GUI. 
 * A instance of this class be represent the text content of a Tile from GameGrid.
 */
public class TextSquare implements Drawable {

    private int boxSize;
    private Font font;
    private String text;
    private Color color;

    public static final String DEFAULT_FONT_FAMILY = "TimesRoman";
    public static final int DEFAULT_FONT_STYLE = Font.PLAIN;
    public static final int DEFAULT_FONT_SIZE = 256;
    public static final String DEFAULT_TEXT = "";
    public static final Color DEFAULT_COLOR = Color.white;

    /*
     * TextSquare constructor.
     */
    public TextSquare() {
        this(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE, DEFAULT_TEXT, DEFAULT_COLOR);
    }

    public TextSquare(String fontFamily, int fontStyle, int fontSize,  String text, Color color) {
        this(new Font(fontFamily, fontStyle, fontSize), text, color);
    }

    /*
     * TextSquare constructor.
     * 
     * @param fontFamily The text font family.
     * @param frontStyle The text font to be displayed eventually in GUI tiles.
     * @param fontSize The text font size.
     * @param text The text that will be rendered in a GUI tile. 
     */
    public TextSquare(String fontFamily, int fontStyle, int fontSize,  String text) {
        this(new Font(fontFamily, fontStyle, fontSize), text, DEFAULT_COLOR);
    }

    /*
     * TextSquare constructor.
     * 
     * @param font Used to render text in visible way.
     * @param text The text that will be rendered in a GUI tile.
     * @param color Provide support for the sRGB values. 
     */
    public TextSquare(Font font, String text, Color color) {
        this.font = font;
        this.boxSize = font.getSize();
        this.text = text;
        this.color = color;
    }

    /*
     * TextSquare copy constuctor. 
     */
    public TextSquare(TextSquare ts) {
        this(ts.font, ts.text, ts.color);
    }

    /*
     * Build a BufferedImage object that can hold image data. 
     * 
     *  @return Image A representation for a graphical image.
     */
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

        if (a < rX) {
            g2d.scale(.4, .4);
            a += 50;
            b += 50;
        }

        g2d.setFont(font);
        g2d.setColor(color);
        g2d.drawString(text, a, b );
        g2d.dispose();

        return bufImg;
    }
}
