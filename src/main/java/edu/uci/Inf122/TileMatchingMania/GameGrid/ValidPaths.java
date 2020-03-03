package edu.uci.Inf122.TileMatchingMania.GameGrid;

public class ValidPaths {
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    public void setUp(boolean bool) {
        up = bool;
    }

    public boolean getUp() {
        return up;
    }

    public void setDown(boolean bool) {
        down = bool;
    }

    public boolean getDown() {
        return down;
    }

    public void setLeft(boolean bool) {
        left = bool;
    }

    public boolean getLeft() {
        return left;
    }

    public void setRight(boolean bool) {
        right = bool;
    }

    public boolean getRight() {
        return right;
    }
}
