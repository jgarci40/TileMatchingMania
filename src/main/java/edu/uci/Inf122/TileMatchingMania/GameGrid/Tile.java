package edu.uci.Inf122.TileMatchingMania.GameGrid;

import edu.uci.Inf122.TileMatchingMania.State.State;

/*
 * This class represents a cell or tile that will be 
 * contained within a grid.
 * Is a child of QuadNode, so each tile will have a 
 * 4-way connection to it's left, right, up, down neighbors.
 */
public class Tile extends QuadNode {
	
    State currentState;
    int row;
    int col;

    /*
     * Tile constructor.
     * 
     * @param row A specific row in a grid.
     * @param col A specific column in a grid. 
     */
    public Tile(int row, int col) {
        this(row, col, null);
    }

    /*
     * Tile constructor.
     * 
     * @param row A specific row in a grid.
     * @param col A specific column in a grid. 
     * @param state Contains the information that will be used 
     * to visually represent a tile.
     */
    public Tile(int row, int col, State state) {
        this.row = row;
        this.col = col;
        this.currentState = state;
    }

    /*
     * Tile copy constructor. 
     * 
     * @param tile Tile object that will be copied.
     */
    public Tile(Tile tile) {
        this(tile.row, tile.col, tile.currentState);
    }

    /*
     * Get tile state.
     * 
     * @return State This gets the state object.
     */
    public State getState() {
        return currentState;
    }

    /*
     * Set tile state.
     * 
     * @param state The updated tile state.
     */
    public void setState(State state) {
        currentState = state;
    }

    /*
     * Get tile's row.
     * 
     * @return int This returns the tile's row.
     */
    public int getRow() {
        return row;
    }

    /*
     * Get tile's column.
     * 
     * @return int This returns the tile's column.
     */
    public int getCol() {
        return col;
    }
}
