package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.TextSquare;

import java.awt.*;

public class Block8 extends TextSquare {
    public Block8() {
        super(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, 64, "8", Color.MAGENTA);
    }

    public Block8(String fontFamily, int fontStyle, int fontSize, String text, Color color) {
        super(fontFamily, fontStyle, fontSize, text, color);
    }

    public Block8(Font font, String text, Color color) {
        super(font, text, color);
    }

    public Block8(FourBlock fb) {
        super(fb);
    }
}
