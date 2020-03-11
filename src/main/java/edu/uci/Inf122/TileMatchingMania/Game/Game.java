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

    /*
     * Game constructor.
     * 
     * @param rows The total rows found in GameGrid.
     * @param cols The total columns found in GameGrid.
     * @defaultCollection A placeholder for State objects which contains null State as default.
     */
    public Game(int rows, int cols, StateCollection defaultCollection) throws Exception {
        this.rows = rows;
        this.cols = cols;
        this.collections = new HashMap<>();
        this.score = 0;
        this.isGameOver = false;
        collections.put("default", defaultCollection);
        gameGrid = new GameGrid(this.rows, this.cols, collections.get("default"));
    }

    /*
     * Child objects will have to specify instantiation actions when a 
     * game is declared. Typically, a constructor in a game will call this method.
     */
    public abstract boolean initGame() throws Exception;

    /*
     * Get the total rows in the GameGrid.
     * 
     * @return int The total rows in GameGrid.
     */
    public int getRows() { return rows; }

    /*
     * Get the total columns in the GameGrid.
     * 
     * @return int The total columns in GameGrid.
     */
    public int getCols() { return cols; }

    /*
     * Get the score for a game.
     * 
     * @return int A game score.
     */
    public int getScore() { return score; }

    /*
     * Determine if game is over.
     * 
     * @return boolean True if game is over, false otherwise.
     */
    public boolean getGameOver() {return isGameOver;}

    /*
     * Set the score for a game.
     * 
     * @param score A game designer will determine starting score.
     */
    public void setScore(int score) { this.score = score; }

    /*
     * Change the score based on a delta because a score may increase
     * or decrease depending on the type of scoring and game design.
     * 
     * @param delta Positive or negative change to player score.
     */
    public void addScore(int delta) { this.score += delta;}

    /*
     * Get the game grid which is a 2D array of Tile objects.
     * 
     * @return Tile[][] The game's 2D array containing all Tile objects. 
     */
    public Tile[][] getGrid() {
        return gameGrid.getGrid();
    }

    /*
     * Get the collection of State objects for a game which are 
     * all associated with a label. A more complex game may have multiple 
     * StateCollection objects.
     * 
     * @return Map<String, StateCollection> A map that contains a StateCollection mapped to a label.
     */
    public Map<String, StateCollection> getCollections() {
        return collections;
    }

    /*
     * Call this method every time a Game needs to advance to a next state.
     * The game logic to make changes to a game's state (changing Tile States, adjusting 
     * scores, determining if game is over) will go here. 
     * This should be called before rendering new GUI content.
     */
    public abstract void nextInput(Input input) throws Exception;
}
