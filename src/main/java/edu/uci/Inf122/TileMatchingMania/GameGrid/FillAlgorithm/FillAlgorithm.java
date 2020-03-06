package edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm;

import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillGenerator.FillGenerator;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.FillProcess.FillProcess;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;

public class FillAlgorithm {
    private FillProcess fillProcess;
    private FillGenerator fillGenerator;

    public FillAlgorithm(FillProcess fillProcess, FillGenerator fillGenerator) {
        this.fillProcess = fillProcess;
        this.fillGenerator = fillGenerator;
    }

    public void fillProcess(Tile[][] tileGrid) throws Exception {
        fillProcess.fillProcess(tileGrid, fillGenerator);
    }
}
