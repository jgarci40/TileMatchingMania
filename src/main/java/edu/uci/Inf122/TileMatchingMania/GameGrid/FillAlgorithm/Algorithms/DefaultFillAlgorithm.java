package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.Algorithms;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.Generators.DefaultGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess.Processes.LinearFillProcess;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

/*
 * A default fill algorithm linearly fills a grid from top to bottom and 
 * each tile will take on the default State through the default generator 
 * process.
 */
public class DefaultFillAlgorithm extends FillAlgorithm {
	/*
	 * DefaultFillAlgorithm constructor.
	 * 
	 * @param stateCollection Hold State objects used in the game.
	 */
    public DefaultFillAlgorithm(StateCollection stateCollection) {
        super(new LinearFillProcess(), new DefaultGenerator(stateCollection));
    }
}
