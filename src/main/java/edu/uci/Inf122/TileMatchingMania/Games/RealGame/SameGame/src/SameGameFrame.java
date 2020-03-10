package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.*;
import edu.uci.Inf122.TileMatchingMania.GUI.GamePanel;
import edu.uci.Inf122.TileMatchingMania.GUI.Grid.GridsCanvas;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.CoordinateInput;
import edu.uci.Inf122.TileMatchingMania.GUI.StateToDrawableConverter;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.Game.SameGame;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.BlueState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.EmptyState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.RedState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.GreenState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.BlackState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.WhiteState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.Game.MasonicV2TestGame;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class SameGameFrame extends JFrame {
    int rows;
    int cols;
    int boxSize;
    Drawable[][] drawables;
    SameGame sg;
    GridsCanvas xyz;
    GamePanel gamePanel;

    class BasicKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent event) {
            printEventInfo("Key Pressed", event);
            try {
                sg.nextInput(new CoordinateInput());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            gamePanel.updateView();
        }

        @Override
        public void keyReleased(KeyEvent event) {
//            printEventInfo("Key Released", event);
        }

        @Override
        public void keyTyped(KeyEvent event) {
//            printEventInfo("Key Typed", event);
        }

        private void printEventInfo(String str, KeyEvent e) {
            System.out.println(str);
            int code = e.getKeyCode();
            System.out.println("   Code: " + KeyEvent.getKeyText(code));
            System.out.println("   Char: " + e.getKeyChar());
            int mods = e.getModifiersEx();
            System.out.println("    Mods: "
                    + KeyEvent.getModifiersExText(mods));
            System.out.println("    Location: "
                    + keyboardLocation(e.getKeyLocation()));
            System.out.println("    Action? " + e.isActionKey());
        }

        private String keyboardLocation(int keybrd) {
            switch (keybrd) {
                case KeyEvent.KEY_LOCATION_RIGHT:
                    return "Right";
                case KeyEvent.KEY_LOCATION_LEFT:
                    return "Left";
                case KeyEvent.KEY_LOCATION_NUMPAD:
                    return "NumPad";
                case KeyEvent.KEY_LOCATION_STANDARD:
                    return "Standard";
                case KeyEvent.KEY_LOCATION_UNKNOWN:
                default:
                    return "Unknown";
            }
        }
    }

    class BasicMouseListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
//        saySomething("Mouse pressed; # of clicks: "
//        + e.getClickCount(), e);
        }

        public void mouseReleased(MouseEvent e) {
//            saySomething("XPos: "
//                    + e.getX() + " YPos: " + e.getY() + ")", e);
//            if(drawable1) {
//                drawable1 = !drawable1;
//                xyz.setGrid(drawables2);
//            } else {
//                drawable1 = !drawable1;
//                xyz.setGrid(drawables1);
//            }
//            revalidate();
//            repaint();
        }

        public void mouseEntered(MouseEvent e) {
//        saySomething("Mouse entered", e);
        }

        public void mouseExited(MouseEvent e) {
//        saySomething("Mouse exited", e);
        }

        public void mouseClicked(MouseEvent e) {
            saySomething("XPos: "
                    + e.getX() + " YPos: " + e.getY() + ")", e);

            int row = e.getY() / boxSize;
            int col = e.getX() / boxSize;

            CoordinateInput input = new CoordinateInput(row, col);

            try {
                sg.nextInput(input);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }


            gamePanel.updateView();
        }

        void saySomething(String eventDescription, MouseEvent e) {
            System.out.println(eventDescription + " detected on "
                    + e.getComponent().getClass().getName()
                    + ".");
        }
    }

    public SameGameFrame() throws Exception {
        setResizable(false);
        boxSize = 64;

        SameGameFrame.BasicMouseListener bml = new SameGameFrame.BasicMouseListener();
        SameGameFrame.BasicKeyListener bkl = new SameGameFrame.BasicKeyListener();
        this.addMouseListener(bml);
        this.addKeyListener(bkl);

        sg = new SameGame();
        Map<String, StateCollection> collections = sg.getCollections();
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

        gamePanel = new GamePanel(sg, boxSize, converters);
        add(gamePanel);
        pack();
        gamePanel.updateView();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Same Game");
        /*
        sg = new SameGame();
        rows = sg.getRows();
        cols = sg.getCols();
        xyz = new GridsCanvas(rows, cols, boxSize);

        add(xyz);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Same Game");
        updateView();

         */
    }
}
