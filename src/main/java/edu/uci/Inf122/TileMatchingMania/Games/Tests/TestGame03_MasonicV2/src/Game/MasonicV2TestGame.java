package edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.Game;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.CoordinateInput;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.Game.Game;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.Algorithms.CheckerBoardAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.CollectionCondition.Conditions.StateCondition;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.ContinuePath.Paths.NeighborPath;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.SearchAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.BlackState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.WhiteState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.MasonicInput;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.List;

class MasonicDefaultCollection extends StateCollection {
    public MasonicDefaultCollection() throws Exception {
        super();
        setDefaultState(new BlackState());
        addState(new WhiteState());
    }
}

public class MasonicV2TestGame extends Game {
    public static int MASONIC_V2_DEFAULT_ROWS = 10;
    public static int MASONIC_V2_DEFAULT_COLS = 10;

    private void checkerboardFillGrid(StateCollection stateCollection) throws Exception {
        gameGrid.fillGrid(new CheckerBoardAlgorithm(stateCollection));
    }

    public MasonicV2TestGame() throws Exception {
        super(MASONIC_V2_DEFAULT_ROWS, MASONIC_V2_DEFAULT_COLS, new MasonicDefaultCollection());
        initGame();
    }

    public boolean initGame() throws Exception {
        checkerboardFillGrid(collections.get("default"));
        return true;
    }

    public void nextInput(Input input) throws Exception {
        if(input instanceof MasonicInput) {
            List<Tile> blackStates = gameGrid.search(new SearchAlgorithm(new NeighborPath(), new StateCondition(new BlackState())));
            List<Tile> whiteStates = gameGrid.search(new SearchAlgorithm(new NeighborPath(), new StateCondition(new WhiteState())));
            blackStates.forEach(e -> e.setState(new WhiteState()));
            whiteStates.forEach((e -> e.setState(new BlackState())));
        }
        if(input instanceof CoordinateInput) {
            Tile t = gameGrid.getTile(((CoordinateInput) input).getRow(), ((CoordinateInput) input).getCol());
            if(t.getState().equivalent(new WhiteState())) {
                t.setState(new BlackState());
            } else {
                t.setState(new WhiteState());
            }
        }
    }
}
