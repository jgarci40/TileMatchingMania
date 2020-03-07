package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.Game;

import edu.uci.Inf122.TileMatchingMania.GUI.Input.CoordinateInput;
import edu.uci.Inf122.TileMatchingMania.GUI.Input.Input;
import edu.uci.Inf122.TileMatchingMania.Game.Game;
import edu.uci.Inf122.TileMatchingMania.GameGrid.FillAlgorithm.Algorithms.RandomFillAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.CollectionCondition.Conditions.NeighborCondition;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.CollectionCondition.Conditions.StateCondition;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.ContinuePath.Paths.NeighborPath;
import edu.uci.Inf122.TileMatchingMania.GameGrid.SearchAlgorithm.SearchAlgorithm;
import edu.uci.Inf122.TileMatchingMania.GameGrid.Tile;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.BlueState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.EmptyState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.GreenState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.RedState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.BlackState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame02_RandomGrid.src.State.WhiteState;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.List;

class SameGameDefaultCollection extends StateCollection {
    public SameGameDefaultCollection() throws Exception {
        super();
        setDefaultState(new EmptyState());
        addState(new BlueState());
        addState(new GreenState());
        addState(new RedState());
    }
}

public class SameGame extends Game {
    public static int SAME_GAME_DEFAULT_ROWS = 10;
    public static int SAME_GAME_DEFAULT_COLS = 10;

    private void checkerboardFillGrid(StateCollection stateCollection) throws Exception {
        gameGrid.fillGrid(new RandomFillAlgorithm(stateCollection));
    }

    public SameGame() throws Exception {
        super(SAME_GAME_DEFAULT_ROWS, SAME_GAME_DEFAULT_COLS, new SameGameDefaultCollection());
        initGame();
    }

    public boolean initGame() throws Exception {
        checkerboardFillGrid(collections.get("default"));
        return true;
    }

    public void nextInput(Input input) throws Exception {
        CoordinateInput ci = (CoordinateInput) input;
        Tile startTile = gameGrid.getTile(ci.getRow(), ci.getCol());
        SearchAlgorithm sa = new SearchAlgorithm(new NeighborPath(), new NeighborCondition());
        List<Tile> matchingTiles = gameGrid.graphSearch(startTile, sa);
        // TODO: remove this print
        for (Tile t: matchingTiles) {
            System.out.println("row: " + t.getRow() + "\t" + "col: " + t.getCol());
            t.setState(new EmptyState());
        }

        //List<Tile> blackStates = gameGrid.search(new SearchAlgorithm(new NeighborPath(), new StateCondition(new BlackState())));
        //List<Tile> whiteStates = gameGrid.search(new SearchAlgorithm(new NeighborPath(), new StateCondition(new WhiteState())));
        //blackStates.forEach(e -> e.setState(new WhiteState()));
        //whiteStates.forEach((e -> e.setState(new BlackState())));
    }

    private void moveDown() {

    }


}
