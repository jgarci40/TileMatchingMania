package edu.uci.Inf122.TileMatchingMania.GameGrid;

import edu.uci.Inf122.TileMatchingMania.State.State;

public class Tile extends QuadNode {
    State currentState;
    int row;
    int col;

    public Tile(int row, int col) {
        this(row, col, null);
    }

    public Tile(int row, int col, State state) {
        this.row = row;
        this.col = col;
        this.currentState = state;
    }

    public Tile(Tile tile) {
        this(tile.row, tile.col, tile.currentState);
    }

    public State getState() {
        return currentState;
    }

    public void setState(State state) {
        currentState = state;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
