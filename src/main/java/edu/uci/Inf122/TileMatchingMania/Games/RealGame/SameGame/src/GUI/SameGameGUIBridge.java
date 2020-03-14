package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.GameGUIBridge;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.ClickToInputMap;

public class SameGameGUIBridge extends GameGUIBridge {
    public SameGameGUIBridge() {
        super(false, true, 1, null, new ClickToInputMap(), "SameGame", new SameGameConverterGenerator());
    }
}
