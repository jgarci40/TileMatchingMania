package edu.uci.Inf122.TileMatchingMania.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.BlackSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.WhiteSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.Grid.GridsCanvas;
import edu.uci.Inf122.TileMatchingMania.Game.Game;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.BlackState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.WhiteState;

import javax.swing.*;

public class GamePanel extends JPanel {
    int rows;
    int cols;
    int boxSize;
    Drawable[][] drawables;
    Game game;
    GridsCanvas gridCanvas;

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

    private Drawable tileToDrawable(Tile tile) throws Exception {
        if(tile.getState().equivalent(new BlackState())) {
            return new BlackSquare();
        } else if(tile.getState().equivalent(new WhiteState())) {
            return new WhiteSquare();
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

    public GamePanel(Game game, int boxSize) {
        this.game = game;
        this.boxSize = boxSize;
        rows = game.getRows();
        cols = game.getCols();

        gridCanvas = new GridsCanvas(rows, cols, boxSize);
        add(gridCanvas);
    }
}
