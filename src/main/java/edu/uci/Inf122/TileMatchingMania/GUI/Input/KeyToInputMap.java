package edu.uci.Inf122.TileMatchingMania.GUI.Input;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.HashMap;
import java.util.Map;

public class KeyToInputMap {
    Map<String, Input> keyInputMap;
    Input defaultInput = null;

    public KeyToInputMap() {
        keyInputMap = new HashMap<>();
    }

    public void addDefaultInput(Input input) {
        this.defaultInput = input;
    }

    public void addInput(String str, Input input) throws Exception {
        keyInputMap.put(str, input);
    }

    public Input getInput(char ch) throws Exception {
        return getInput(String.valueOf(ch));
    }

    public Input getInput(String str) throws Exception {
        if(defaultInput != null) return defaultInput;
        if(!keyInputMap.containsKey(str)) throw new Exception("KeyToInputMap does not contain key " + str);
        return keyInputMap.get(str);
    }
}
