package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;
import edu.uci.Inf122.TileMatchingMania.GUI.Grid.GridsCanvas;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.CoordinateInput;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.Game.SameGame;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.BlueState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.EmptyState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.RedState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.GreenState;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SameGameFrame extends JFrame {
    int rows;
    int cols;
    int boxSize;
    Drawable[][] drawables;
    SameGame sg;
    GridsCanvas xyz;

    private Drawable tileToDrawable(Tile tile) throws Exception {
        if(tile.getState().equivalent(new RedState())) {
            return new edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.Drawable.RedSquare();
        } else if(tile.getState().equivalent(new BlueState())) {
            return new edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.Drawable.BlueSquare();
        } else if(tile.getState().equivalent(new GreenState())) {
            return new edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.Drawable.GreenSquare();
        } else if(tile.getState().equivalent(new EmptyState())) {
            return new edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.Drawable.EmptySquare();
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
            drawables = convertGridToDrawable(sg.getGrid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        xyz.setGrid(drawables);
        revalidate();
        repaint();
    }

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
            updateView();
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


            updateView();
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
        drawables = new Drawable[rows][cols];

        SameGameFrame.BasicMouseListener bml = new SameGameFrame.BasicMouseListener();
        SameGameFrame.BasicKeyListener bkl = new SameGameFrame.BasicKeyListener();
        this.addMouseListener(bml);
        this.addKeyListener(bkl);

        sg = new SameGame();
        rows = sg.getRows();
        cols = sg.getCols();
        xyz = new GridsCanvas(rows, cols, boxSize);

        add(xyz);
        pack();

        updateView();
    }
}
