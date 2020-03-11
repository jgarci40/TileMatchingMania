package edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.KeyToInputMap;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.Input.MasonicInput;

public class MasonicKeyToInputMap extends KeyToInputMap {
    public MasonicKeyToInputMap() {
        super();
        addDefaultInput(new MasonicInput());
    }
}
