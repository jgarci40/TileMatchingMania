package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.Game;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.CoordinateInput;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.Game.Game;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.Algorithms.RandomFillAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.CollectionCondition.Conditions.NeighborCondition;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.CollectionCondition.Conditions.StateCondition;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.ContinuePath.Paths.NeighborPath;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.SearchAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.BlueState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.EmptyState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.GreenState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.RedState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.BlackState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.WhiteState;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.List;

class SameGameDefaultCollection extends StateCollection {
    public SameGameDefaultCollection() throws Exception {
        super();
        setDefaultState(new EmptyState());
        addState(new BlueState());
        addState(new GreenState());
        addState(new RedState());
    }
}

public class SameGame extends Game {
    public static int SAME_GAME_DEFAULT_ROWS = 10;
    public static int SAME_GAME_DEFAULT_COLS = 10;

    private void checkerboardFillGrid(StateCollection stateCollection) throws Exception {
        gameGrid.fillGrid(new RandomFillAlgorithm(stateCollection));
    }

    public SameGame() throws Exception {
        super(SAME_GAME_DEFAULT_ROWS, SAME_GAME_DEFAULT_COLS, new SameGameDefaultCollection());
        initGame();
    }

    public boolean initGame() throws Exception {
        checkerboardFillGrid(collections.get("default"));
        moveTilesDown();
        return true;
    }

    public void nextInput(Input input) throws Exception {
        CoordinateInput ci = (CoordinateInput) input;
        Tile startTile = gameGrid.getTile(ci.getRow(), ci.getCol());
        State startTileState = startTile.getState();

        SearchAlgorithm sa = new SearchAlgorithm(new NeighborPath(), new NeighborCondition());
        List<Tile> matchingTiles = gameGrid.graphSearch(startTile, sa);

        // if player did not click on an empty tile, calculate score
        if (!(startTileState.equivalent(new EmptyState()))) {
            calculateScore(matchingTiles.size());
        }

        for (Tile t: matchingTiles) {
            t.setState(new EmptyState());
        }

        moveTilesDown();
        moveTilesLeft();
    }

    private void moveTilesDown() throws Exception {
        // for every column in the grid
        for (int col = 0; col < gameGrid.getCols(); ++col) {
            for (int row = 0; row < gameGrid.getRows(); ++row) {
                // for every tile in that column
                for (int tile = 0; tile < gameGrid.getRows() - 1; ++tile) {
                    Tile t = gameGrid.getTile(tile, col);
                    Tile down = (Tile) t.getDown();
                    if (!(t.getState() instanceof EmptyState) && (down.getState() instanceof EmptyState)) {
                        down.setState(t.getState());
                        t.setState(new EmptyState());
                    }
                }
            }
        }
    }

    private void moveTilesLeft() throws Exception {
        for (int i = 0; i < gameGrid.getCols(); ++i) {
            for (int col = 1; col < gameGrid.getCols(); ++col) {
                Tile prevCol = gameGrid.getTile(gameGrid.getRows() -1, col-1);
                // if the previous column is empty, shift left
                if (prevCol.getState() instanceof EmptyState) {
                    for (int row = 0; row < gameGrid.getRows(); ++row) {
                        Tile t = gameGrid.getTile(row, col);
                        Tile left = (Tile) t.getLeft();
                        left.setState(t.getState());
                        t.setState(new EmptyState());
                    }
                }
            }
        }
    }

    private void calculateScore(int numTiles) {
        // cannot score points on 1 block
        if (numTiles <= 1) return;

        int score = (int) Math.pow((numTiles - 1), 2);
        addScore(score);
        System.out.println("Current score: " + getScore());
    }
}
