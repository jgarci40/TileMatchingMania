package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.TextSquare;

import java.awt.*;

public class TwoBlock extends TextSquare {

    public TwoBlock() {
        super(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE, DEFAULT_WIDTH, DEFAULT_HEIGHT, "2");
    }

    public TwoBlock(String fontFamily, int fontStyle, int fontSize, int width, int height, String text) {
        super(fontFamily, fontStyle, fontSize, width, height, text);
    }

    public TwoBlock(Font font, int width, int height, String text) {
        super(font, width, height, text);
    }

    public TwoBlock(TwoBlock t) {
        super(t.font, t.width, t.height, t.text);
    }
}
