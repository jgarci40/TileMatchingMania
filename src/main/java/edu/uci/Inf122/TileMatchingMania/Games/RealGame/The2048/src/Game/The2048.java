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
import java.util.List;
import java.util.Random;

class The2048DefaultCollection extends StateCollection {
    public The2048DefaultCollection() throws Exception {
        super();
        setDefaultState(new EmptyBlockState());
        //addState(new EmptyBlockState());
        //addState(new Block4State());
        //addState(new Block8State());
        //addState(new Block16State());
        //addState(new Block32State());
        //addState(new Block64State());
        //addState(new Block128State());
        //addState(new Block256State());
        //addState(new Block512State());
        //addState(new Block1024State());
        //addState(new Block2048State());

    }
}

public class The2048 extends Game {
    public static int _2048_DEFAULT_ROWS = 4;
    public static int _2048_DEFAULT_COLS = 4;

    // Keeps track of our empty slots
    private List<Tile> emptyTiles = new ArrayList<Tile>();

    private void checkerboardFillGrid(StateCollection stateCollection) throws Exception {
        gameGrid.fillGrid(new RandomFillAlgorithm(stateCollection));
    }

    public The2048() throws Exception {
        super(_2048_DEFAULT_ROWS, _2048_DEFAULT_COLS, new The2048DefaultCollection());
        initGame();
    }

    public boolean initGame() throws Exception {
        checkerboardFillGrid(collections.get("default"));

        // Add all tiles to emptyTiles list
        loadEmptyTiles();

        generateTile();
        generateTile();

        return true;
    }

    public void nextInput(Input input) throws Exception {
        KeyInput ki = (KeyInput) input;
        if (makeMove(ki.getDirection())) {
            updateEmptyTiles();
            generateTile();
        }
    }

    private boolean makeMove(Direction d) throws Exception {
        if (d == Direction.LEFT) {
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
        else if (d == Direction.UP) {
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
        else if (d == Direction.RIGHT) {
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
        else if (d == Direction.DOWN) {
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
        else if (d == Direction.INVALID) {
            System.out.println("You did not press a direction");
            return false;
        }

        return true;
    }

    // Finds all empty grid tiles and loads them into EmptyTiles list
    private void loadEmptyTiles() {
        // Add all tiles to emptyTiles list
        for (Tile[] row: gameGrid.getGrid()) {
            for (Tile t : row) {
                if (t.getState() instanceof EmptyBlockState) {
                    emptyTiles.add(t);
                }
            }
        }
    }

    // Updates the empty tiles
    private void updateEmptyTiles() {
        emptyTiles.clear();
        loadEmptyTiles();
    }

    // Generates a 2 or 4 tile in a random empty spot
    private void generateTile() {
        // if we have empty tiles
        if (emptyTiles.size() > 0) {
            // get a random location
            int index = new Random().nextInt(emptyTiles.size());
            // 10% of random tiles are 4's, 90% are 2's
            int randomNum = new Random().nextInt(10);

            if (randomNum == 0) {
                emptyTiles.get(index).setState(new Block4State());
            }
            else {
                emptyTiles.get(index).setState(new Block2State());
            }
            // remove tile that is no longer an empty space
            emptyTiles.remove(index);
        }
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

    // merge two same tiles
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

    // moves tile state up a class i.e: 2 -> 4, 4 -> 8 and so on...
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
        else if (state instanceof Block128State) {
            t.setState(new Block256State());
        }
        else if (state instanceof Block256State) {
            t.setState(new Block512State());
        }
        else if (state instanceof Block512State) {
            t.setState(new Block1024State());
        }
        else if (state instanceof Block1024State) {
            t.setState(new Block2048State());
        }
        else {
            t.setState(new EmptyBlockState());
        }
    }

}
