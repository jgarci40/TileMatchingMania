package edu.uci.Inf122.TileMatchingMania.GUI;

import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.GUI.SameGameConverterGenerator;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.PanelBridgePair;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import javax.swing.*;
import java.util.Map;

public class GameFrame extends JFrame {
    int boxSize;
    PanelBridgePair panelBridgePair;

    public GameFrame(GameBridgePair gameBridgePair) throws Exception {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        boxSize = 64;
        setTitle(gameBridgePair.getBridge().getName());

        Map<String, StateCollection> collections = gameBridgePair.getGame().getCollections();
        ConverterGenerator converterGenerator = gameBridgePair.getBridge().getConverter();
        Map<String, StateToDrawableConverter> converters = converterGenerator.generate(collections);

        panelBridgePair = new PanelBridgePair(gameBridgePair, new GamePanel(gameBridgePair.getGame(), boxSize, converters));

        if(gameBridgePair.getBridge().getUsesKeyInput()) {
            BasicKeyListener bkl = new BasicKeyListener(panelBridgePair);
            this.addKeyListener(bkl);
        }

        if(gameBridgePair.getBridge().getUsesClickInput()) {
            BasicMouseListener bml = new BasicMouseListener(panelBridgePair, boxSize);
            this.addMouseListener(bml);
        }

        add(panelBridgePair.getGamePanel());
        pack();
        panelBridgePair.getGamePanel().updateView();
    }
}
