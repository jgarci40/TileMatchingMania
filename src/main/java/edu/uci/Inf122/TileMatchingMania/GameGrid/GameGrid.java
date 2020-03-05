package edu.uci.Inf122.TileMatchingMania.GameGrid;

import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchHeuristics.SearchHeuristic;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameGrid {
    private Tile[][] grid;
    private int rows;
    private int cols;
    private StateCollection stateCollection;

    public GameGrid(int rows, int cols, State defaultState) throws Exception {
        if(defaultState == null) throw new Exception("default state may not be null");
        this.rows = rows;
        this.cols = cols;
        grid = new Tile[rows][cols];

        stateCollection = new StateCollection();
        stateCollection.setDefaultState(defaultState);

        fillGrid();
        connectGrid();
    }

    private void fillGrid() throws Exception {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Tile(i, j, stateCollection.getDefaultState());
            }
        }
    }

    private Tile findAbove(int row, int col) {
        int aboveRow = row - 1;
        if(!inRowBounds(aboveRow) || !inColBounds(col)) return null;
        return grid[aboveRow][col];
    }

    private Tile findBelow(int row, int col) {
        int belowRow = row + 1;
        if(!inRowBounds(belowRow) || !inColBounds(col)) return null;
        return grid[belowRow][col];
    }

    private Tile findRight(int row, int col) {
        int rightCol = col + 1;
        if(!inRowBounds(row) || !inColBounds(rightCol)) return null;
        return grid[row][rightCol];
    }

    private Tile findLeft(int row, int col) {
        int leftCol = col - 1;
        if(!inRowBounds(row) || !inColBounds(leftCol)) return null;
        return grid[row][leftCol];
    }

    private void connectTile(int row, int col) throws Exception {
        checkBounds(row, col);
        Tile tmpTile = grid[row][col];
        tmpTile.setRight(findRight(row, col));
        tmpTile.setLeft(findLeft(row, col));
        tmpTile.setUp(findAbove(row, col));
        tmpTile.setDown(findBelow(row, col));
    }

    private void connectGrid() throws Exception {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                connectTile(i, j);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void addValidState(State state) {
        stateCollection.addState(state);
    }

    public boolean isValidState(State state) {
        return stateCollection.containsState(state);
    }

    private boolean outOfBounds(int upBound, int num) {
        return num >= upBound || num < 0;
    }

    private boolean inRowBounds(int row) {
        return !outOfBounds(rows, row);
    }

    private boolean inColBounds(int col) {
        return !outOfBounds(cols, col);
    }

    private void checkBounds(int row, int col) throws Exception {
        if(!inRowBounds(row)) {
            throw new Exception("row must be less than rows and greater than 0");
        }
        if(!inColBounds(col)) {
            throw new Exception("col must be less than cols and greater than 0");
        }
    }

    private void checkState(State state) throws Exception {
        if(!isValidState(state)) {
            throw new Exception("must pass a valid state into setGrid");
        }
    }

    public void setTile(int row, int col, Tile tile) throws Exception {
        checkBounds(row, col);
        State tileState = tile.getState();
        checkState(tileState);
        grid[row][col].setState(tileState);
    }

    public void setState(int row, int col, State state) throws Exception {
        checkBounds(row, col);
        checkState(state);

        grid[row][col].setState(state);
    }

    public Tile getTile(int row, int col) throws Exception {
        if(!inRowBounds(row) || !inColBounds(col)) return null;
        return grid[row][col];
    }

    public void clear() throws Exception {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                grid[i][j].setState(stateCollection.getDefaultState());
            }
        }
    }

    public void clearRow(int row) throws Exception {
        checkBounds(row, 0);
        for(int i = 0; i < cols; i++) {
            grid[row][i].setState(stateCollection.getDefaultState());
        }
    }

    public void clearCol(int col) throws Exception {
        checkBounds(0, col);
        for(int i = 0; i < rows; i++) {
            grid[i][col].setState(stateCollection.getDefaultState());
        }
    }

    public void clearPostion(int row, int col) throws Exception {
        checkBounds(row, col);
        grid[row][col].setState(stateCollection.getDefaultState());
    }

    public ArrayList<Tile> graphSearch(Tile tile, SearchHeuristic searchHeuristic) {
        HashSet<Tile> visited = new HashSet<>();
        ArrayList<Tile> collected = new ArrayList<>();
        graphSearch(tile, searchHeuristic, visited, collected);
        return collected;
    }

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
