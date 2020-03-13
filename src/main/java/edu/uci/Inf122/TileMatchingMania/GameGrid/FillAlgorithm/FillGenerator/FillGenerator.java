package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.List;

// TODO Add tests for this code
/*
 * Each tile in a grid will take on some State.s
 */
public abstract class FillGenerator {
    protected StateCollection stateCollection;
    protected List<State> currStates;

    /*
     * FillGenerator constructor.
     * 
     * @param stateCollection Hold State objects used in the game.
     */
    public FillGenerator(StateCollection stateCollection) {
        setStateCollection(stateCollection);
    }

    /*
     * Update all States from StateCollection's valid states. 
     */
    protected void updateStates() {
        currStates = stateCollection.getValidStates();
    }

    /*
     * Set the collection of States
     * 
     * @param stateCollection Hold State objects used in the game.
     */
    public void setStateCollection(StateCollection stateCollection) {
        this.stateCollection = stateCollection;
        updateStates();
    }

    /*
     * Each child of this class must specify the manner in which 
     * tiles take on States.
     */
    public abstract State fillGenerator(Tile tile) throws Exception;
}
