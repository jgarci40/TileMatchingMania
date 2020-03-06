package edu.uci.Inf122.TileMatchingMania.Games.Tests.RandomGrid.src;

import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.Drawable;
import edu.uci.Inf122.TileMatchingMania.GUI.Grid.GridsCanvas;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.RandomGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess.LinearFillProcess;
import edu.uci.Inf122.TileMatchingMania.GameGrid.GameGrid;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.RandomGrid.src.Drawable.BlackSquare;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.RandomGrid.src.Drawable.WhiteSquare;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.RandomGrid.src.State.BlackState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.RandomGrid.src.State.WhiteState;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import javax.swing.*;

public class RandomGridTestGame extends JFrame {
    int rows;
    int cols;
    int boxSize;
    Drawable[][] drawables;
    StateCollection stateCollection;
    GridsCanvas xyz;
    GameGrid gameGrid;

    private void randomFillGrid() throws Exception {
        gameGrid.fillGrid(new LinearFillProcess(stateCollection), new RandomGenerator(stateCollection));
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

    private Drawable[][] convertGridToDrawable() throws Exception {
        Drawable[][] drawables = new Drawable[rows][cols];
        Tile[][] tmpGrid = gameGrid.getGameGrid();
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

    public RandomGridTestGame() throws Exception {
        setResizable(false);
        rows = 10;
        cols = 10;
        boxSize = 64;
        drawables = new Drawable[rows][cols];
        stateCollection = new StateCollection();
        stateCollection.setDefaultState(new BlackState());
        stateCollection.addState(new WhiteState());
        gameGrid = new GameGrid(rows, cols, stateCollection);
        randomFillGrid();
        drawables = convertGridToDrawable();

        xyz = new GridsCanvas(rows, cols, boxSize);
        xyz.setGrid(drawables);
        add(xyz);
        pack();
    }
}
