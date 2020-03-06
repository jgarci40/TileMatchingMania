package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.List;

// TODO Add tests for this code
public abstract class FillGenerator {
    StateCollection stateCollection;
    List<State> currStates;
    void updateStates() {
        currStates = stateCollection.getValidStates();
    }

    public void setStateCollection(StateCollection stateCollection) {
        this.stateCollection = stateCollection;
        updateStates();
    }

    public abstract State fillGenerator(Tile tile) throws Exception;
}
