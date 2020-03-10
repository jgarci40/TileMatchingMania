package edu.uci.Inf122.TileMatchingMania.State;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StateSet {
    private Set<Class> validStates;

    public StateSet() {
        validStates = new HashSet<>();
    }

    public int size() {
        return validStates.size();
    }

    private Class stateClass(State state) {
        return state.getClass();
    }

    public boolean add(State state) {
        if(state == null) return false;
        if(validStates.contains(stateClass(state))) {
            return false;
        } else {
            validStates.add(stateClass(state));
            return true;
        }
    }

    public boolean remove(State state) {
        if(validStates.contains(stateClass(state))) {
            validStates.remove(stateClass(state));
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(State state) {
        if(state == null) return false;
        return validStates.contains(stateClass(state));
    }

    public boolean equals(StateSet ss) {
        return validStates.equals(ss.validStates);
    }

    /*
     * This method will crash if State classes exist only locally in classes
     * or functions.
     */
    public List<State> getStates() {
        return validStates.stream().map(e -> {
            try {
                return (State)e.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }
}
