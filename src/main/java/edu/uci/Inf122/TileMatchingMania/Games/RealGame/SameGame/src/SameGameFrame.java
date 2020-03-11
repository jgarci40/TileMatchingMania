package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.*;
import edu.uci.Inf122.TileMatchingMania.GUI.GamePanel;
import edu.uci.Inf122.TileMatchingMania.GUI.Grid.GridsCanvas;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.ClickToInputMap;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.CoordinateInput;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
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
    ClickToInputMap clickToInputMap;


    class BasicKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent event) {
            try {
                sg.nextInput(new CoordinateInput());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            gamePanel.updateView();
        }

        public void keyReleased(KeyEvent event) {
        }

        public void keyTyped(KeyEvent event) {
        }
    }

    class BasicMouseListener implements MouseListener {
        public void mousePressed(MouseEvent event) {
            int x = event.getX();
            int y = event.getY();
            try {
                Input input = clickToInputMap.getInput((y - (boxSize / 2) - 4) / boxSize, (x - 4) / boxSize);
                sg.nextInput(input);
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
        clickToInputMap = new ClickToInputMap();

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
