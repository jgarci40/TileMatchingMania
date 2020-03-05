package edu.uci.Inf122.TileMatchingMania.GameGrid.CollectionConditions;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;

/*
 * This class collects tiles based on a neighborhood condition;
 * meaning Tiles are matched with each other if they have the 
 * same State and are left, right, up, or down neighbors. 
 */
public class NeighborCondition extends CollectionCondition {
	/*
	 * Collect the 4 neighboring tiles if the State for each
	 * Title is the same as the starting tile.
	 * 
	 * @param tile This is the current Tile evaluating it's 4 neighbors.
	 * 
	 * @return boolean False if no match candidate exists, true otherwise
	 * if 1 of the 4 neighbors are a match. 
	 */
    public boolean collectionCondition(Tile tile) {
        Tile tL = (Tile)tile.getLeft();
        if(tL != null) {
            if(tile.getState().getClass() == tL.getState().getClass()) return true;
        }

        Tile tR = (Tile)tile.getRight();
        if(tR != null) {
            if(tile.getState().getClass() == tR.getState().getClass()) return true;
        }

        Tile tU = (Tile)tile.getUp();
        if(tU != null) {
            if(tile.getState().getClass() == tU.getState().getClass()) return true;
        }

        Tile tD = (Tile)tile.getDown();
        if(tD != null) {
            if(tile.getState().getClass() == tD.getState().getClass()) return true;
        }

        return false;
    }
}
