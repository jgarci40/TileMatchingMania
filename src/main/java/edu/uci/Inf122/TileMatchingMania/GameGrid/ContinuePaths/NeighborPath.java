package edu.uci.Inf122.TileMatchingMania.GameGrid.ContinuePaths;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.GameGrid.ValidPaths;

public class NeighborPath extends ContinuePath {
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
