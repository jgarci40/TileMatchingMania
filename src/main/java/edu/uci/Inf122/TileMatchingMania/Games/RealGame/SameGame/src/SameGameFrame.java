package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.*;
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
    GamePanel gamePanel;
    SameGameBridgePair gameBridge;


    class BasicKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent event) {
            int keyCode = event.getKeyCode();
            Input input;
            try {
                input = gameBridge.getBridge().getKeyToInputMap().getInput(keyCode);
                if(input instanceof NoInput) return;
                gameBridge.getGame().nextInput(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
                Input input = gameBridge.getBridge().getClickToInputMap().getInput((y - (boxSize / 2) - 4) / boxSize, (x - 4) / boxSize);
                gameBridge.getGame().nextInput(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            gamePanel.updateView();
        }

        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
    }

    public SameGameFrame() throws Exception {
        setResizable(false);
        boxSize = 64;
        gameBridge = new SameGameBridgePair();

        if(gameBridge.getBridge().getUsesClickInput()) {
            SameGameFrame.BasicMouseListener bml = new SameGameFrame.BasicMouseListener();
            this.addMouseListener(bml);
        }

        if(gameBridge.getBridge().getUsesKeyInput()) {
            SameGameFrame.BasicKeyListener bkl = new SameGameFrame.BasicKeyListener();
            this.addKeyListener(bkl);
        }


        Map<String, StateCollection> collections = gameBridge.getGame().getCollections();
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

        gamePanel = new GamePanel(gameBridge.getGame(), boxSize, converters);
        add(gamePanel);
        pack();
        gamePanel.updateView();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Same Game");

    }
}
