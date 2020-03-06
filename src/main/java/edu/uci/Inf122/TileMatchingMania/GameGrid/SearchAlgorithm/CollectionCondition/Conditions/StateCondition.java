package edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.CollectionCondition.Conditions;

import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.CollectionCondition.CollectionCondition;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.State;

/*
 * This class collects tiles based on a neighborhood condition;
 * meaning Tiles are matched with each other if they have the 
 * same State and are left, right, up, or down neighbors. 
 */
public class StateCondition extends CollectionCondition {
    private State state;

    public boolean setState(State state) {
        if(state == null) return false;
        this.state = state;
        return true;
    }

    public StateCondition(State state) {
        setState(state);
    }

	/*
	 * Collect the 4 neighboring tiles if the State for each
	 * Title is the same as the starting tile.
	 * 
	 * @param tile This is the current Tile evaluating it's 4 neighbors.
	 * 
	 * @return boolean False if no match candidate exists, true otherwise
	 * if 1 of the 4 neighbors are a match. 
	 */
    public boolean collectionCondition(Tile tile) {
        if(tile.getState().equivalent(state)) {
            return true;
        }
        return false;
    }

    public boolean collectionCondition(Tile tile, State state) {
        setState(state);
        return collectionCondition(tile);
    }
}
