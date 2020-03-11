package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.GameBridgePair;
import edu.uci.Inf122.TileMatchingMania.GUI.GameGUIBridge;
import edu.uci.Inf122.TileMatchingMania.Game.Game;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Game.The2048;

public class The2048GameBridgePair extends GameBridgePair {
    public The2048GameBridgePair() throws Exception {
        super(new The2048(), new The2048GUIBridge());
    }
}
