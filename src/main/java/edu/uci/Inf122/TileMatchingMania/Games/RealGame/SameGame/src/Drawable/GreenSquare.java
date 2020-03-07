package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare;

public class GreenSquare extends RGBSquare {
    public GreenSquare() {
        super(0, 255, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public GreenSquare(int width, int height) {
        super(0, 255, 0, width, height);
    }

    public GreenSquare(GreenSquare sq) {
        super(0, 255, 0, sq.width, sq.height);
    }
}
