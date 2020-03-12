package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.Generators;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.FillGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

/*
 * Support filling each tile with default state.
 */

// TODO Add tests for this code
public class DefaultGenerator extends FillGenerator {

	/*
	 * DefaultGenerator constructor.
	 */
    public DefaultGenerator(StateCollection stateCollection) {
        super(stateCollection);
    }

    /*
     * This method will be called by a fill process. 
     * By returning a default State, each tile from the calling 
     * method will set its tile States to default.
     * 
     * @return State A default state will be returned.
     */
    public State fillGenerator(Tile tile) throws Exception {
        if(currStates == null) {
            updateStates();
        }
        return stateCollection.getDefaultState();
    }
}
