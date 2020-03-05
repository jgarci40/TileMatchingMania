package edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.ContinuePaths;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.GameGrid.ValidPaths;

/*
 * This class provides a path algorithm to find its 4
 * neighboring nodes based on a matching condition of 
 * belonging to the same State. For example, two tiles
 * that are colored blue will share the same State and so
 * will meet the matching condition to be on the same path.
 */
public class NeighborPath extends ContinuePath {
	
	/*
	 * This method will search a path of 
	 * tiles in a grid and return valid paths.
	 * 
	 * @param tile The search starting point is a tile 
	 * in the grid.
	 * 
	 * @return ValidPaths The object that records what paths
	 * exists in a 4-way direction.
	 */
    @Override
    public ValidPaths continuePaths(Tile tile) {
        ValidPaths vp = new ValidPaths();
        Tile tL = (Tile)tile.getLeft();
        if(tL != null) {
            if(tile.getState().getClass() == tL.getState().getClass()) {
                vp.setLeft(true);
            } else {
                vp.setLeft(false);
            }
        } else {
            vp.setLeft(false);
        }

        Tile tR = (Tile)tile.getRight();
        if(tR != null) {
            if (tile.getState().getClass() == tR.getState().getClass()) {
                vp.setRight(true);
            } else {
                vp.setRight(false);
            }
        } else {
            vp.setRight(false);
        }

        Tile tU = (Tile)tile.getUp();
        if(tU != null) {
            if (tile.getState().getClass() == tU.getState().getClass()) {
                vp.setUp(true);
            } else {
                vp.setUp(false);
            }
        } else {
            vp.setUp(false);
        }

        Tile tD = (Tile)tile.getDown();
        if(tD != null) {
            if (tile.getState().getClass() == tD.getState().getClass()) {
                vp.setDown(true);
            } else {
                vp.setDown(false);
            }
        } else {
            vp.setDown(false);
        }
        return vp;
    }
}
