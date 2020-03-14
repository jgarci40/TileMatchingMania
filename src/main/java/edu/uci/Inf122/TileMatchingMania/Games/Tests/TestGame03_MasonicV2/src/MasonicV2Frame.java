package edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src;

import edu.uci.Inf122.TileMatchingMania.GUI.*;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.BlackSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.WhiteSquare;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.PanelBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.GUI.MasonicConverterGenerator;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.GUI.MasonicGameBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.State.BlackState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.State.WhiteState;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MasonicV2Frame extends JFrame {
    int boxSize;
    PanelBridgePair panelBridgePair;

    public MasonicV2Frame() throws Exception {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        boxSize = 64;
        GameBridgePair tmpBridge = new MasonicGameBridgePair();
        setTitle(tmpBridge.getBridge().getName());


        Map<String, StateCollection> collections = tmpBridge.getGame().getCollections();
        MasonicConverterGenerator masonicConverterGenerator = new MasonicConverterGenerator();
        Map<String, StateToDrawableConverter> converters = masonicConverterGenerator.generate(collections);

//        GamePanel gp2 = new GamePanel(gameBridge.getGame(), boxSize, converters);
//        JPanel mainPanel = new JPanel();
//        mainPanel.setBorder(null);
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
//        mainPanel.add(gp2);
//        mainPanel.add(gamePanel);
//        LoginDemo loginDemo = new LoginDemo();
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
//        gp2.updateView();
    }
}
