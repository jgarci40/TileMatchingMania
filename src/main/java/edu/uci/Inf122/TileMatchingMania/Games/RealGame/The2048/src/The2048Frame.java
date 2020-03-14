package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src;

import edu.uci.Inf122.TileMatchingMania.GUI.GameFrame;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.The2048GameBridgePair;

public class The2048Frame extends GameFrame {
    public The2048Frame() throws Exception {
        super(new The2048GameBridgePair());
    }
}
