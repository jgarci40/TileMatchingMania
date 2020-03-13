package edu.uci.Inf122.TileMatchingMania.State;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StateSet {
    private Set<Class> validStates;

    /*
     * StateSet constructor.
     */
    public StateSet() {
        validStates = new HashSet<>();
    }

    /*
     * Get the size of the State set.
     * 
     * @return int The State set size.
     */
    public int size() {
        return validStates.size();
    }

    /*
     * Get the runtime class of an object. 
     * 
     * @param state The State object representing the internal logic of a tile.
     * 
     * @return The declared class containing static methods of the State object.
     * 
     */
    private Class stateClass(State state) {
        return state.getClass();
    }

    /*
     * Add a state to the set.
     * 
     * @param state The State object representing the internal logic of a tile.
     * 
     * @return boolean True if a State was added to the set, false otherwise.
     */
    public boolean add(State state) {
        if(state == null) return false;
        if(validStates.contains(stateClass(state))) {
            return false;
        } else {
            validStates.add(stateClass(state));
            return true;
        }
    }

    /*
     * Remove a State from the set.
     * 
     * @return boolean True if a State was removed from the set, false otherwise.
     */
    public boolean remove(State state) {
        if(validStates.contains(stateClass(state))) {
            validStates.remove(stateClass(state));
            return true;
        } else {
            return false;
        }
    }

    /*
     * Check if a state exists in the set.
     * 
     * @param state The State object representing the internal logic of a tile.
     * 
     * @return boolean True if a State exists in the set, false otherwise.
     */
    public boolean contains(State state) {
        if(state == null) return false;
        return validStates.contains(stateClass(state));
    }

    /*
     * Determine if two State sets are the same.
     * 
     * @param ss The State set being compared.
     * 
     * @return boolean True if both State sets are the same, false otherwise.
     */
    public boolean equals(StateSet ss) {
        return validStates.equals(ss.validStates);
    }

    /*
     * Get the list of valid states.
     * This method will crash if State classes exist only locally in classes
     * or functions.
     * 
     * @return List<State> A list of States in the valid set.
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
