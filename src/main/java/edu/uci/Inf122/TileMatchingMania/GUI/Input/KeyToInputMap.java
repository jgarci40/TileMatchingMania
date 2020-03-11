package edu.uci.Inf122.TileMatchingMania.GUI.Input;

import java.util.HashMap;
import java.util.Map;

public class KeyToInputMap {
    Map<Integer, Input> keyInputMap;
    Input defaultInput = null;

    public KeyToInputMap() {
        keyInputMap = new HashMap<>();
    }

    public void addDefaultInput(Input input) {
        this.defaultInput = input;
    }

    public void addInput(int keyCode, Input input) throws Exception {
        keyInputMap.put(keyCode, input);
    }

    public Input getInput(int keyCode) throws Exception {
        if(defaultInput != null) return defaultInput;
        if(!keyInputMap.containsKey(keyCode)) throw new Exception("KeyToInputMap does not contain key code " + keyCode);
        return keyInputMap.get(keyCode);
    }
}
