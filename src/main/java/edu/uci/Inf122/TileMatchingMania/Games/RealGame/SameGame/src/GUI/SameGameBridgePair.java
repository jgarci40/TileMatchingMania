package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.GameBridgePair;
import edu.uci.Inf122.TileMatchingMania.GUI.GameGUIBridge;
import edu.uci.Inf122.TileMatchingMania.Game.Game;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.Game.SameGame;

public class SameGameBridgePair extends GameBridgePair {
    public SameGameBridgePair() throws Exception {
        super(new SameGame(), new SameGameGUIBridge());
    }
}
