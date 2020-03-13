package edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm;

import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.CollectionCondition.CollectionCondition;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.ContinuePath.ContinuePath;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.GameGrid.ValidPaths;

/*
 * A tile matching game will have a search algorithm which is defined
 * as a combination of a path search algorithm and a collection/matching condition.
 */
public class SearchAlgorithm {
    private ContinuePath ct;
    private CollectionCondition cd;

    /*
     * SearchAlgorithm constructor.
     * 
     * @param ct The way a game searches the grid.
     * @param The way the game collects or matches tiles.
     */
    public SearchAlgorithm(ContinuePath ct, CollectionCondition cd) {
        this.ct = ct;
        this.cd = cd;
    }

    /*
     * Search the grid to find valid paths.
     * 
     * @param tile The starting point for searching.
     * 
     * @param ValidPaths A data structure that will contain the valid 
     * paths where possible matching tiles will be found.
     */
    public ValidPaths continuePath(Tile tile) {
        return ct.continuePaths(tile);
    }

    /*
     * Determine if a neighboring tile can be collected so that it 
     * may be modified or removed.
     * 
     * @param tile The starting point for searching.
     * 
     * @return True if a neighbor is a candidate to be collected, false otherwise.
     */
    public boolean collectionCondition(Tile tile) {
        return cd.collectionCondition(tile);
    }
}
