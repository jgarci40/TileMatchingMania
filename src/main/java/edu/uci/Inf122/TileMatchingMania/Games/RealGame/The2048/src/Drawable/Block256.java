package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.TextSquare;

import java.awt.*;

public class Block256 extends TextSquare {
    public Block256() {
        super(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, 64, "256", new Color(255, 69, 117));
    }

    public Block256(String fontFamily, int fontStyle, int fontSize, String text, Color color) {
        super(fontFamily, fontStyle, fontSize, text, color);
    }

    public Block256(Font font, String text, Color color) {
        super(font, text, color);
    }

    public Block256(Block4 fb) {
        super(fb);
    }
}
