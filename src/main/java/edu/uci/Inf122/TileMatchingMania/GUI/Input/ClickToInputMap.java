package edu.uci.Inf122.TileMatchingMania.GUI.Input;

public class ClickToInputMap {
    Input defaultInput = null;

    public Input getInput(int row, int col) {
        return new CoordinateInput(row, col);
    }
}
