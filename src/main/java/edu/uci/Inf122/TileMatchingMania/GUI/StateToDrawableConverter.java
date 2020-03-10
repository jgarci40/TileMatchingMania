package edu.uci.Inf122.TileMatchingMania.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.HashMap;
import java.util.Map;

public class StateToDrawableConverter {
    private StateCollection stateCollection;
    private Map<Class, Drawable> conversionMap;

    public StateToDrawableConverter(StateCollection stateCollection) {
        this.stateCollection = stateCollection;
        conversionMap = new HashMap<>();
    }

    public void addDrawable(State state, Drawable drawable) throws Exception {
        if(!stateCollection.containsState(state)) throw new Exception("State passed to addDrawable is not in StateCollection");
        conversionMap.put(state.getClass(), drawable);
    }

    public Drawable getDrawable(State state) throws Exception {
        if(!stateCollection.containsState(state)) throw new Exception("State passed to getDrawable is not in StateCollection");
        if(!conversionMap.containsKey(state.getClass())) throw new Exception("Drawable has not been added for this state: " + state);
        return conversionMap.get(state.getClass());
    }
}
