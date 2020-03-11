package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.DirectionInput;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.KeyToInputMap;

public class The2048KeyToInputMap extends KeyToInputMap {
    public The2048KeyToInputMap() throws Exception {
        super();
        addInput(37, new DirectionInput(DirectionInput.Direction.LEFT));
        addInput(38, new DirectionInput(DirectionInput.Direction.UP));
        addInput(39, new DirectionInput(DirectionInput.Direction.RIGHT));
        addInput(40, new DirectionInput(DirectionInput.Direction.DOWN));
    }
}
