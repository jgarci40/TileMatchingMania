package edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.BlackSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.WhiteSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.GamePanel;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.ClickToInputMap;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.GUI.StateToDrawableConverter;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.BlackState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.WhiteState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.GUI.MasonicKeyToInputMap;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.Game.MasonicV2TestGame;
import edu.uci.Inf122.TileMatchingMania.State.State;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MasonicV2Frame extends JFrame {
    int boxSize;
    MasonicV2TestGame mtg;
    GamePanel gamePanel;
    MasonicKeyToInputMap masonicKeyToInputMap;
    ClickToInputMap clickToInputMap;

    class BasicKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent event) {
            try {
                Input input = masonicKeyToInputMap.getInput(event.getKeyChar());
                mtg.nextInput(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            gamePanel.updateView();
        }
        public void keyReleased(KeyEvent event) {}
        public void keyTyped(KeyEvent event) {}
    }

    class BasicMouseListener implements MouseListener {
        public void mousePressed(MouseEvent event) {
            int x = event.getX();
            int y = event.getY();
            try {
                Input input = clickToInputMap.getInput((y - (boxSize / 2) - 4) / boxSize, (x - 4) / boxSize);
                mtg.nextInput(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            gamePanel.updateView();
        }

        public void mouseReleased(MouseEvent event) {}

        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
    }

    public MasonicV2Frame() throws Exception {
        setResizable(false);
        boxSize = 64;
        masonicKeyToInputMap = new MasonicKeyToInputMap();
        clickToInputMap = new ClickToInputMap();

        BasicMouseListener bml = new BasicMouseListener();
        BasicKeyListener bkl = new BasicKeyListener();
        this.addMouseListener(bml);
        this.addKeyListener(bkl);

        mtg = new MasonicV2TestGame();
        Map<String, StateCollection> collections = mtg.getCollections();
        Map<String, StateToDrawableConverter> converters = new HashMap<>();
        for(String key : collections.keySet()) {
            StateCollection states = collections.get(key);
            StateToDrawableConverter converter = new StateToDrawableConverter(states);
            converter.addDrawable(new BlackState(), new BlackSquare());
            converter.addDrawable(new WhiteState(), new WhiteSquare());
            converters.put(key, converter);
        }

        gamePanel = new GamePanel(mtg, boxSize, converters);
        GamePanel gp2 = new GamePanel(mtg, boxSize, converters);
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(null);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
//        mainPanel.add(gp2);
        mainPanel.add(gamePanel);
        add(mainPanel);
        pack();
        gamePanel.updateView();
        gp2.updateView();
    }
}
