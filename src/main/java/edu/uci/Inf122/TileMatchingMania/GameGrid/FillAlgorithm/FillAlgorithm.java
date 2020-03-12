package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.FillGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess.FillProcess;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;

/*
 * A game grid will be filled with tiles and the tiles will always have 
 * some State. This class is a combination of filling a game grid in some 
 * manner such a linearly or randomly and generating tiles that a fill algorithm
 * will use.
 */
public class FillAlgorithm {
    private FillProcess fillProcess;
    private FillGenerator fillGenerator;

    /*
     * FillAlgorithm constructor. 
     * 
     * @param fillProcess The way a grid will be populated.
     * @param fillGenerator The type of State tiles will take on will depend on the generator.
     */
    public FillAlgorithm(FillProcess fillProcess, FillGenerator fillGenerator) {
        this.fillProcess = fillProcess;
        this.fillGenerator = fillGenerator;
    }

    /*
     * A fill process is based on the FillProcess type and it will 
     * use a fill generator to help set the tile States.
     */
    public void fillProcess(Tile[][] tileGrid) throws Exception {
        fillProcess.fillProcess(tileGrid, fillGenerator);
    }
}
