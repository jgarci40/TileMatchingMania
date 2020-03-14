package edu.uci.Inf122.TileMatchingMania.GUI.GameRunner;

import edu.uci.Inf122.TileMatchingMania.GUI.GameBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.GUI.SameGameBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.The2048GameBridgePair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameRunner extends JFrame {
    List<GameBridgePair> games;
    int boxSize;
    boolean player1LoggedIn;
    LoginPanel player1LoginPanel;
    boolean player2LogginedIn;

    public GameRunner() throws Exception {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        boxSize = 64;
        player1LoggedIn = true;
        player2LogginedIn = true;
        setLayout(new GridLayout(3, 1));
        games = new ArrayList<>();
        games.add(new The2048GameBridgePair());
        games.add(new SameGameBridgePair());

        player1LoginPanel = new LoginPanel("Player1");
        player1LoginPanel.setCallBack(() -> System.out.println("ASdasd"));
        add(player1LoginPanel);
        pack();
    }
}
