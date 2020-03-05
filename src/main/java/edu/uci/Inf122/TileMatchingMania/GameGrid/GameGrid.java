package edu.uci.Inf122.TileMatchingMania.GameGrid;

import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchHeuristics.SearchHeuristic;
import edu.uci.Inf122.TileMatchingMania.State.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
 * This class contains the grid data structure for a tile based game, 
 * search algorithms to find matching tiles.
 * Tiles cannot be changed once a grid is set, only the tile State
 * can be modified to different states.
 */
public class GameGrid {
    private Tile[][] grid;
    private int rows;
    private int cols;
    private State defaultState;
    HashSet<Class> validStates;

    /*
     * GameGrid constructor.
     * 
     * @param rows 
     * @param cols
     * @param defaultState The default state that will represent all grid tiles. 
     * A game will provide a variation of states to the tiles.
     */
    public GameGrid(int rows, int cols, State defaultState) throws Exception {
        if(defaultState == null) throw new Exception("default state may not be null");
        this.rows = rows;
        this.cols = cols;
        grid = new Tile[rows][cols];
        validStates = new HashSet<>();
        validStates.add(defaultState.getClass());
        this.defaultState = defaultState;
        fillGrid();
        connectGrid();
    }

    /*
     * Populate the grid array with tiles using a default
     * state for each tile.
     */
    private void fillGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Tile(i, j, defaultState);
            }
        }
    }

    /*
     * Find the up tile neighbor.
     * 
     * @param row Represents the row number.
     * @param col Represent the column number.
     * 
     * @return Tile This is the up tile neighbor if exists, else null.
     */
    private Tile findAbove(int row, int col) {
        int aboveRow = row - 1;
        if(!inRowBounds(aboveRow) || !inColBounds(col)) return null;
        return grid[aboveRow][col];
    }

    /*
     * Find the down tile neighbor.
     * 
     * @param row Represents the row number.
     * @param col Represent the column number.
     * 
     * @return Tile This is the down tile neighbor if exists, else null.
     */
    private Tile findBelow(int row, int col) {
        int belowRow = row + 1;
        if(!inRowBounds(belowRow) || !inColBounds(col)) return null;
        return grid[belowRow][col];
    }

    /*
     * Find the right tile neighbor.
     * 
     * @param row Represents the row number.
     * @param col Represent the column number.
     * 
     * @return Tile This is the right tile neighbor if exists, else null.
     */
    private Tile findRight(int row, int col) {
        int rightCol = col + 1;
        if(!inRowBounds(row) || !inColBounds(rightCol)) return null;
        return grid[row][rightCol];
    }

    /*
     * Find the left tile neighbor.
     * 
     * @param row Represents the row number.
     * @param col Represent the column number.
     * 
     * @return Tile This is the left tile neighbor if exists, else null.
     */
    private Tile findLeft(int row, int col) {
        int leftCol = col - 1;
        if(!inRowBounds(row) || !inColBounds(leftCol)) return null;
        return grid[row][leftCol];
    }

    /*
     * 4-way connect each tile to its left, right, up, down neighbors. 
     * 
     * @param row Represents the row number.
     * @param col Represent the column number.
     * 
     */
    private void connectTile(int row, int col) throws Exception {
        checkBounds(row, col);
        Tile tmpTile = grid[row][col];
        tmpTile.setRight(findRight(row, col));
        tmpTile.setLeft(findLeft(row, col));
        tmpTile.setUp(findAbove(row, col));
        tmpTile.setDown(findBelow(row, col));
    }

    /*
     * A grid is connected once each tile has a 4-way connection
     * to its left, right, up, down neighbors. 
     */
    private void connectGrid() throws Exception {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                connectTile(i, j);
            }
        }
    }

    /*
     * Get rows
     * 
     * @return int The amount of rows in the grid.
     */
    public int getRows() {
        return rows;
    }

    /*
     * Get columns.
     * 
     * @return int The amount of columns in the grid.
     */
    public int getCols() {
        return cols;
    }

    /*
     * Append a valid State to the valid states set.
     * The valid states set keeps track of the States
     * that game developers have created for their tile matching game.
     * 
     * @param state This is a State instantiated by a game developer.
     */
    public void addValidState(State state) {
        validStates.add(state.getClass());
    }

    /*
     * Check if a State is in set of valid states.
     * 
     * @param state This is a State instantiated by a game developer.
     * 
     * @return boolean True if a state is included in valid states, false otherwise.
     */
    public boolean isValidState(State state) {
        return validStates.contains(state.getClass());
    }

    /*
     * An out of bounds method that checks if a row or col
     * is within grid bounds.
     * 
     * @param upBound The upper boundary (total rows or cols) being checked.
     * @param num The specific row or col number.
     * 
     * @return True if row or col is within the bounds of 
     * row length or col length respectively, false otherwise.
     */
    private boolean outOfBounds(int upBound, int num) {
        return num >= upBound || num < 0;
    }

    /*
     * Check if row is within bounds.
     * 
     * @param row Represents the row number.
     * 
     * @return boolean True if row is within bounds, false otherwise.
     */
    private boolean inRowBounds(int row) {
        return !outOfBounds(rows, row);
    }

    /*
     * Check if column is within bounds.
     * 
     * @param col Represents the column number.
     * 
     * @return boolean True if column is within bounds, false otherwise.
     */
    private boolean inColBounds(int col) {
        return !outOfBounds(cols, col);
    }

    /*
     * Check if a tile position based on row and col
     * is within bounds of the grid.
     * 
     * @param row Represents the row number.
     * @param col Represent the column number.
     */
    private void checkBounds(int row, int col) throws Exception {
        if(!inRowBounds(row)) {
            throw new Exception("row must be less than rows and greater than 0");
        }
        if(!inColBounds(col)) {
            throw new Exception("col must be less than cols and greater than 0");
        }
    }

    /*
     * Throw an exception if a State is being used that is not a valid State.
     * Valid States are contained in the valid states set.
     * 
     * @param state This is a State instantiated by a game developer.
     */
    private void checkState(State state) throws Exception {
        if(!isValidState(state)) {
            throw new Exception("must pass a valid state into setGrid");
        }
    }

    /*
     * Set a tile and it's the row, col coordinate. 
     * 
     * @param row Represents the row number.
     * @param col Represent the column number.
     * @param tile Is the Tile that will populate a location in the grid.
     */
    public void setTile(int row, int col, Tile tile) throws Exception {
        checkBounds(row, col);
        State tileState = tile.getState();
        checkState(tileState);
        grid[row][col].setState(tileState);
    }

    /*
     * Set a tile State based on where it is found in the grid.
     * 
     * @param row Represents the row number.
     * @param col Represent the column number.
     * @param state This is a State instantiated by a game developer.
     */
    public void setState(int row, int col, State state) throws Exception {
        checkBounds(row, col);
        checkState(state);

        grid[row][col].setState(state);
    }

    /*
     * Get a specific tile based on a row, col coordinate.
     * 
     * @param row Represents the row number.
     * @param col Represent the column number.
     */
    public Tile getTile(int row, int col) throws Exception {
        if(!inRowBounds(row) || !inColBounds(col)) return null;
        return grid[row][col];
    }
    
    /*
     * Clear the grid by setting the State of each tile to default state.
     */
    public void clear() throws Exception {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                grid[i][j].setState(defaultState);
            }
        }
    }

    /*
     * Clear an entire row of tiles.
     *  
     * @param row Represents the row number.
     */
    public void clearRow(int row) throws Exception {
        checkBounds(row, 0);
        for(int i = 0; i < cols; i++) {
            grid[row][i].setState(defaultState);
        }
    }

    /*
     * Clear an enture column of tiles. 
     * 
     * @param col Represent the column number.
     */
    public void clearCol(int col) throws Exception {
        checkBounds(0, col);
        for(int i = 0; i < rows; i++) {
            grid[i][col].setState(defaultState);
        }
    }

    /*
     * Clear a Tile at a specific row, col coordinate.
     * 
     * @param row Represents the row number.
     * @param col Represent the column number.
     */
    public void clearPostion(int row, int col) throws Exception {
        checkBounds(row, col);
        grid[row][col].setState(defaultState);
    }

    /*
     * A graph search that keeps track of which Tiles will 
     * be collected based on a collection/matching condition.
     * 
     * @param tile The starting Tile for the graph search.
     * @param searchHeurisitc A search heuristic determined by the game developer
     * that will match Tiles with each other if they contain the same State.
     * 
     * @return ArrayList<Tile> The collection of matching Tiles found during traversal.
     */
    public ArrayList<Tile> graphSearch(Tile tile, SearchHeuristic searchHeuristic) {
        HashSet<Tile> visited = new HashSet<>();
        ArrayList<Tile> collected = new ArrayList<>();
        graphSearch(tile, searchHeuristic, visited, collected);
        return collected;
    }

    /*
     * Recursively find all matching Tiles along the available Tile paths.
     * 
     * @param tile The starting Tile for the graph search.
     * @param searchHeurisitc A search heuristic determined by the game developer
     * that will match Tiles with each other if they contain the same State.
     * @param visted A data structure to keep track of visited Tiles.
     * @param collected A data structure to keep track of collected Tiles.
     * 
     */
    public void graphSearch(Tile tile, SearchHeuristic searchHeuristic, Set<Tile> visited, ArrayList<Tile> collected) {
        if(visited.contains(tile)) {
            return;
        } else {
            visited.add(tile);
        }

        if(searchHeuristic.collectionCondition(tile)) {
            collected.add(tile);
        }

        ValidPaths vps = searchHeuristic.continuePath(tile);
        if(vps.getDown()) {
            graphSearch((Tile)tile.getDown(), searchHeuristic, visited, collected);
        }

        if(vps.getUp()) {
            graphSearch((Tile)tile.getUp(), searchHeuristic, visited, collected);

        }

        if(vps.getLeft()) {
            graphSearch((Tile)tile.getLeft(), searchHeuristic, visited, collected);
        }

        if(vps.getRight()) {
            graphSearch((Tile)tile.getRight(), searchHeuristic, visited, collected);
        }
    }

    /*
     * A linear search through the entire game grid.
     * 
     * @param searchHeurisitc A search heuristic determined by the game developer
     * that will match Tiles with each other if they contain the same State.
     * 
     * @return ArrayList<Tile> The collection of matching Tiles found during traversal.
     */
    public ArrayList<Tile> search(SearchHeuristic searchHeuristic) {
        ArrayList<Tile> collected = new ArrayList<>();
        for(Tile[] tileArr : grid) {
            for(Tile tile : tileArr) {
                if(searchHeuristic.collectionCondition(tile)) collected.add(tile);
            }
        }
        return collected;
    }
}
