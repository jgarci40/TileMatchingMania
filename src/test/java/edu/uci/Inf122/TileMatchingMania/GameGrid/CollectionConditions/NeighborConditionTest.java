package edu.uci.Inf122.TileMatchingMania.GameGrid.CollectionConditions;

import edu.uci.Inf122.TileMatchingMania.GameGrid.GameGrid;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestState extends State {

}

class NeighborConditionTest {

    @Test
    void testAll() throws Exception {
        collectionCondition();
    }

    void collectionCondition() throws Exception {
        NeighborCondition nc = new NeighborCondition();
        int rows = 5;
        int cols = 5;
        State state = new State(){};
        State state2 = new TestState();
        GameGrid gg = new GameGrid(rows, cols, state);
        gg.addValidState(state2);

        Tile t1 = gg.getTile(1, 1);
        boolean collect = nc.collectionCondition(t1);
        assertTrue(collect);

        gg.setState(0, 1, state2);
        gg.setState(1, 0, state2);
        gg.setState(1, 2, state2);
        gg.setState(2, 1, state2);
        collect = nc.collectionCondition(t1);
        assertFalse(collect);

        gg.setState(2, 1, state);
        collect = nc.collectionCondition(t1);
        assertTrue(collect);

        gg.setState(4, 3, state2);
        Tile t2 = gg.getTile(4, 4);
        collect = nc.collectionCondition(t2);
        assertTrue(collect);

        gg.setState(3, 4, state2);
        collect = nc.collectionCondition(t2);
        assertFalse(collect);
    }
}