package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.TextSquare;

import java.awt.*;

public class TwoBlock extends TextSquare {

    public TwoBlock() {
        super(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, 64, "2", DEFAULT_COLOR);
    }

    public TwoBlock(String fontFamily, int fontStyle, int fontSize, String text, Color color) {
        super(fontFamily, fontStyle, fontSize, text, color);
    }

    public TwoBlock(Font font, String text, Color color) {
        super(font, text, color);
    }

    public TwoBlock(TwoBlock tb) {
        super(tb);
    }
}
