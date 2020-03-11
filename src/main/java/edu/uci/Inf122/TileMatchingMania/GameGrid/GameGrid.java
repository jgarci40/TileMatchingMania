package edu.uci.Inf122.TileMatchingMania.GameGrid;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.Algorithms.DefaultFillAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.Generators.DefaultGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.FillGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess.FillProcess;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess.Processes.LinearFillProcess;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.SearchAlgorithm;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//TODO replace two-D arrays with collections and return unmodifiable collections

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
    private StateCollection stateCollection;

    /*
     * GameGrid constructor.
     * 
     * @param rows The total rows in 2D grid.
     * @param cols The total columns in 2D grid.
     * @param sc A collection of States that will be used in when comparing/changing. tiles.
     */
    public GameGrid(int rows, int cols, StateCollection sc) throws Exception {
        this.rows = rows;
        this.cols = cols;
        grid = new Tile[rows][cols];
        stateCollection = sc;

        initGrid();
        fillGrid(new DefaultFillAlgorithm(stateCollection));
        connectGrid();
    }


    /*
     * Populate the grid array with tiles using a default
     * state for each tile.
     */
    private void initGrid() throws Exception {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Tile(i, j, stateCollection.getDefaultState());
            }
        }
    }

    /*
     * Get grid.
     * 
     * @return Tile[][] The game grid which is a 2D array.
     */
    public Tile[][] getGrid() {
        return grid;
    }

    
    /*
     * Fill a grid based on the specified fill algorithm.
     * 
     * @param fillAlgorithm The specific type of algorithm that will populate a 
     * grid in some manner.
     */
    public void fillGrid(FillAlgorithm fillAlgorithm) throws Exception {
        fillAlgorithm.fillProcess(grid);
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
     * Get a specific row from the grid.
     * 
     * @param row The specific row in grid
     * 
     * @return ArrayList<Tile> A list of tiles which represents the row.
     */
    public ArrayList<Tile> getRow(int row) throws Exception {
        if (!inRowBounds(row)) {
            throw new Exception("Row number must be between 0 and " + (rows-1));
        }
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (Tile tile: grid[row]) {
            tiles.add(tile);
        }

        return tiles;
    }

    /*
     * Get a specific column from the grid.
     * 
     * @param row The specific column in grid
     * 
     * @return ArrayList<Tile> A list of tiles which represents the column.
     */
    public ArrayList<Tile> getColumn(int col) throws Exception {
        if (!inColBounds(col)) {
            throw new Exception("Column number must be between 0 and " + (cols-1));
        }
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int row = 0; row < rows; ++row) {
            tiles.add(grid[row][col]);
        }

        return tiles;
    }

    /*
     * Check if a State is in set of valid states.
     * 
     * @param state This is a State instantiated by a game developer.
     * 
     * @return boolean True if a state is included in valid states, false otherwise.
     */
    private boolean isValidState(State state) {
        return stateCollection.containsState(state);
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
                grid[i][j].setState(stateCollection.getDefaultState());
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
            grid[row][i].setState(stateCollection.getDefaultState());
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
            grid[i][col].setState(stateCollection.getDefaultState());
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
        grid[row][col].setState(stateCollection.getDefaultState());
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
    public ArrayList<Tile> graphSearch(Tile tile, SearchAlgorithm searchAlgorithm) {
        HashSet<Tile> visited = new HashSet<>();
        ArrayList<Tile> collected = new ArrayList<>();
        graphSearch(tile, searchAlgorithm, visited, collected);
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
    public void graphSearch(Tile tile, SearchAlgorithm searchAlgorithm, Set<Tile> visited, ArrayList<Tile> collected) {
        if(visited.contains(tile)) {
            return;
        } else {
            visited.add(tile);
        }

        if(searchAlgorithm.collectionCondition(tile)) {
            collected.add(tile);
        }

        ValidPaths vps = searchAlgorithm.continuePath(tile);
        if(vps.getDown()) {
            graphSearch((Tile)tile.getDown(), searchAlgorithm, visited, collected);
        }

        if(vps.getUp()) {
            graphSearch((Tile)tile.getUp(), searchAlgorithm, visited, collected);

        }

        if(vps.getLeft()) {
            graphSearch((Tile)tile.getLeft(), searchAlgorithm, visited, collected);
        }

        if(vps.getRight()) {
            graphSearch((Tile)tile.getRight(), searchAlgorithm, visited, collected);
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
    public ArrayList<Tile> search(SearchAlgorithm searchAlgorithm) {
        ArrayList<Tile> collected = new ArrayList<>();
        for(Tile[] tileArr : grid) {
            for(Tile tile : tileArr) {
                if(searchAlgorithm.collectionCondition(tile)) collected.add(tile);
            }
        }
        return collected;
    }
}
