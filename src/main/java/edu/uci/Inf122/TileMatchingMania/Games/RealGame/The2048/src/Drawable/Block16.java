package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.TextSquare;

import java.awt.*;

public class Block16 extends TextSquare {
    public Block16() {
        super(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, 64, "16", Color.CYAN);
    }

    public Block16(String fontFamily, int fontStyle, int fontSize, String text, Color color) {
        super(fontFamily, fontStyle, fontSize, text, color);
    }

    public Block16(Font font, String text, Color color) {
        super(font, text, color);
    }

    public Block16(Block4 fb) {
        super(fb);
    }
}
