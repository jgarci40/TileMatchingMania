package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.TextSquare;

import java.awt.*;


public class Block4 extends TextSquare {
    public Block4() {
        super(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, 64, "4", Color.ORANGE);
    }

    public Block4(String fontFamily, int fontStyle, int fontSize, String text, Color color) {
        super(fontFamily, fontStyle, fontSize, text, color);
    }

    public Block4(Font font, String text, Color color) {
        super(font, text, color);
    }

    public Block4(Block4 fb) {
        super(fb);
    }
}

