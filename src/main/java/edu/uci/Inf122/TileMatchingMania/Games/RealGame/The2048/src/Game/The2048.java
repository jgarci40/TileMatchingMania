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
        boolean canMove = canSwipe(d);
        boolean merged = false;

        System.out.println("Can swipe = " + canSwipe(d));
        if (d == Direction.LEFT) {
            System.out.println("You pressed LEFT");
            swipeLeft();
            ArrayList<Tile> tiles;
            for (int row = 0; row < gameGrid.getRows(); ++row) {
                tiles = gameGrid.getRow(row);
                for (Tile tile : tiles) {
                    if (merge(tile, (Tile) tile.getRight())) {
                        merged = true;
                    }
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
                    if(merge(tile, (Tile) tile.getDown())) {
                        merged = true;
                    }
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
                    if (merge(tile, (Tile) tile.getLeft())) {
                        merged = true;
                    }
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
                    if (merge(tile, (Tile) tile.getUp())) {
                        merged = true;
                    }
                }
            }
            swipeDown();
        }
        else if (d == Direction.INVALID) {
            System.out.println("You did not press a direction");
            return false;
        }


        System.out.println("Merged = " + merged);
        return canMove || merged;
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

    private boolean swipeLeft() throws Exception {
        boolean swiped = false;
        // for every row in the grid
        for (int row = 0; row < gameGrid.getRows(); ++row) {
            for (int col = 0; col < gameGrid.getCols(); ++col) {
                for (int space = 1; space < gameGrid.getCols(); ++space) {
                    Tile t = gameGrid.getTile(row, space);
                    Tile left = (Tile) t.getLeft();
                    if (left.getState() instanceof EmptyBlockState) {
                        swiped = true;
                        left.setState(t.getState());
                        t.setState(new EmptyBlockState());
                    }
                }
            }
        }
        return swiped;
    }

    private boolean swipeRight() throws Exception {
        // for every row in the grid
        boolean swiped = false;
        for (int row = 0; row < gameGrid.getRows(); ++row) {
            for (int col = 0; col < gameGrid.getCols(); ++col) {
                for (int space = gameGrid.getCols() - 2; space >= 0; --space) {
                    Tile t = gameGrid.getTile(row, space);
                    Tile right = (Tile) t.getRight();
                    if (right.getState() instanceof EmptyBlockState) {
                        swiped = true;
                        right.setState(t.getState());
                        t.setState(new EmptyBlockState());
                    }
                }
            }
        }
        return swiped;
    }

    private boolean swipeUp() throws Exception {
        // for every column in the grid
        boolean swiped = false;
        for (int col = 0; col < gameGrid.getCols(); ++col) {
            ArrayList<Tile> column = gameGrid.getColumn(col);
            for (int row = 0; row < gameGrid.getRows(); ++row) {
                for (Tile tile : column) {
                    Tile down = (Tile) tile.getDown();
                    if ((tile.getState() instanceof EmptyBlockState) && (down != null)) {
                        swiped = true;
                        tile.setState(down.getState());
                        down.setState(new EmptyBlockState());
                    }
                }
            }
        }
        return swiped;
    }

    private boolean swipeDown() throws Exception {
        // for every column in the grid
        boolean swiped = false;
        for (int col = 0; col < gameGrid.getCols(); ++col) {
            ArrayList<Tile> column = gameGrid.getColumn(col);
            Collections.reverse(column);
            for (int row = 0; row < gameGrid.getRows(); ++row) {
                for (Tile tile : column) {
                    Tile up = (Tile) tile.getUp();
                    if ((tile.getState() instanceof EmptyBlockState) && (up != null)) {
                        swiped = true;
                        tile.setState(up.getState());
                        up.setState(new EmptyBlockState());
                    }
                }
            }
        }
        return swiped;
    }

    // merge two same tiles
    private boolean merge(Tile t1, Tile t2) {
        boolean merged = false;

        if (t1 == null || t2 == null)  {
            return false;
        }

        else if (t1.getState().equivalent(new EmptyBlockState())) {
            return false;
        }

        else if (t1.getState().equivalent(t2.getState())) {
            merged = true;
            upgradeState(t1);
            t2.setState(new EmptyBlockState());
        }
        return merged;
    }

    // moves tile state up a class i.e: 2 -> 4, 4 -> 8 and so on...
    private void upgradeState(Tile t) {
        System.out.println("IN UPGRADE STATE");
        int score = 0;
        State state = t.getState();
        if (state instanceof Block2State) {
            t.setState(new Block4State());
            score = 4;
        }
        else if (state instanceof Block4State) {
            t.setState(new Block8State());
            score = 8;
        }
        else if (state instanceof Block8State) {
            t.setState(new Block16State());
            score = 16;
        }
        else if (state instanceof Block16State) {
            t.setState(new Block32State());
            score = 32;
        }
        else if (state instanceof Block32State) {
            t.setState(new Block64State());
            score = 64;
        }
        else if (state instanceof Block64State) {
            t.setState(new Block128State());
            score = 128;
        }
        else if (state instanceof Block128State) {
            t.setState(new Block256State());
            score = 256;
        }
        else if (state instanceof Block256State) {
            t.setState(new Block512State());
            score = 512;
        }
        else if (state instanceof Block512State) {
            t.setState(new Block1024State());
            score = 1024;
        }
        else if (state instanceof Block1024State) {
            t.setState(new Block2048State());
            score = 2048;
        }
        else {
            t.setState(new EmptyBlockState());
        }

        addScore(score);
        System.out.println("Current score: " + getScore());
    }

    private boolean canSwipe(Direction d) {
        updateEmptyTiles();
        if (d == Direction.RIGHT) {
            for (Tile tile: emptyTiles) {
                Tile left = (Tile) tile.getLeft();
                if (left != null && !(left.getState().equivalent(new EmptyBlockState())))
                    return true;
            }
        }
        else if (d == Direction.LEFT) {
            for (Tile tile: emptyTiles) {
                Tile right = (Tile) tile.getRight();
                if (right != null && !(right.getState().equivalent(new EmptyBlockState())))
                    return true;
            }
        }
        else if (d == Direction.UP) {
            for (Tile tile: emptyTiles) {
                Tile down = (Tile) tile.getDown();
                if (down != null && !(down.getState().equivalent(new EmptyBlockState())))
                    return true;
            }
        }
        else if (d == Direction.DOWN) {
            for (Tile tile: emptyTiles) {
                Tile up = (Tile) tile.getUp();
                if (up != null && !(up.getState().equivalent(new EmptyBlockState())))
                    return true;
            }
        }

        return false;
    }

}
