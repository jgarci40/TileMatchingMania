package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.List;
import java.util.Random;

// TODO Add tests for this code
public class RandomGenerator extends FillGenerator {
    StateCollection stateCollection;
    Random ran;

    public RandomGenerator(StateCollection stateCollection) {
        this.stateCollection = stateCollection;
        ran = new Random();
    }

    public void setStateCollection(StateCollection stateCollection) {
        this.stateCollection = stateCollection;
    }

    public State fillGenerator(Tile tile) {
        List<State> states = stateCollection.getValidStates();
        return states.get(ran.nextInt(states.size()));
    }
}
