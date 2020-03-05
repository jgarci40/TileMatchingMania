package edu.uci.Inf122.TileMatchingMania.GameGrid;

import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.NeighborAlgorithm;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;
import edu.uci.Inf122.TileMatchingMania.State.TestState;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestTile extends Tile {
    public TestTile(int row, int col) {
        super(row, col);
        currentState = new TestState();
        this.row = row;
        this.col = col;
    }
}

class GameGridTest {

    @Test
    void runAll() throws Exception {
        testGetRows();
        testGetCols();
        testGetTile();
        testSetState();
        testGridConnections();
        testClearRow();
        testClearCol();
        testClearPos();
        testClear();
        testGraphSearch1();
        testGraphSearch2();
        testGraphSearch3();
        testSearch1();
        testSearch2();
    }

    void testGetRows() throws Exception {
        int rows = 5;
        int cols = 5;
        StateCollection sc = new StateCollection();
        State state = new State(){};
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        assertEquals(rows, gg.getRows());
    }

    void testGetCols() throws Exception {
        int rows = 5;
        int cols = 5;
        StateCollection sc = new StateCollection();
        State state = new State(){};
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        assertEquals(cols, gg.getCols());
    }

    void testGetTile() throws Exception {
        int rows = 5;
        int cols = 5;
        StateCollection sc = new StateCollection();
        State state = new State(){};
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        Tile t1 = gg.getTile(4, 1);
        assertNotNull(t1);

        TestTile tt = new TestTile(1, 1);
        sc.addState(tt.getState());
        gg.setTile(1, 1, tt);
        assertEquals(tt.getState(), gg.getTile(1, 1).getState());

        TestTile t2 = new TestTile(1, 1);
    }

    void testSetState() throws Exception {
        int rows = 5;
        int cols = 5;
        StateCollection sc = new StateCollection();
        State state = new State(){};
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);

