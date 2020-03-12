package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.DirectionInput;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.KeyToInputMap;

public class The2048KeyToInputMap extends KeyToInputMap {
    public The2048KeyToInputMap(int left, int up, int right, int down) throws Exception {
        super();
        addInput(left, new DirectionInput(DirectionInput.Direction.LEFT));
        addInput(up, new DirectionInput(DirectionInput.Direction.UP));
        addInput(right, new DirectionInput(DirectionInput.Direction.RIGHT));
        addInput(down, new DirectionInput(DirectionInput.Direction.DOWN));
    }
}
