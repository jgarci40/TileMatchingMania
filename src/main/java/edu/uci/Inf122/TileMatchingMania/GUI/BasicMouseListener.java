package edu.uci.Inf122.TileMatchingMania.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.NoInput;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.PanelBridgePair;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class BasicMouseListener implements MouseListener {
    PanelBridgePair panelBridgePair;
    List<PanelBridgePair> bridges;
    int boxSize;

    public BasicMouseListener(PanelBridgePair panelBridgePair, int boxSize) {
        this.boxSize = boxSize;
        this.panelBridgePair = panelBridgePair;
    }

    public BasicMouseListener(List<PanelBridgePair> bridges, int boxSize) {
        this.boxSize = boxSize;
        this.bridges = bridges;
    }

    public void mousePressed(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        if(panelBridgePair != null) {
            processBridge(panelBridgePair, x, y);
        } else {
            for(var pair : bridges) {
                processBridge(pair, x, y);
            }
        }
    }

    private void processBridge(PanelBridgePair panelBridgePair, int x, int y) {
        try {
            Input input = panelBridgePair.getBridge().getBridge().getClickToInputMap().getInput((y - (boxSize / 2) - 4) / boxSize, (x - 4) / boxSize);
            panelBridgePair.getBridge().getGame().nextInput(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        panelBridgePair.getScorePanel().setScore(panelBridgePair.getBridge().getGame().getScore());
        panelBridgePair.getGamePanel().updateView();
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}