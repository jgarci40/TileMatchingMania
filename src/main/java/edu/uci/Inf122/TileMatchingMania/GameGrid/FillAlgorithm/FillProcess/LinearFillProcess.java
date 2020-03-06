package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.FillGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

//TODO add tests for this class
public class LinearFillProcess extends FillProcess {
    StateCollection stateCollection;

    public LinearFillProcess(StateCollection stateCollection) {
        this.stateCollection = stateCollection;
    }

    public void setStateCollection(StateCollection stateCollection) {
        this.stateCollection = stateCollection;
    }

    public void fillProcess(Tile[][] tileGrid, FillGenerator fg) throws Exception {
        for(Tile[] row : tileGrid) {
            for(Tile tile : row) {
                fillTile(tile, fg);
            }
        }
    }

    void fillTile(Tile tile, FillGenerator fg) throws Exception {
        tile.setState(fg.fillGenerator(tile));
    }
}
