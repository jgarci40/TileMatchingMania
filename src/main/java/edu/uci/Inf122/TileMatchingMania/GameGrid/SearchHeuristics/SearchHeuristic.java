package edu.uci.Inf122.TileMatchingMania.GameGrid.SearchHeuristics;

import edu.uci.Inf122.TileMatchingMania.GameGrid.CollectionConditions.CollectionCondition;
import edu.uci.Inf122.TileMatchingMania.GameGrid.ContinuePaths.ContinuePath;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.GameGrid.ValidPaths;

public class SearchHeuristic {
    private ContinuePath ct;
    private CollectionCondition cd;

    public SearchHeuristic(ContinuePath ct, CollectionCondition cd) {
        this.ct = ct;
        this.cd = cd;
    }

    public ValidPaths continuePath(Tile t) {
        return ct.continuePaths(t);
    }

    public boolean collectionCondition(Tile t) {
        return cd.collectionCondition(t);
    }
}
