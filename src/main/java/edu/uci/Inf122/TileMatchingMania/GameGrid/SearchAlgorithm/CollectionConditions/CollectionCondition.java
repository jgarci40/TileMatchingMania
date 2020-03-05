package edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.CollectionConditions;

import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;

/*
 * Tile based games will have a collection condition. 
 * This means that a tile is modified or removed in some 
 * way depending on the condition created by game developers.
 * For example, a collection condition can be if two Tile 
 * objects contain the same State which may be the color red
 * or the State may be an integer. 
 */
public abstract class CollectionCondition {
    public abstract boolean collectionCondition(Tile tile);
}
