package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.Algorithms;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.Generators.DefaultGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.Generators.RandomGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess.Processes.LinearFillProcess;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

/*
 * A random fill algorithm linearly fills a grid from top to bottom and 
 * each tile will take on a random State among the random States stored
 * in FillAlgorithm.
 */
public class RandomFillAlgorithm extends FillAlgorithm {
	/*
	 * DefaultFillAlgorithm constructor.
	 * 
	 * @param stateCollection Hold State objects used in the game.
	 */
    public RandomFillAlgorithm(StateCollection stateCollection) {
        super(new LinearFillProcess(), new RandomGenerator(stateCollection));
    }
}
