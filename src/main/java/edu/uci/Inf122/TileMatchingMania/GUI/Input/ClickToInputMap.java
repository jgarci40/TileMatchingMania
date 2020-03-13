package edu.uci.Inf122.TileMatchingMania.GUI.Input;

/*
 * Map the click coordinate from mouse on device 
 * to a GameGrid row, col coordinate.
 */
public class ClickToInputMap {
    Input defaultInput = null;

    /*
     * Get player click coordinate as row, col pair.
     * 
     * @param row A player's x coordinate from mouse click.
     * @param col A player's y coordinate from mouse click.
     * 
     * @return Input A certain input method will be specified such as mouse or keyboard.
     */
    public Input getInput(int row, int col) {
        return new CoordinateInput(row, col);
    }
}
