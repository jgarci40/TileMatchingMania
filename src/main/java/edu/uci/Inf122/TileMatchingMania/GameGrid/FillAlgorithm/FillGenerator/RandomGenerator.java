package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.Random;

// TODO Add tests for this code
public class RandomGenerator extends FillGenerator {
    Random ran;

    public RandomGenerator(StateCollection stateCollection) {
        this.stateCollection = stateCollection;
        updateStates();
        ran = new Random();
    }

    public State fillGenerator(Tile tile) {
        if(currStates == null) {
            updateStates();
        }
        return currStates.get(ran.nextInt(currStates.size()));
    }
}
