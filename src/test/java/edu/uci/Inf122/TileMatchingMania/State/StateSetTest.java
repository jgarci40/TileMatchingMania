package edu.uci.Inf122.TileMatchingMania.State;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StateSetTest {
    @Test
    void runAll() {
        testInitToEmpty();
        testAddState();
        testRemoveState();
        testContains();
        testEquals();
        testGetStates();
    }

    void testInitToEmpty() {
        StateSet ss = new StateSet();
        assertEquals(0, ss.size());
    }

    void testAddState() {
        StateSet ss = new StateSet();
        State s = new TestState();
        boolean boolRet = ss.add(s);
        assertEquals(1, ss.size());
        assertEquals(true, boolRet);
        assertFalse(ss.add(null));

        // no duplicates of same class
        State s1 = new TestState();
        boolRet = ss.add(s);
        assertEquals(false, boolRet);

        boolRet = ss.add(s1);
        assertEquals(false, boolRet);

        assertEquals(1, ss.size());
    }

    void testContains() {
        StateSet ss = new StateSet();

        State s = new TestState();
        boolean boolRet = ss.contains(s);
        assertEquals(false, boolRet);
        ss.add(s);
        boolRet = ss.contains(s);
        assertEquals(true, boolRet);
    }

    void testRemoveState() {
        StateSet ss = new StateSet();
        State s = new TestState();
        boolean boolRet = ss.remove(s);
        assertEquals(0, ss.size());
        assertEquals(false, boolRet);


        ss.add(s);
        boolRet = ss.remove(s);
        assertEquals(0, ss.size());
        assertEquals(true, boolRet);
    }

    void testEquals() {
        StateSet ss1 = new StateSet();
        StateSet ss2 = new StateSet();
        boolean boolRet = ss1.equals(ss2);
        assertTrue(boolRet);

        State s1 = new State(){};
        State s2 = new State(){};
        ss1.add(s1);
        ss2.add(s2);
        boolRet = ss1.equals(ss2);
        assertFalse(boolRet);

        ss1.add(s2);
        ss2.add(s1);
        boolRet = ss1.equals(ss2);
        assertTrue(boolRet);
    }

    void testGetStates() {
        StateSet ss = new StateSet();

        State s1 = new TestState1();
        State s2 = new TestState2();

        ss.add(s1);
        ss.add(s2);

        List<State> states = ss.getStates();
        assertEquals(2, states.size());
        assertEquals(s1.getClass(), states.get(0).getClass());
        assertEquals(s2.getClass(), states.get(1).getClass());
    }
}
