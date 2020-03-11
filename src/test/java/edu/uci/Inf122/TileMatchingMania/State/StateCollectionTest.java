package edu.uci.Inf122.TileMatchingMania.State;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StateCollectionTest {
    @Test
    void runAll() throws Exception {
        testInitToEmpty();
        testAddState();
        testHasDefaultState();
        testGetDefaultState();
        testRemoveState();
        testSetDefaultState();
        testContainsState();
        testEquals();
        testGetValidStates();
    }

    void testInitToEmpty() {
        StateCollection sc = new StateCollection();
        assertEquals(0, sc.size());
    }

    void testAddState() {
        StateCollection sc = new StateCollection();
        assertEquals(0, sc.size());
        State state = new TestState();
        sc.addState(state);
        assertEquals(1, sc.size());
    }

    void testHasDefaultState() throws Exception {
        StateCollection sc = new StateCollection();
        assertFalse(sc.hasDefaultState());
        State state = new TestState();
        sc.setDefaultState(state);
        assertTrue(sc.hasDefaultState());
    }

    void testGetDefaultState() throws Exception {
        StateCollection sc = new StateCollection();
        assertThrows(Exception.class, () -> sc.getDefaultState());
        State state = new TestState();
        sc.setDefaultState(state);
        try {
            assertEquals(state.getClass(), sc.getDefaultState().getClass());
        } catch(Exception e) {
        }
    }

    void testContainsState() {
        StateCollection sc = new StateCollection();
        assertFalse(sc.containsState(null));
        State state = new TestState();
        sc.addState(state);
        assertTrue(sc.containsState(state));

        State state2 = new TestState1();
        assertFalse(sc.containsState(state2));
    }

    void testSetDefaultState() throws Exception {
        StateCollection sc = new StateCollection();
        assertThrows(Exception.class, () -> sc.setDefaultState(null));
        State state = new TestState();
        sc.setDefaultState(state);
    }

    void testRemoveState() {
        StateCollection sc = new StateCollection();
        assertFalse(sc.containsState(null));
        TestState state = new TestState();
        assertFalse(sc.containsState(state));
        assertFalse(sc.removeState(state));
        sc.addState(state);
        assertTrue(sc.containsState(state));
        assertTrue(sc.removeState(state));
    }

    void testEquals() throws Exception {
        StateCollection sc1 = new StateCollection();
        StateCollection sc2 = new StateCollection();
        assertFalse(sc1.equals(sc2));

        State state1 = new TestState1();
        State state2 = new TestState2();

        sc1.setDefaultState(state1);
        sc2.setDefaultState(state2);
        assertFalse(sc1.equals(sc2));

        sc1.setDefaultState(state2);
        assertTrue(sc1.equals(sc2));

        sc1.addState(state1);
        assertFalse(sc1.equals(sc2));

        sc2.addState(state1);
        assertTrue(sc1.equals(sc2));
    }

    void testGetValidStates() {
        StateCollection sc = new StateCollection();
        State state1 = new TestState1();
        State state2 = new TestState2();
        sc.addState(state1);
        sc.addState(state2);

        List<State> states = sc.getValidStates();
        State state1o = states.get(0);
        State state2o = states.get(1);
        assertTrue(state1.equivalent(state2o));
        assertTrue(state2.equivalent(state1o));
    }
}
