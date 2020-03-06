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

    public Game(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.collections = new HashMap<>();
        this.score = 0;
    }

    public abstract boolean initGame();

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public Tile[][] getGrid() {
        return gameGrid.getGrid();
    }

    public abstract void nextInput(Input input);
}
