package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.TextSquare;

import java.awt.*;

public class Block1024 extends TextSquare {
    public Block1024() {
        super(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, 64, "1024", Color.orange);
    }

    public Block1024(String fontFamily, int fontStyle, int fontSize, String text, Color color) {
        super(fontFamily, fontStyle, fontSize, text, color);
    }

    public Block1024(Font font, String text, Color color) {
        super(font, text, color);
    }

    public Block1024(Block4 fb) {
        super(fb);
    }
}
