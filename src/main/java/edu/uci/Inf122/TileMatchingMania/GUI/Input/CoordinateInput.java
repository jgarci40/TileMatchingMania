package edu.uci.Inf122.TileMatchingMania.GUI.Input;

public class CoordinateInput extends Input {
    private int row, col;

    // Can be used for error detection
    public CoordinateInput() {
        this(-1, -1);
    }

    public CoordinateInput(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
