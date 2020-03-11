package edu.uci.Inf122.TileMatchingMania.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;
import edu.uci.Inf122.TileMatchingMania.GUI.Grid.GridsCanvas;
import edu.uci.Inf122.TileMatchingMania.Game.Game;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;

import javax.swing.*;
import java.util.Map;

public class GamePanel extends JPanel {
    int rows;
    int cols;
    int boxSize;
    Drawable[][] drawables;
    Game game;
    GridsCanvas gridCanvas;
    Map<String, StateToDrawableConverter> converters;

    public void updateView() {
        try {
            drawables = convertGridToDrawable(game.getGrid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        gridCanvas.setGrid(drawables);
        revalidate();
        repaint();
    }

    private Drawable tileToDrawable(Tile tile, String set) throws Exception {
        return converters.get(set).getDrawable(tile.getState());
    }

    private Drawable[][] convertGridToDrawable(Tile[][] tmpGrid) throws Exception {
        return convertGridToDrawable(tmpGrid, "default");
    }

    private Drawable[][] convertGridToDrawable(Tile[][] tmpGrid, String set) throws Exception {
        Drawable[][] drawables = new Drawable[rows][cols];
        int i = 0;
        for(Tile[] row : tmpGrid) {
            int j = 0;
            for(Tile tile : row) {
                drawables[i][j] = tileToDrawable(tile, set);
                j++;
            }
            i++;
        }
        return drawables;
    }

    public GamePanel(Game game, int boxSize, Map<String, StateToDrawableConverter> converters) {
        this.game = game;
        this.boxSize = boxSize;
        setBorder(null);
        rows = game.getRows();
        cols = game.getCols();
        this.converters = converters;

        gridCanvas = new GridsCanvas(rows, cols, boxSize);
        add(gridCanvas);
    }
}
