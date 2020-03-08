package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.TextSquare;

import java.awt.*;

public class EmptyBlock extends TextSquare {
    public EmptyBlock() {
        super(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE, "", Color.black);
    }

    public EmptyBlock(String fontFamily, int fontStyle, int fontSize, String text, Color color) {
        super(fontFamily, fontStyle, fontSize, text, color);
    }

    public EmptyBlock(Font font, String text, Color color) {
        super(font, text, color);
    }

    public EmptyBlock(EmptyBlock e) {
        super(e);
    }
}
