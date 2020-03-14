package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.GameGUIBridge;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.ClickToInputMap;

public class The2048GUIBridge extends GameGUIBridge {
    protected The2048KeyToInputMap player2;
    public The2048GUIBridge() throws Exception {
        super(true, false, 2, new The2048KeyToInputMap(37, 38, 39, 40), new ClickToInputMap(), "2048", new The2048ConverterGenerator());
        player2 = new The2048KeyToInputMap(65, 87,68,83);
    }
}
