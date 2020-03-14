package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.GameBridgePair;
import edu.uci.Inf122.TileMatchingMania.GUI.GamePanel;

public class PanelBridgePair {
    private GameBridgePair bridgePair;
    private GamePanel gamePanel;

    public PanelBridgePair(GameBridgePair gameBridgePair, GamePanel gamePanel) {
        this.bridgePair = gameBridgePair;
        this.gamePanel = gamePanel;
    }

    public GameBridgePair getBridge() {
        return bridgePair;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
