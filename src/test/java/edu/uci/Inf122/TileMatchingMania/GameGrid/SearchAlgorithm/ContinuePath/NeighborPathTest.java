package edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.ContinuePath;

import edu.uci.Inf122.TileMatchingMania.GameGrid.GameGrid;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.ContinuePath.Paths.NeighborPath;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.GameGrid.ValidPaths;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;
import edu.uci.Inf122.TileMatchingMania.State.TestState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeighborPathTest {

    @Test
    void testAll() throws Exception {
        continuePaths();
    }

    void continuePaths() throws Exception {
        NeighborPath np = new NeighborPath();
        int rows = 5;
        int cols = 5;
        State state = new State(){};
        State state2 = new TestState();

        StateCollection sc = new StateCollection();
        sc.setDefaultState(state);
        GameGrid gg = new GameGrid(rows, cols, sc);
        sc.addState(state2);

        Tile t1 = gg.getTile(1, 1);
        ValidPaths vps = np.continuePaths(t1);
        assertTrue(vps.getDown());
        assertTrue(vps.getLeft());
        assertTrue(vps.getRight());
        assertTrue(vps.getUp());

        gg.setState(0, 1, state2);
        gg.setState(1, 0, state2);
        gg.setState(1, 2, state2);
        gg.setState(2, 1, state2);
        vps = np.continuePaths(t1);
        assertFalse(vps.getDown());
        assertFalse(vps.getLeft());
        assertFalse(vps.getRight());
        assertFalse(vps.getUp());

        gg.setState(2, 1, state);
        vps = np.continuePaths(t1);
        assertTrue(vps.getDown());
        assertFalse(vps.getLeft());
        assertFalse(vps.getRight());
        assertFalse(vps.getUp());
    }
}