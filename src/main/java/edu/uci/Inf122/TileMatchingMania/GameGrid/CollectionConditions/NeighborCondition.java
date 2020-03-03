package edu.uci.Inf122.TileMatchingMania.GameGrid.CollectionConditions;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;

public class NeighborCondition extends CollectionCondition {
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
