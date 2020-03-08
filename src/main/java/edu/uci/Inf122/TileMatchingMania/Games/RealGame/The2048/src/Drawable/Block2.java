package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.TextSquare;

import java.awt.*;

public class Block2 extends TextSquare {

    public Block2() {
        super(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, 64, "2", DEFAULT_COLOR);
    }

    public Block2(String fontFamily, int fontStyle, int fontSize, String text, Color color) {
        super(fontFamily, fontStyle, fontSize, text, color);
    }

    public Block2(Font font, String text, Color color) {
        super(font, text, color);
    }

    public Block2(Block2 tb) {
        super(tb);
    }
}
