package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.GameBridgePair;
import edu.uci.Inf122.TileMatchingMania.GUI.GamePanel;
import edu.uci.Inf122.TileMatchingMania.GUI.ScorePanel;

public class PanelBridgePair {
    private GameBridgePair bridgePair;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;

    public PanelBridgePair(GameBridgePair gameBridgePair, GamePanel gamePanel, ScorePanel scorePanel) {
        this.scorePanel = scorePanel;
        this.bridgePair = gameBridgePair;
        this.gamePanel = gamePanel;
    }

    public GameBridgePair getBridge() {
        return bridgePair;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ScorePanel getScorePanel() { return scorePanel; }
}
