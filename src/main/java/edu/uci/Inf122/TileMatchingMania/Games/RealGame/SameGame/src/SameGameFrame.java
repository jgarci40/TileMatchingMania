package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src;

import edu.uci.Inf122.TileMatchingMania.GUI.*;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.GUI.SameGameBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.GUI.SameGameConverterGenerator;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.PanelBridgePair;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import javax.swing.*;
import java.util.Map;

public class SameGameFrame extends JFrame {
    int boxSize;
    PanelBridgePair panelBridgePair;

    public SameGameFrame() throws Exception {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        boxSize = 64;
        GameBridgePair tmpBridge = new SameGameBridgePair();
        setTitle(tmpBridge.getBridge().getName());

        Map<String, StateCollection> collections = tmpBridge.getGame().getCollections();
        SameGameConverterGenerator sameGameConverterGenerator = new SameGameConverterGenerator();
        Map<String, StateToDrawableConverter> converters = sameGameConverterGenerator.generate(collections);



        if(tmpBridge.getBridge().getUsesKeyInput()) {
            BasicKeyListener bkl = new BasicKeyListener(panelBridgePair);
            this.addKeyListener(bkl);
        }

        if(tmpBridge.getBridge().getUsesClickInput()) {
            BasicMouseListener bml = new BasicMouseListener(panelBridgePair, boxSize);
            this.addMouseListener(bml);
        }

        add(panelBridgePair.getGamePanel());
        pack();

        panelBridgePair.getGamePanel().updateView();

    }
}
