package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.Generators;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.FillGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

/*
 * Fill each tile with default state.
 */

// TODO Add tests for this code
public class DefaultGenerator extends FillGenerator {

    public DefaultGenerator(StateCollection stateCollection) {
        super(stateCollection);
    }

    public State fillGenerator(Tile tile) throws Exception {
        if(currStates == null) {
            updateStates();
        }
        return stateCollection.getDefaultState();
    }
}
