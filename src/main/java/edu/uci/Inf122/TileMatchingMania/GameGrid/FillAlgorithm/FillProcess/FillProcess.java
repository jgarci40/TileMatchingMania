package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.FillGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

/*
 * A fill process will populate tiles in a grid in some specified manner 
 * depending on what fill algorithm is selected. For example, a gird can 
 * be filled linearly from top to bottom if a linear fill is used.
 */
public abstract class FillProcess {
	/*
	 * To completely fill a grid based where tile States are determiend by the
	 * fill generator provided.
	 * 
	 *  @param tileGrid The 2D grid that contains all the Tiles.
	 *  @param fg A generator that will support setting each tile State.
	 */
    public abstract void fillProcess(Tile[][] tileGrid, FillGenerator fg) throws Exception;

    /*
     * Set a tile's State object.
     * 
     * @param tile A specific tile that comes from the grid.
	 * @param fg A generator that will support setting each tile State.
     */
    protected abstract void fillTile(Tile tile, FillGenerator fg) throws Exception;
}
