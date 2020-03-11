package edu.uci.Inf122.TileMatchingMania.GUI;

import edu.uci.Inf122.TileMatchingMania.Game.Game;

public class GameBridgePair {
    Game game;
    GameGUIBridge bridge;

    public GameBridgePair(Game game, GameGUIBridge bridge) {
        this.game = game;
        this.bridge = bridge;
    }

    public Game getGame() { return game; }
    public GameGUIBridge getBridge() { return bridge; }
}
