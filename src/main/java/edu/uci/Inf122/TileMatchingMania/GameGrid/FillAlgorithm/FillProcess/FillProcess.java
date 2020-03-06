package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.FillGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

public abstract class FillProcess {
    StateCollection stateCollection;
    public abstract void fillProcess(Tile[][] tileGrid, FillGenerator fg) throws Exception;

    abstract void fillTile(Tile tile, FillGenerator fg) throws Exception;
}
