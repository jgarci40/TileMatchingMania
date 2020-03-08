package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Game;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.Direction;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.KeyInput;
import edu.uci.Inf122.TileMatchingMania.Game.Game;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.Algorithms.RandomFillAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;

import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.State.*;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.ArrayList;
import java.util.Collections;

class The2048DefaultCollection extends StateCollection {
    public The2048DefaultCollection() throws Exception {
        super();
        setDefaultState(new Block2State());
        addState(new EmptyBlockState());
        //addState(new Block4State());
        //addState(new Block8State());
        //addState(new Block16State());

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
            ArrayList<Tile> tiles;
            for (int row = 0; row < gameGrid.getRows(); ++row) {
                tiles = gameGrid.getRow(row);
                for (Tile tile : tiles) {
                    merge(tile, (Tile) tile.getRight());
                }
            }
            swipeLeft();
        }
        else if (ki.getDirection() == Direction.UP) {
            System.out.println("You pressed UP");
            swipeUp();
            ArrayList<Tile> tiles;
            for (int col = 0; col < gameGrid.getCols(); ++col) {
                tiles = gameGrid.getColumn(col);
                for (Tile tile : tiles) {
                    merge(tile, (Tile) tile.getDown());
                }
            }
            swipeUp();
        }
        else if (ki.getDirection() == Direction.RIGHT) {
            System.out.println("You pressed RIGHT");
            swipeRight();
            ArrayList<Tile> tiles;
            for (int row = 0; row < gameGrid.getRows(); ++row) {
                tiles = gameGrid.getRow(row);
                Collections.reverse(tiles);
                for (Tile tile : tiles) {
                    merge(tile, (Tile) tile.getLeft());
                }
            }
            swipeRight();
        }
        else if (ki.getDirection() == Direction.DOWN) {
            System.out.println("You pressed DOWN");
            swipeDown();
            ArrayList<Tile> tiles;
            for (int col = 0; col < gameGrid.getCols(); ++col) {
                tiles = gameGrid.getColumn(col);
                Collections.reverse(tiles);
                for (Tile tile : tiles) {
                    merge(tile, (Tile) tile.getUp());
                }
            }
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

    private void merge(Tile t1, Tile t2) {
        System.out.println("IN MERGE");
        if (t1 == null || t2 == null)  {
            return;
        }

        if (t1.getState().equivalent(t2.getState())) {
            upgradeState(t1);
            t2.setState(new EmptyBlockState());
        }
    }

    private void upgradeState(Tile t) {
        System.out.println("IN UPGRADE STATE");
        State state = t.getState();
        if (state instanceof Block2State) {
            t.setState(new Block4State());
        }
        else if (state instanceof Block4State) {
            t.setState(new Block8State());
        }
        else if (state instanceof Block8State) {
            t.setState(new Block16State());
        }
        else if (state instanceof Block16State) {
            t.setState(new Block32State());
        }
        else if (state instanceof Block32State) {
            t.setState(new Block64State());
        }
        else if (state instanceof Block64State) {
            t.setState(new Block128State());
        }
        else {
            t.setState(new EmptyBlockState());
        }
    }

}
