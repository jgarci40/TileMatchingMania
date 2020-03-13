package edu.uci.Inf122.TileMatchingMania.State;

import java.util.List;

public class StateCollection {
    private State defaultState;
    private StateSet validStates;

    /*
     * StateCollection constructor.
     */
    public StateCollection() {
        defaultState = null;
        validStates = new StateSet();
    }

    /*
     * Add a State object to the collection.
     */
    public boolean addState(State state) {
        return validStates.add(state);
    }

    /*
     * Get the size of the total States in the collection.
     * 
     * @return int The amount of State objects.
     */
    public int size() { return validStates.size(); }

    /*
     * Determine if there is a default State provided.
     * 
     * @return boolean True if there was a default State provided, false otherwise.
     */
    public boolean hasDefaultState() {
        return defaultState != null;
    }

    /*
     * Get the default State.
     * 
     * @return State The default state will be returned.
     */
    public State getDefaultState() throws Exception {
        if(defaultState == null) {
            throw new Exception("Cannot request a null default state");
        }
        return defaultState;
    }

    /*
     * Remove a State from the collection.
     * 
     * @param state A State object that will be removed from the collection.
     * 
     * @return boolean True if State is removed, false otherwise.
     */
    public boolean removeState(State state) {
        if(state == null) return false;
        if(defaultState != null && defaultState.equivalent(state)) {
            defaultState = null;
        }
        return validStates.remove(state);
    }

    /*
     * Determine whether a State exists in the collection
     * 
     * @param state A State object that will be looked up in a set.
     * 
     * @return boolean True if collection contains the State, false otherwise.
     */
    public boolean containsState(State state) {
        if(state == null) return false;
        return validStates.contains(state);
    }

    /*
     * Set the default state.
     * 
     * @param state A State object that will serve as the default.
     */
    public void setDefaultState(State state) throws Exception {
        if(state == null) {
            throw new Exception("Cannot set a null default state");
        }
        if(validStates.contains(defaultState)) {
            validStates.remove(defaultState);
        }
        addState(state);
        defaultState = state;
    }

    /*
     * Determine if two collections are the same.
     * 
     * @param sc The collection being compared against.
     * 
     * @return boolean True if the collections are the same, false otherwise.
     */
    public boolean equals(StateCollection sc) throws Exception {
        boolean bool1 = hasDefaultState() && sc.hasDefaultState();
        if(!bool1) return false;
        boolean bool2 = defaultState.equivalent(sc.getDefaultState());
        if(!bool2) return false;
        return validStates.equals(sc.validStates);
    }

    /*
     * Get the list of valid states.
     * 
     * @return List<State> A list of States in the valid set.
     */
    public List<State> getValidStates() {
        return validStates.getStates();
    }
}
