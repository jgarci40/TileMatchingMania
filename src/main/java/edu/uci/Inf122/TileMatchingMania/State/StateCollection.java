package edu.uci.Inf122.TileMatchingMania.State;

import java.util.HashSet;
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

    public boolean hasDefaultState() {
        return defaultState != null;
    }

    public State getDefaultState() throws Exception {
        if(defaultState == null) {
            throw new Exception("Cannot request a null default state");
        }
        return defaultState;
    }

//    public List<State> getValidStates() {
//        return
//    }

    public boolean removeState(State state) {
        if(defaultState.equivalent(state)) {
            defaultState = null;
        }
        return validStates.remove(state);
    }

    public void setDefaultState(State state) {
        if(validStates.contains(defaultState)) {
            validStates.remove(defaultState);
        }
        addState(state);
        defaultState = state;
    }
}
