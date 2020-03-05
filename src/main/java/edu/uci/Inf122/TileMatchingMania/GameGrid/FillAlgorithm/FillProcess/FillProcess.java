package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

public abstract class FillProcess {
    StateCollection stateCollection;
    public abstract void fillProcess(Tile[][] tileGrid);

    public abstract void fillTile(Tile tile);
}
