package edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.Algorithms;

import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.CollectionCondition.Conditions.NeighborCondition;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.ContinuePath.Paths.NeighborPath;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.SearchAlgorithm;

/*
 * This class is a type of SearchHeuristic whereby searching is 
 * based on finding neighbors to the left, right, up, and down 
 * and the collection conditional will be based on whether this neighbors 
 * share State objects (meaning tiles have identical States such as both being
 * red or both being the number 4). 
 */
public class NeighborAlgorithm extends SearchAlgorithm {
    public NeighborAlgorithm() {
        super(new NeighborPath(), new NeighborCondition());
    }
}
