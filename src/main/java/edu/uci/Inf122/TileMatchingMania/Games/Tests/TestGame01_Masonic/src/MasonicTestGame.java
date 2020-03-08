package edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame01_Masonic.src;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.BlackSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.WhiteSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.Grid.GridsCanvas;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MasonicTestGame extends JFrame {
    Drawable[][] drawables1;
    Drawable[][] drawables2;
    GridsCanvas xyz;
    boolean drawable1;

    class BasicKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent event) {
            printEventInfo("Key Pressed", event);
            if(drawable1) {
                drawable1 = !drawable1;
                xyz.setGrid(drawables2);
            } else {
                drawable1 = !drawable1;
                xyz.setGrid(drawables1);
            }
            revalidate();
            repaint();
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
            saySomething("XPos: "
                    + e.getX() + " YPos: " + e.getY() + ")", e);
            if(drawable1) {
                drawable1 = !drawable1;
                xyz.setGrid(drawables2);
            } else {
                drawable1 = !drawable1;
                xyz.setGrid(drawables1);
            }
            revalidate();
            repaint();
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

    public MasonicTestGame() {
        setResizable(false);
        int rows = 10;
        int cols = 10;
        int boxSize = 64;
        drawables1 = new Drawable[rows][cols];
        drawables2 = new Drawable[rows][cols];
        BasicMouseListener bml = new BasicMouseListener();
        BasicKeyListener bkl = new BasicKeyListener();
        this.addMouseListener(bml);
        this.addKeyListener(bkl);

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(i % 2 == 0) {
                    if(j % 2 == 0) {
                        drawables1[i][j] = new WhiteSquare(boxSize, boxSize);
                        drawables2[i][j] = new BlackSquare(boxSize, boxSize);
                    } else {
                        drawables1[i][j] = new BlackSquare(boxSize, boxSize);
                        drawables2[i][j] = new WhiteSquare(boxSize, boxSize);
                    }
                } else {
                    if(j % 2 == 0) {
                        drawables1[i][j] = new BlackSquare(boxSize, boxSize);
                        drawables2[i][j] = new WhiteSquare(boxSize, boxSize);
                    } else {
                        drawables1[i][j] = new WhiteSquare(boxSize, boxSize);
                        drawables2[i][j] = new BlackSquare(boxSize, boxSize);
                    }
                }
            }
        }

        xyz = new GridsCanvas(rows, cols, boxSize);
        drawable1 = true;
        xyz.setGrid(drawables1);
        add(xyz);
        pack();
    }
}
