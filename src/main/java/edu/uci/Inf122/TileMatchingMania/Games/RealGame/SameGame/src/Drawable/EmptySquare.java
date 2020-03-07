package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare;

public class EmptySquare extends RGBSquare {
    public EmptySquare() {
        super(0, 0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public EmptySquare(int width, int height) {
        super(0, 0, 0, width, height);
    }

    public EmptySquare(EmptySquare sq) {
        super(0, 0, 0, sq.width, sq.height);
    }
}
