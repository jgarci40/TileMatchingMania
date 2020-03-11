package edu.uci.Inf122.TileMatchingMania.GUI;

public class GameGUIBridge {
    protected int maxPlayers;
    protected boolean usesKeyInput;
    protected boolean usesClickInput;

    public int getMaxPlayers() { return maxPlayers; }
    public boolean getUsesKeyInput() { return usesKeyInput; }
    public boolean getUsesClickInput() { return usesClickInput; }

    public GameGUIBridge(boolean usesKeyInput, boolean usesClickInput, int maxPlayers) {
        this.usesKeyInput = usesKeyInput;
        this.usesClickInput = usesClickInput;
        this.maxPlayers = maxPlayers;
    }
}
