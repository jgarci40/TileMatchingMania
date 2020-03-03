package edu.uci.Inf122.TileMatchingMania.GameGrid.ContinuePaths;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.GameGrid.ValidPaths;

public abstract class ContinuePath {
    public abstract ValidPaths continuePaths(Tile tile);
}
