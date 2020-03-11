package edu.uci.Inf122.TileMatchingMania.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.ClickToInputMap;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.KeyToInputMap;

public class GameGUIBridge {
    protected int maxPlayers;
    protected boolean usesKeyInput;
    protected boolean usesClickInput;
    protected KeyToInputMap keyToInputMap;
    protected ClickToInputMap clickToInputMap;

    public int getMaxPlayers() { return maxPlayers; }
    public boolean getUsesKeyInput() { return usesKeyInput; }
    public boolean getUsesClickInput() { return usesClickInput; }
    public KeyToInputMap getKeyToInputMap() { return keyToInputMap; }
    public ClickToInputMap getClickToInputMap() { return clickToInputMap; }

    public GameGUIBridge(boolean usesKeyInput,
                         boolean usesClickInput,
                         int maxPlayers,
                         KeyToInputMap keyToInputMap,
                         ClickToInputMap clickToInputMap) {
        this.usesKeyInput = usesKeyInput;
        this.usesClickInput = usesClickInput;
        this.maxPlayers = maxPlayers;
        this.keyToInputMap = keyToInputMap;
        this.clickToInputMap = clickToInputMap;
    }
}
