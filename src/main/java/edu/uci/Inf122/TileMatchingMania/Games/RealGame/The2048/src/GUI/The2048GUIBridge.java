package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.GameGUIBridge;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.ClickToInputMap;

public class The2048GUIBridge extends GameGUIBridge {
    public The2048GUIBridge() throws Exception {
        super(true, false, 2, new The2048KeyToInputMap(), new ClickToInputMap());
    }
}
