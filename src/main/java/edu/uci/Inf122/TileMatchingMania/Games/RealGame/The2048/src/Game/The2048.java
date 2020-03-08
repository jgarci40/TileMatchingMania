package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Game;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.CoordinateInput;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.Direction;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.KeyInput;
import edu.uci.Inf122.TileMatchingMania.Game.Game;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.Algorithms.RandomFillAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.CollectionCondition.Conditions.NeighborCondition;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.ContinuePath.Paths.NeighborPath;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.SearchAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;

import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.EmptyState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.State.EmptyBlockState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.State.FourBlockState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.State.TwoBlockState;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class The2048DefaultCollection extends StateCollection {
    public The2048DefaultCollection() throws Exception {
        super();
        setDefaultState(new TwoBlockState());
        addState(new EmptyBlockState());
        addState(new FourBlockState());
    }
}

public class The2048 extends Game {
    public static int _2048_DEFAULT_ROWS = 4;
    public static int _2048_DEFAULT_COLS = 4;

    private void checkerboardFillGrid(StateCollection stateCollection) throws Exception {
        gameGrid.fillGrid(new RandomFillAlgorithm(stateCollection));
    }

    public The2048() throws Exception {
        super(_2048_DEFAULT_ROWS, _2048_DEFAULT_COLS, new The2048DefaultCollection());
        initGame();
    }

    public boolean initGame() throws Exception {
        checkerboardFillGrid(collections.get("default"));
        return true;
    }

    public void nextInput(Input input) throws Exception {
        KeyInput ki = (KeyInput) input;
        if (ki.getDirection() == Direction.LEFT) {
            System.out.println("You pressed LEFT");
            swipeLeft();
        }
        else if (ki.getDirection() == Direction.UP) {
            System.out.println("You pressed UP");
            swipeUp();
        }
        else if (ki.getDirection() == Direction.RIGHT) {
            System.out.println("You pressed RIGHT");
            swipeRight();
        }
        else if (ki.getDirection() == Direction.DOWN) {
            System.out.println("You pressed DOWN");
            swipeDown();
        }
        else if (ki.getDirection() == Direction.INVALID) {
            System.out.println("You did not press a direction");
        }
        /*Tile startTile = gameGrid.getTile(ci.getRow(), ci.getCol());
        SearchAlgorithm sa = new SearchAlgorithm(new NeighborPath(), new NeighborCondition());
        List<Tile> matchingTiles = gameGrid.graphSearch(startTile, sa);
        // TODO: remove this print
        for (Tile t: matchingTiles) {
            System.out.println("row: " + t.getRow() + "\t" + "col: " + t.getCol());
            t.setState(new EmptyBlockState());
        }

         */
    }

    private void swipeLeft() throws Exception {
        // for every row in the grid
        for (int row = 0; row < gameGrid.getRows(); ++row) {
            for (int col = 0; col < gameGrid.getCols(); ++col) {
                for (int space = 1; space < gameGrid.getCols(); ++space) {
                    Tile t = gameGrid.getTile(row, space);
                    Tile left = (Tile) t.getLeft();
                    if (left.getState() instanceof EmptyBlockState) {
                        left.setState(t.getState());
                        t.setState(new EmptyBlockState());
                    }
                }
            }
        }
    }

    private void swipeRight() throws Exception {
        // for every row in the grid
        for (int row = 0; row < gameGrid.getRows(); ++row) {
            for (int col = 0; col < gameGrid.getCols(); ++col) {
                for (int space = gameGrid.getCols() - 2; space >= 0; --space) {
                    Tile t = gameGrid.getTile(row, space);
                    Tile right = (Tile) t.getRight();
                    if (right.getState() instanceof EmptyBlockState) {
                        right.setState(t.getState());
                        t.setState(new EmptyBlockState());
                    }
                }
            }
        }
    }

    private void swipeUp() throws Exception {
        // for every column in the grid
        for (int col = 0; col < gameGrid.getCols(); ++col) {
            ArrayList<Tile> column = gameGrid.getColumn(col);
            for (int row = 0; row < gameGrid.getRows(); ++row) {
                for (Tile tile : column) {
                    Tile down = (Tile) tile.getDown();
                    if ((tile.getState() instanceof EmptyBlockState) && (down != null)) {
                        tile.setState(down.getState());
                        down.setState(new EmptyBlockState());
                    }
                }
            }
        }
    }

    private void swipeDown() throws Exception {
        // for every column in the grid
        for (int col = 0; col < gameGrid.getCols(); ++col) {
            ArrayList<Tile> column = gameGrid.getColumn(col);
            Collections.reverse(column);
            for (int row = 0; row < gameGrid.getRows(); ++row) {
                for (Tile tile : column) {
                    Tile up = (Tile) tile.getUp();
                    if ((tile.getState() instanceof EmptyBlockState) && (up != null)) {
                        tile.setState(up.getState());
                        up.setState(new EmptyBlockState());
                    }
                }
            }
        }
    }



}
