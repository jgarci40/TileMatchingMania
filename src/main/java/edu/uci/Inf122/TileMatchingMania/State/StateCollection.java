package edu.uci.Inf122.TileMatchingMania.State;

import java.util.List;

public class StateCollection {
    private State defaultState;
    private StateSet validStates;

    public StateCollection() {
        defaultState = null;
        validStates = new StateSet();
    }

    public boolean addState(State state) {
        return validStates.add(state);
    }

    public int size() { return validStates.size(); }

    public boolean hasDefaultState() {
        return defaultState != null;
    }

    public State getDefaultState() throws Exception {
        if(defaultState == null) {
            throw new Exception("Cannot request a null default state");
        }
        return defaultState;
    }

    public boolean removeState(State state) {
        if(state == null) return false;
        if(defaultState != null && defaultState.equivalent(state)) {
            defaultState = null;
        }
        return validStates.remove(state);
    }

    public boolean containsState(State state) {
        if(state == null) return false;
        return validStates.contains(state);
    }

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

    public boolean equals(StateCollection sc) throws Exception {
        boolean bool1 = hasDefaultState() && sc.hasDefaultState();
        if(!bool1) return false;
        boolean bool2 = defaultState.equivalent(sc.getDefaultState());
        if(!bool2) return false;
        return validStates.equals(sc.validStates);
    }

    public List<State> getValidStates() {
        return validStates.getStates();
    }
}