        State state2 = new State(){};
        sc.addState(state2);
        gg.setState(1, 1, state2);
        assertNotEquals(state, gg.getTile(1, 1).getState());
        assertEquals(state2, gg.getTile(1, 1).getState());
    }

    void testGridConnections() throws Exception {
        int rows = 5;
        int cols = 5;

        StateCollection sc = new StateCollection();
        State state = new State(){};
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);

        Tile t11 = gg.getTile(1, 1);
        Tile t12 = gg.getTile(1, 2);
        Tile t13 = gg.getTile(1, 3);
        Tile t23 = gg.getTile(2, 3);
        Tile t24 = gg.getTile(2, 4);
        Tile t25 = gg.getTile(2, 5);
        Tile t14 = gg.getTile(1, 4);

        assertEquals(t11.getRight(), t12);
        assertEquals(t11.getRight().getRight(), t13);
        assertNotEquals(t11.getRight().getRight().getDown(), t13);
        assertEquals(t11.getRight().getRight().getDown(), t23);
        assertEquals(t11.getRight().getRight().getDown().getRight(), t24);
        assertEquals(t11.getRight().getRight().getDown().getRight().getRight(), t25);
        assertEquals(t25, null);
        assertEquals(t11.getRight().getRight().getDown().getRight().getUp(), t14);

        Tile t44 = gg.getTile(4, 4);
        Tile t34 = gg.getTile(3, 4);

        assertEquals(t44.getUp(), t34);
    }

    void testClearRow() throws Exception {
        int rows = 5;
        int cols = 5;
        State state = new State() {};
        State state2 = new State(){};

        StateCollection sc = new StateCollection();
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        sc.addState(state2);

        gg.setState(0, 1, state2);
        assertEquals(gg.getTile(0,1).getState(), state2);
        gg.setState(0, 2, state2);
        assertEquals(gg.getTile(0,2).getState(), state2);

        gg.clearRow(0);
        assertEquals(gg.getTile(0,1).getState(), state);
        assertEquals(gg.getTile(0,2).getState(), state);
    }

    void testClearCol() throws Exception {
        int rows = 5;
        int cols = 5;
        State state = new State() {};
        State state2 = new State(){};

        StateCollection sc = new StateCollection();
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        sc.addState(state2);

        gg.setState(1, 1, state2);
        assertEquals(gg.getTile(1,1).getState(), state2);
        gg.setState(2, 1, state2);
        assertEquals(gg.getTile(2,1).getState(), state2);

        gg.clearCol(1);
        assertEquals(gg.getTile(1,1).getState(), state);
        assertEquals(gg.getTile(2,1).getState(), state);
    }

    void testClearPos() throws Exception {
        int rows = 5;
        int cols = 5;
        State state = new State() {};
        State state2 = new State(){};

        StateCollection sc = new StateCollection();
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        sc.addState(state2);

        gg.setState(1, 1, state2);
        assertEquals(gg.getTile(1,1).getState(), state2);
        gg.setState(2, 1, state2);
        assertEquals(gg.getTile(2,1).getState(), state2);

        gg.clearPostion(1, 1);
        gg.clearPostion(2, 1);
        assertEquals(gg.getTile(1,1).getState(), state);
        assertEquals(gg.getTile(2,1).getState(), state);
    }

    void testClear() throws Exception {
        int rows = 5;
        int cols = 5;
        State state = new State() {};
        State state2 = new State(){};

        StateCollection sc = new StateCollection();
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        sc.addState(state2);

        gg.setState(1, 1, state2);
        assertEquals(gg.getTile(1,1).getState(), state2);
        gg.setState(2, 1, state2);
        assertEquals(gg.getTile(2,1).getState(), state2);
        gg.setState(0, 1, state2);
        assertEquals(gg.getTile(0,1).getState(), state2);
        gg.setState(0, 2, state2);
        assertEquals(gg.getTile(0,2).getState(), state2);

        gg.clear();
        assertEquals(gg.getTile(1,1).getState(), state);
        assertEquals(gg.getTile(2,1).getState(), state);
        assertEquals(gg.getTile(0,1).getState(), state);
        assertEquals(gg.getTile(0,2).getState(), state);
    }

    void testGraphSearch1() throws Exception {
        int rows = 5;
        int cols = 5;
        State state = new State() {};
        State state2 = new State() {};

        StateCollection sc = new StateCollection();
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        sc.addState(state2);

        Tile tile = gg.getTile(0,1);
        NeighborAlgorithm nh = new NeighborAlgorithm();
        ArrayList<Tile> tiles = gg.graphSearch(tile, nh);
        assertEquals(tiles.size(), 25);

        gg.setState(0,0, state2);
        tiles = gg.graphSearch(tile, nh);
        assertEquals(tiles.size(), 24);
    }

    void testGraphSearch2() throws Exception {
        int rows = 5;
        int cols = 5;
        State state = new State() {};
        State state2 = new State() {};
        StateCollection sc = new StateCollection();
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        sc.addState(state2);

        gg.setState(0,0, state2);
        gg.setState(0,1, state2);
        gg.setState(0,2, state2);
        gg.setState(0,3, state2);
        gg.setState(0,4, state2);

        Tile tile = gg.getTile(0,1);

        NeighborAlgorithm nh = new NeighborAlgorithm();
        ArrayList<Tile> tiles = gg.graphSearch(tile, nh);
        assertEquals(tiles.size(), 5);
    }

    void testGraphSearch3() throws Exception {
        int rows = 5;
        int cols = 5;
        State state = new State() {};
        State state2 = new State() {};
        StateCollection sc = new StateCollection();
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        sc.addState(state2);

        gg.setState(2,3, state2);
        gg.setState(2,1, state2);
        gg.setState(2,2, state2);
        gg.setState(3,2, state2);
        gg.setState(1,3, state2);

        Tile tile = gg.getTile(2,1);

        NeighborAlgorithm nh = new NeighborAlgorithm();
        ArrayList<Tile> tiles = gg.graphSearch(tile, nh);
        assertEquals(tiles.size(), 5);

        Tile tile2 = gg.getTile(0,0);
        tiles = gg.graphSearch(tile2, nh);
        assertEquals(tiles.size(), 20);
    }

    void testSearch1() throws Exception {
        int rows = 5;
        int cols = 5;
        State state = new State() {};
        State state2 = new State() {};
        StateCollection sc = new StateCollection();
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        sc.addState(state2);

        gg.setState(2,3, state2);
        gg.setState(2,1, state2);
        gg.setState(2,2, state2);
        gg.setState(3,2, state2);
        gg.setState(1,3, state2);

        NeighborAlgorithm nh = new NeighborAlgorithm();
        ArrayList<Tile> tiles = gg.search(nh);
        assertEquals(tiles.size(), 25);
    }

    void testSearch2() throws Exception {
        int rows = 5;
        int cols = 5;
        State state = new State() {};
        State state2 = new State() {};
        StateCollection sc = new StateCollection();
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        sc.addState(state2);

        gg.setState(4,3, state2);
        gg.setState(3,4, state2);

        NeighborAlgorithm nh = new NeighborAlgorithm();
        ArrayList<Tile> tiles = gg.search(nh);
        assertEquals(tiles.size(), 22);

        gg.setState(3,3, state2);

        tiles = gg.search(nh);
        assertEquals(tiles.size(), 24);
    }
}