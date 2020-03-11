package edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.GameGUIBridge;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.ClickToInputMap;

public class MasonicGameGUIBridge extends GameGUIBridge {
    public MasonicGameGUIBridge() {
        super(true,
                true,
                1,
                new MasonicKeyToInputMap(),
                new ClickToInputMap());
    }
}
