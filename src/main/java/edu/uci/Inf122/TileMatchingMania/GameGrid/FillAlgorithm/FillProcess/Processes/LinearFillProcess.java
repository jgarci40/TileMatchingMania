package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess.Processes;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.FillGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess.FillProcess;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

//TODO add tests for this class
/*
 * Fill a game grid in a linear manner starting from the top left tile
 * and ending at the bottom right tile.
 */
public class LinearFillProcess extends FillProcess {
	/*
	 * Fill a game grid by populating each tile with a State object
	 * in a linear manner.
	 * 
	 * @param tileGrid The 2D grid that contains all the Tiles.
	 * @param fg A generator that will support setting each tile State.
	 *
	 */
    public void fillProcess(Tile[][] tileGrid, FillGenerator fg) throws Exception {
        for(Tile[] row : tileGrid) {
            for(Tile tile : row) {
                fillTile(tile, fg);
            }
        }
    }

    /*
     * Set a tile's State object.
     * 
     * @param tile A specific tile that comes from the grid.
	 * @param fg A generator that will support setting each tile State.
     */
    protected void fillTile(Tile tile, FillGenerator fg) throws Exception {
        tile.setState(fg.fillGenerator(tile));
    }
}
