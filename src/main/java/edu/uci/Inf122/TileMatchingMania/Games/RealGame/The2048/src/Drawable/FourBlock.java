package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.TextSquare;

import java.awt.*;


public class FourBlock extends TextSquare {
    public FourBlock() {
        super(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, 64, "4", Color.ORANGE);
    }

    public FourBlock(String fontFamily, int fontStyle, int fontSize, String text, Color color) {
        super(fontFamily, fontStyle, fontSize, text, color);
    }

    public FourBlock(Font font, String text, Color color) {
        super(font, text, color);
    }

    public FourBlock(FourBlock fb) {
        super(fb);
    }
}

