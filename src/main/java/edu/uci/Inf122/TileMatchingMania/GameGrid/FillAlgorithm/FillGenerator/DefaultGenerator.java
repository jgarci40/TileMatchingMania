package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

// TODO Add tests for this code
public class DefaultGenerator extends FillGenerator {

    public DefaultGenerator(StateCollection stateCollection) {
        this.stateCollection = stateCollection;
        updateStates();
    }

    public State fillGenerator(Tile tile) throws Exception {
        if(currStates == null) {
            updateStates();
        }
        return stateCollection.getDefaultState();
    }
}
