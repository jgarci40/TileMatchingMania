package edu.uci.Inf122.TileMatchingMania.GameGrid.ContinuePaths;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.GameGrid.ValidPaths;

/*
 * This abstract class specifies path methods based on search.
 */
public abstract class ContinuePath {
	/*
	 * This method will search a path of 
	 * tiles/nodes in a grid and return valid paths. 
	 */
    public abstract ValidPaths continuePaths(Tile tile);
}
