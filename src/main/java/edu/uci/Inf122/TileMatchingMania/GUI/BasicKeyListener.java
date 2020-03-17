package edu.uci.Inf122.TileMatchingMania.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.NoInput;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.PanelBridgePair;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class BasicKeyListener implements KeyListener {
    PanelBridgePair panelBridgePair;
    List<PanelBridgePair> bridges;
    public BasicKeyListener(PanelBridgePair panelBridgePair) {
        this.panelBridgePair = panelBridgePair;
    }

    public BasicKeyListener(List<PanelBridgePair> bridges) {
        this.bridges = bridges;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if(panelBridgePair != null) {
           processBridge(panelBridgePair, keyCode);
        } else {
            for(var pair : bridges) {
                processBridge(pair, keyCode);
            }
        }
    }

    private void processBridge(PanelBridgePair panelBridgePair, int keyCode) {
        Input input;
        try {
            input = panelBridgePair.getBridge().getBridge().getKeyToInputMap().getInput(keyCode);
            if(input instanceof NoInput) return;
            panelBridgePair.getBridge().getGame().nextInput(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        panelBridgePair.getScorePanel().setScore(panelBridgePair.getBridge().getGame().getScore(), panelBridgePair.getBridge().getGame().getGameOver());
        panelBridgePair.getGamePanel().updateView();
    }

    public void keyReleased(KeyEvent event) {}
    public void keyTyped(KeyEvent event) {}
}