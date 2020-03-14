package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src;

import edu.uci.Inf122.TileMatchingMania.GUI.GameBridgePair;
import edu.uci.Inf122.TileMatchingMania.GUI.GamePanel;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.DirectionInput;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.NoInput;
import edu.uci.Inf122.TileMatchingMania.GUI.StateToDrawableConverter;

import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable.*;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.PanelBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.The2048GameBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.State.*;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class The2048Frame extends JFrame {
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
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
    }

    public The2048Frame() throws Exception {
        setResizable(false);
        boxSize = 64;
        GameBridgePair tmpBridge = new The2048GameBridgePair();

        if(tmpBridge.getBridge().getUsesClickInput()) {
            The2048Frame.BasicMouseListener bml = new The2048Frame.BasicMouseListener();
            this.addMouseListener(bml);
        }

        if(tmpBridge.getBridge().getUsesKeyInput()) {
            The2048Frame.BasicKeyListener bkl = new The2048Frame.BasicKeyListener();
            this.addKeyListener(bkl);
        }

        Map<String, StateCollection> collections = tmpBridge.getGame().getCollections();
        Map<String, StateToDrawableConverter> converters = new HashMap<>();

        for(String key : collections.keySet()) {
            System.out.println(key);
            StateCollection states = collections.get(key);
            StateToDrawableConverter converter = new StateToDrawableConverter(states);
            converter.addDrawable(new EmptyBlockState(), new EmptyBlock());
            converter.addDrawable(new Block2State(), new Block2());
            converter.addDrawable(new Block4State(), new Block4());
            converter.addDrawable(new Block8State(), new Block8());
            converter.addDrawable(new Block16State(), new Block16());
            converter.addDrawable(new Block32State(), new Block32());
            converter.addDrawable(new Block64State(), new Block64());
            converter.addDrawable(new Block128State(), new Block128());
            converter.addDrawable(new Block256State(), new Block256());
            converter.addDrawable(new Block512State(), new Block512());
            converter.addDrawable(new Block1024State(), new Block1024());
            converter.addDrawable(new Block2048State(), new Block2048());
            converters.put(key, converter);
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("2048");
        panelBridgePair = new PanelBridgePair(tmpBridge, new GamePanel(tmpBridge.getGame(), boxSize, converters));

        add(panelBridgePair.getGamePanel());
        pack();
        panelBridgePair.getGamePanel().updateView();
    }
}
