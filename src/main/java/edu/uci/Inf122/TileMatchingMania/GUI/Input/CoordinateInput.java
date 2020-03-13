package edu.uci.Inf122.TileMatchingMania.GUI.Input;

/*
 * A coordinate will be the x,y tuple that comes from a mouse click.
 */
public class CoordinateInput extends Input {
    private int row, col;

    /*
     * CoordinateInput constructor.
     */
    public CoordinateInput() {
        this(-1, -1);
    }

    /*
     * Set the coordinate input.
     * 
     * @param row The x coordinate from GUI will map to a row in grid.
     * @param col The y coodinate from GUI will map to a col in grid.
     */
    public CoordinateInput(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /*
     * Get row.
     * 
     * @return int The row from the clicked coordinate.
     */
    public int getRow() {
        return row;
    }

    /*
     * Get col.
     * 
     * @return int The col from the clicked coordinate.
     */
    public int getCol() {
        return col;
    }
}
