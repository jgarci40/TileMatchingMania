package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.Generators;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.FillGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.Random;

// TODO Add tests for this code
/*
 * Support filling each tile with a random State.
 */
public class RandomGenerator extends FillGenerator {
    Random ran;

    /*
     * RandomGenerator constructor.
     * 
     * @param stateCollection Hold State objects used in the game.
     */
    public RandomGenerator(StateCollection stateCollection) {
        super(stateCollection);
        ran = new Random();
    }

    /*
     * This method will be called by a fill process. 
     * By returning a random State, each tile from the calling 
     * method will set its tile to a random State.
     * 
     * @return State A random State selected from existing States.
     */
    public State fillGenerator(Tile tile) {
        if(currStates == null) {
            updateStates();
        }
        return currStates.get(ran.nextInt(currStates.size()));
    }
}
