package edu.uci.Inf122.TileMatchingMania.GameGrid.SearchHeuristics;

import edu.uci.Inf122.TileMatchingMania.GameGrid.CollectionConditions.NeighborCondition;
import edu.uci.Inf122.TileMatchingMania.GameGrid.ContinuePaths.NeighborPath;

public class NeighborHeuristic extends SearchHeuristic {
    public NeighborHeuristic() {
        super(new NeighborPath(), new NeighborCondition());
    }
}
