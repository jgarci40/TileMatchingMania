package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.Algorithms;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.Generators.DefaultGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess.Processes.LinearFillProcess;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

public class DefaultFillAlgorithm extends FillAlgorithm {
    public DefaultFillAlgorithm(StateCollection stateCollection) {
        super(new LinearFillProcess(), new DefaultGenerator(stateCollection));
    }
}
