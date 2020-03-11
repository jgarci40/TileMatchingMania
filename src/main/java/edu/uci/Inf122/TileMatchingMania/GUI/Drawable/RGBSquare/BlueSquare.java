package edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare;

public class BlueSquare extends RGBSquare {
        public BlueSquare() {
            super(0, 0, 255, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }

        public BlueSquare(int width, int height) {
            super(0, 0, 255, width, height);
        }

        public BlueSquare(BlueSquare sq) {
            super(0, 0, 255, sq.width, sq.height);
        }
}
