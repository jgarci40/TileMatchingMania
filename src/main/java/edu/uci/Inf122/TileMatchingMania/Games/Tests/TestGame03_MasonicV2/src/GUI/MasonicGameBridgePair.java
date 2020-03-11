package edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.GameBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.Game.MasonicV2TestGame;

public class MasonicGameBridgePair extends GameBridgePair {
    public MasonicGameBridgePair() throws Exception {
        super(new MasonicV2TestGame(), new MasonicGameGUIBridge());
    }
}
