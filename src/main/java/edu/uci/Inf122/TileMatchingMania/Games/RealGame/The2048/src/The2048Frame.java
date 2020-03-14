package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src;

import edu.uci.Inf122.TileMatchingMania.GUI.*;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable.*;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.PanelBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.The2048ConverterGenerator;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.The2048GameBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.State.*;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class The2048Frame extends JFrame {
    int boxSize;
    PanelBridgePair panelBridgePair;

    public The2048Frame() throws Exception {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        boxSize = 64;
        GameBridgePair tmpBridge = new The2048GameBridgePair();
        setTitle(tmpBridge.getBridge().getName());

        Map<String, StateCollection> collections = tmpBridge.getGame().getCollections();
        The2048ConverterGenerator the2048ConverterGenerator = new The2048ConverterGenerator();
        Map<String, StateToDrawableConverter> converters = the2048ConverterGenerator.generate(collections);

        panelBridgePair = new PanelBridgePair(tmpBridge, new GamePanel(tmpBridge.getGame(), boxSize, converters));

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
