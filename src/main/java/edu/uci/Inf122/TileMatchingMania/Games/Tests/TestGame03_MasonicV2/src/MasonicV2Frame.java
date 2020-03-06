package edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;
import edu.uci.Inf122.TileMatchingMania.GUI.Grid.GridsCanvas;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.Algorithms.CheckerBoardAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.Algorithms.RandomFillAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.GameGrid;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.BlackState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.WhiteState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.Drawable.BlackSquare;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.Drawable.WhiteSquare;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MasonicV2Frame extends JFrame {
    int rows;
    int cols;
    int boxSize;
    GameGrid gameGrid;
    Drawable[][] drawables;
    StateCollection stateCollection;

    GridsCanvas xyz;

    private void checkerboardFillGrid() throws Exception {
        gameGrid.fillGrid(new CheckerBoardAlgorithm(stateCollection));
    }


    private Drawable tileToDrawable(Tile tile) throws Exception {
        if(tile.getState().equivalent(new BlackState())) {
            return new edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.Drawable.BlackSquare();
        } else if(tile.getState().equivalent(new WhiteState())) {
            return new edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.Drawable.WhiteSquare();
        } else {
            throw new Exception("Invalid state");
        }
    }

    private Drawable[][] convertGridToDrawable() throws Exception {
        Drawable[][] drawables = new Drawable[rows][cols];
        Tile[][] tmpGrid = gameGrid.getGrid();
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

    class BasicKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent event) {
//            printEventInfo("Key Pressed", event);
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

        void saySomething(String eventDescription, MouseEvent e) {
            System.out.println(eventDescription + " detected on "
                    + e.getComponent().getClass().getName()
                    + ".");
        }
    }

    public MasonicV2Frame() throws Exception {
        setResizable(false);
        rows = 10;
        cols = 10;
        boxSize = 64;
        drawables = new Drawable[rows][cols];
        BasicMouseListener bml = new BasicMouseListener();
        BasicKeyListener bkl = new BasicKeyListener();
        this.addMouseListener(bml);
        this.addKeyListener(bkl);

//        for(int i = 0; i < rows; i++) {
//            for(int j = 0; j < cols; j++) {
//                if(i % 2 == 0) {
//                    if(j % 2 == 0) {
//                        drawables1[i][j] = new WhiteSquare(boxSize, boxSize);
//                        drawables2[i][j] = new BlackSquare(boxSize, boxSize);
//                    } else {
//                        drawables1[i][j] = new BlackSquare(boxSize, boxSize);
//                        drawables2[i][j] = new WhiteSquare(boxSize, boxSize);
//                    }
//                } else {
//                    if(j % 2 == 0) {
//                        drawables1[i][j] = new BlackSquare(boxSize, boxSize);
//                        drawables2[i][j] = new WhiteSquare(boxSize, boxSize);
//                    } else {
//                        drawables1[i][j] = new WhiteSquare(boxSize, boxSize);
//                        drawables2[i][j] = new BlackSquare(boxSize, boxSize);
//                    }
//                }
//            }
//        }

        stateCollection = new StateCollection();
        stateCollection.setDefaultState(new BlackState());
        stateCollection.addState(new WhiteState());
        gameGrid = new GameGrid(rows, cols, stateCollection);
        checkerboardFillGrid();
        drawables = convertGridToDrawable();

        xyz = new GridsCanvas(rows, cols, boxSize);
        xyz.setGrid(drawables);
        add(xyz);
        pack();
    }
}
