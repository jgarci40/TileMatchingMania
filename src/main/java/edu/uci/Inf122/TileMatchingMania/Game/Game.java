package edu.uci.Inf122.TileMatchingMania.Game;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.GameGrid.GameGrid;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.HashMap;
import java.util.Map;

public abstract class Game {
    protected GameGrid gameGrid;
    protected int rows;
    protected int cols;
    protected Map<String, StateCollection> collections;
    protected int score;
    protected boolean isGameOver;

    public Game(int rows, int cols, StateCollection defaultCollection) throws Exception {
        this.rows = rows;
        this.cols = cols;
        this.collections = new HashMap<>();
        this.score = 0;
        this.isGameOver = false;
        collections.put("default", defaultCollection);
        gameGrid = new GameGrid(this.rows, this.cols, collections.get("default"));
    }

    public abstract boolean initGame() throws Exception;

    public int getRows() { return rows; }

    public int getCols() { return cols; }

    public int getScore() { return score; }

    public boolean getGameOver() {return isGameOver;}

    public void setScore(int score) { this.score = score; }

    public void addScore(int delta) { this.score += delta;}

    public Tile[][] getGrid() {
        return gameGrid.getGrid();
    }

    public Map<String, StateCollection> getCollections() {
        return collections;
    }

    public abstract void nextInput(Input input) throws Exception;
}
