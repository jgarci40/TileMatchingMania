package edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare;

public class WhiteSquare extends RGBSquare {
    public WhiteSquare() {
        super(255, 255, 255, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public WhiteSquare(int width, int height) {
        super(255, 255, 255, width, height);
    }

    public WhiteSquare(WhiteSquare sq) {
        super(255, 255, 255, sq.width, sq.height);
    }
}
