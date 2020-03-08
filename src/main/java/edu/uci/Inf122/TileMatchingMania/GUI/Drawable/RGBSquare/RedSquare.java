package edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare;

public class RedSquare extends RGBSquare {
    public RedSquare() {
        super(255, 0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public RedSquare(int width, int height) {
        super(255, 0, 0, width, height);
    }

    public RedSquare(RedSquare sq) {
        super(255, 0, 0, sq.width, sq.height);
    }
}
