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

public class GameGrid {
    private Tile[][] grid;
    private int rows;
    private int cols;
    private StateCollection stateCollection;

    public GameGrid(int rows, int cols, StateCollection sc) throws Exception {
        this.rows = rows;
        this.cols = cols;
        grid = new Tile[rows][cols];
        stateCollection = sc;

        initGrid();
        fillGrid(new DefaultFillAlgorithm(stateCollection));
        connectGrid();
    }

    private void initGrid() throws Exception {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Tile(i, j, stateCollection.getDefaultState());
            }
        }
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public void fillGrid(FillAlgorithm fillAlgorithm) throws Exception {
        fillAlgorithm.fillProcess(grid);
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

    private boolean isValidState(State state) {
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

    public ArrayList<Tile> graphSearch(Tile tile, SearchAlgorithm searchAlgorithm) {
        HashSet<Tile> visited = new HashSet<>();
        ArrayList<Tile> collected = new ArrayList<>();
        graphSearch(tile, searchAlgorithm, visited, collected);
        return collected;
    }

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
