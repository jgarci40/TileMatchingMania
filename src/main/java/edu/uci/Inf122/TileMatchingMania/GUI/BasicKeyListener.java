package edu.uci.Inf122.TileMatchingMania.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.NoInput;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.PanelBridgePair;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BasicKeyListener implements KeyListener {
    PanelBridgePair panelBridgePair;
    public BasicKeyListener(PanelBridgePair panelBridgePair) {
        this.panelBridgePair = panelBridgePair;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        Input input;
        try {
            input = panelBridgePair.getBridge().getBridge().getKeyToInputMap().getInput(keyCode);
            if(input instanceof NoInput) return;
            panelBridgePair.getBridge().getGame().nextInput(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        panelBridgePair.getGamePanel().updateView();
    }

    public void keyReleased(KeyEvent event) {}
    public void keyTyped(KeyEvent event) {}
}