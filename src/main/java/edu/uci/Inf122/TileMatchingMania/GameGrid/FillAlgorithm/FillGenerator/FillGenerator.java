package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

public abstract class FillGenerator {
    StateCollection stateCollection;
    public abstract Tile fillGenerator(Tile tile);
}
