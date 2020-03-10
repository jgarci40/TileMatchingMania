package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.BlackSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.WhiteSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.GamePanel;
import edu.uci.Inf122.TileMatchingMania.GUI.Grid.GridsCanvas;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.CoordinateInput;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.Direction;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.KeyInput;
import edu.uci.Inf122.TileMatchingMania.GUI.StateToDrawableConverter;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;

import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable.*;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Game.The2048;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.State.*;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.BlackState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.WhiteState;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class The2048Frame extends JFrame {
    int rows;
    int cols;
    int boxSize;
    Drawable[][] drawables;
    The2048 the2048;
    GridsCanvas xyz;
    GamePanel gamePanel;

    /*private Drawable tileToDrawable(Tile tile) throws Exception {
        if(tile.getState().equivalent(new Block2State())) {
            return new Block2();
        } else if(tile.getState().equivalent(new EmptyBlockState())) {
            return new EmptyBlock();
        } else if(tile.getState().equivalent(new Block4State())) {
            return new Block4();
        } else if(tile.getState().equivalent(new Block8State())) {
            return new Block8();
        } else if(tile.getState().equivalent(new Block16State())) {
            return new Block16();
        } else if(tile.getState().equivalent(new Block32State())) {
            return new Block32();
        } else if(tile.getState().equivalent(new Block64State())) {
            return new Block64();
        } else if(tile.getState().equivalent(new Block128State())) {
            return new Block128();
        } else if(tile.getState().equivalent(new Block256State())) {
            return new Block256();
        } else if(tile.getState().equivalent(new Block512State())) {
            return new Block512();
        } else if(tile.getState().equivalent(new Block1024State())) {
            return new Block1024();
        } else if(tile.getState().equivalent(new Block2048State())) {
            return new Block2048();
        } else {
            throw new Exception("Invalid state");
        }
    }

    private Drawable[][] convertGridToDrawable(Tile[][] tmpGrid) throws Exception {
        Drawable[][] drawables = new Drawable[rows][cols];
        int i = 0;
        for(Tile[] row : tmpGrid) {
            int j = 0;
            for(Tile tile : row) {
                drawables[i][j] = tileToDrawable(tile);
                j++;
            }
            i++;
        }
        return drawables;
    }

    private void updateView() {
        try {
            drawables = convertGridToDrawable(the2048.getGrid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        xyz.setGrid(drawables);
        revalidate();
        repaint();
    }

     */

    class BasicKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent event) {
            printEventInfo("Key Pressed", event);

            int keyCode = event.getKeyCode();
            KeyInput input;

            switch (keyCode) {
                case 37:
                    input = new KeyInput(Direction.LEFT);
                    break;
                case 38:
                    input = new KeyInput(Direction.UP);
                    break;
                case 39:
                    input = new KeyInput(Direction.RIGHT);
                    break;
                case 40:
                    input = new KeyInput(Direction.DOWN);
                    break;
                default:
                    input = new KeyInput(Direction.INVALID);
                    break;
            }

            try {
                the2048.nextInput(input);
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

            gamePanel.updateView();
        }

        void saySomething(String eventDescription, MouseEvent e) {
            System.out.println(eventDescription + " detected on "
                    + e.getComponent().getClass().getName()
                    + ".");
        }
    }

    public The2048Frame() throws Exception {
        setResizable(false);
        boxSize = 64;
        //drawables = new Drawable[rows][cols];

        The2048Frame.BasicMouseListener bml = new The2048Frame.BasicMouseListener();
        The2048Frame.BasicKeyListener bkl = new The2048Frame.BasicKeyListener();
        this.addMouseListener(bml);
        this.addKeyListener(bkl);

        the2048 = new The2048();
        Map<String, StateCollection> collections = the2048.getCollections();
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
        gamePanel = new GamePanel(the2048, boxSize, converters);
        add(gamePanel);
        pack();
        gamePanel.updateView();


        /*the2048 = new The2048();
        rows = the2048.getRows();
        cols = the2048.getCols();
        xyz = new GridsCanvas(rows, cols, boxSize);

        add(xyz);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("2048");
        updateView();

         */
    }
}
