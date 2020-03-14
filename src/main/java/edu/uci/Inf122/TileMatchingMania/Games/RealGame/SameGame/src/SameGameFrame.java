package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.*;
import edu.uci.Inf122.TileMatchingMania.GUI.GameBridgePair;
import edu.uci.Inf122.TileMatchingMania.GUI.GamePanel;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.DirectionInput;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.NoInput;
import edu.uci.Inf122.TileMatchingMania.GUI.StateToDrawableConverter;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.GUI.SameGameBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.BlueState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.EmptyState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.RedState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.GreenState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.PanelBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.The2048GameBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.The2048Frame;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class SameGameFrame extends JFrame {
    int boxSize;
    PanelBridgePair panelBridgePair;

    class BasicKeyListener implements KeyListener {
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

    class BasicMouseListener implements MouseListener {
        public void mousePressed(MouseEvent event) {
            int x = event.getX();
            int y = event.getY();
            try {
                Input input = panelBridgePair.getBridge().getBridge().getClickToInputMap().getInput((y - (boxSize / 2) - 4) / boxSize, (x - 4) / boxSize);
                panelBridgePair.getBridge().getGame().nextInput(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            panelBridgePair.getGamePanel().updateView();
        }

        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
    }

    public SameGameFrame() throws Exception {
        setResizable(false);
        boxSize = 64;
        GameBridgePair tmpBridge = new SameGameBridgePair();

        if(tmpBridge.getBridge().getUsesClickInput()) {
            BasicMouseListener bml = new BasicMouseListener();
            this.addMouseListener(bml);
        }

        if(tmpBridge.getBridge().getUsesKeyInput()) {
            BasicKeyListener bkl = new BasicKeyListener();
            this.addKeyListener(bkl);
        }


        Map<String, StateCollection> collections = tmpBridge.getGame().getCollections();
        Map<String, StateToDrawableConverter> converters = new HashMap<>();
        for(String key : collections.keySet()) {
            StateCollection states = collections.get(key);
            StateToDrawableConverter converter = new StateToDrawableConverter(states);
            converter.addDrawable(new EmptyState(), new BlackSquare());
            converter.addDrawable(new GreenState(), new GreenSquare());
            converter.addDrawable(new RedState(), new RedSquare());
            converter.addDrawable(new BlueState(), new BlueSquare());
            converters.put(key, converter);
        }

        panelBridgePair = new PanelBridgePair(tmpBridge, new GamePanel(tmpBridge.getGame(), boxSize, converters));

        add(panelBridgePair.getGamePanel());
        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Same Game");
        panelBridgePair.getGamePanel().updateView();

    }
}
