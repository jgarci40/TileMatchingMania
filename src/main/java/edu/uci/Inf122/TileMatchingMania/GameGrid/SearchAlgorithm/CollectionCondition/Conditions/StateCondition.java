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

    /*
     * Set the State object that is used for this collection condition.
     */
    public boolean setState(State state) {
        if(state == null) return false;
        this.state = state;
        return true;
    }

    /*
     * StateCondition constructor.
     * 
     * @param state The State object that determines the game type for a tile.
     */
    public StateCondition(State state) {
        setState(state);
    }

	/*
	 * Determine whether a tile can be collected based on whether
	 * its State matches the current state in this class.
	 * 
	 * @param tile The current tile that will be evaluated based on its State.
	 * 
	 * @return True if the tile State matches the StateCondition State, false otherwise.
	 */
    public boolean collectionCondition(Tile tile) {
        if(tile.getState().equivalent(state)) {
            return true;
        }
        return false;
    }

	/*
	 * Determine whether a tile can be collected based on whether
	 * its State matches the current state in this class.
	 * 
	 * @param tile The current tile that will be evaluated based on its State.
	 * @param state A State may be specified that will be checked against the tile State.
	 * 
	 * @return True if the tile State matches the StateCondition State, false otherwise.
	 */
    public boolean collectionCondition(Tile tile, State state) {
        setState(state);
        return collectionCondition(tile);
    }
}
