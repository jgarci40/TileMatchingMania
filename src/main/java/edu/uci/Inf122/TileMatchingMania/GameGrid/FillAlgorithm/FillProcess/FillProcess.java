package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.FillGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

public abstract class FillProcess {
    protected StateCollection stateCollection;

    public FillProcess(StateCollection stateCollection) {
        setStateCollection(stateCollection);
    }

    public void setStateCollection(StateCollection stateCollection) {
        this.stateCollection = stateCollection;
    }

    public abstract void fillProcess(Tile[][] tileGrid, FillGenerator fg) throws Exception;

    protected abstract void fillTile(Tile tile, FillGenerator fg) throws Exception;
}
