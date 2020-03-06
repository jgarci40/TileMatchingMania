package edu.uci.Inf122.TileMatchingMania.Games.Tests.RandomGrid.src.Drawable;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare;

public class BlackSquare extends RGBSquare {
    public BlackSquare() {
        super(0, 0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public BlackSquare(int width, int height) {
        super(0, 0, 0, width, height);
    }

    public BlackSquare(BlackSquare sq) {
        super(0, 0, 0, sq.width, sq.height);
    }
}
