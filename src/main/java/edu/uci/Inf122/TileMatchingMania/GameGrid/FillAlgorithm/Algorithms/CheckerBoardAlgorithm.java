package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.Algorithms;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.FillGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.Generators.DefaultGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess.Processes.LinearFillProcess;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.List;

class CheckerBoardGenerator extends FillGenerator {
    List<State> states;
    public CheckerBoardGenerator(StateCollection stateCollection) throws Exception {
        super(stateCollection);
        if(stateCollection.size() != 2) {
            throw new Exception("CheckerBoardGenerator must be passed a StateCollection with only two states");
        }
        states = stateCollection.getValidStates();
    }

    public State fillGenerator(Tile tile) {
        if(currStates == null) updateStates();

        if(!tile.hasLeft() && !tile.hasUp()) {
            return states.get(0);
        } else if(tile.hasLeft() && !tile.hasUp()) {
            return toggle((Tile)tile.getLeft());
        } else if(!tile.hasLeft() && tile.hasUp()) {
            return toggle((Tile)tile.getUp());
        }
        return toggle((Tile)tile.getLeft());
    }

    private State toggle(Tile tile) {
        return toggle(tile.getState());
    }

    private State toggle(State state) {
        State state0 = states.get(0);
        State state1 = states.get(1);
        if(state.equivalent(state0)) {
            return state1;
        } else {
            return state0;
        }
    }
}

public class CheckerBoardAlgorithm extends FillAlgorithm {
    public CheckerBoardAlgorithm(StateCollection stateCollection) throws Exception {
        super(new LinearFillProcess(stateCollection), new CheckerBoardGenerator(stateCollection));
    }
}
