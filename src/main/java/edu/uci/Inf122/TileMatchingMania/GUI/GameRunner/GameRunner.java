package edu.uci.Inf122.TileMatchingMania.GUI.GameRunner;

import edu.uci.Inf122.TileMatchingMania.GUI.*;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.GUI.SameGameBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.PanelBridgePair;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI.The2048GameBridgePair;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameRunner extends JFrame {
    List<GameBridgePair> games;
    int boxSize;
    boolean player1LoggedIn;
    LoginPanel playerLoginPanel;
    boolean player2LoggedIn;
    GameBridgePair selectedGame;
    Map<String, GameBridgePair> gameMap;
    ListPanel s;
    List<PanelBridgePair> activeGames;
    ScorePanel scorePanel1;
    ScorePanel scorePanel2;

    The2048GameBridgePair player2 = new The2048GameBridgePair();

    private void handlePlayer1Login() {
        player1LoggedIn = true;
        remove(playerLoginPanel);
        pack();
        showGameList();
    }

    private void handlePlayer2Login() throws Exception {
        player2LoggedIn = true;
        remove(playerLoginPanel);
        pack();
        startSinglePlayer();
        startSecondPlayer();
    }

    private void setupPlayer1Login() {
        playerLoginPanel = new LoginPanel();
        playerLoginPanel.setText("Player1");
        playerLoginPanel.setCallBack(() -> { handlePlayer1Login(); });
        add(playerLoginPanel);
        pack();
    }

    private void setupPlayer2Login() {
        playerLoginPanel = new LoginPanel();
        playerLoginPanel.setText("Player2");
        playerLoginPanel.setCallBack(() -> {
            try {
                handlePlayer2Login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        add(playerLoginPanel);
        pack();
    }

    private void handleSelectGame(String str) {
        selectedGame = gameMap.get(str);
        remove(s);
        pack();
        showPlayerSelectList();
    }

    private void startSinglePlayer() throws Exception {
        JPanel firstPanel = new JPanel();
        BasicMouseListener bml = new BasicMouseListener(activeGames, boxSize);
        BasicKeyListener bkl = new BasicKeyListener(activeGames);
        addKeyListener(bkl);
        firstPanel.addMouseListener(bml);
        firstPanel.addKeyListener(bkl);
        scorePanel1 = new ScorePanel();
        firstPanel.add(scorePanel1);

        Map<String, StateCollection> collections = selectedGame.getGame().getCollections();
        ConverterGenerator converterGenerator = selectedGame.getBridge().getConverter();
        Map<String, StateToDrawableConverter> converters = converterGenerator.generate(collections);

        PanelBridgePair panelBridgePair = new PanelBridgePair(selectedGame, new GamePanel(selectedGame.getGame(), boxSize, converters), scorePanel1);

        firstPanel.add(panelBridgePair.getGamePanel());
        panelBridgePair.getGamePanel().updateView();
        activeGames.add(panelBridgePair);
        add(firstPanel);
        pack();
    }

    private void startSecondPlayer() throws Exception {
        JPanel secondPanel = new JPanel();
        scorePanel2 = new ScorePanel();
        BasicKeyListener bkl = new BasicKeyListener(activeGames);
        scorePanel2.addKeyListener(bkl);
        secondPanel.add(scorePanel2);

        Map<String, StateCollection> collections = player2.getGame().getCollections();
        ConverterGenerator converterGenerator = player2.getBridge().getConverter();
        Map<String, StateToDrawableConverter> converters = converterGenerator.generate(collections);

        player2.getBridge().setKeyToInputMap(player2.getBridge().getPlayer2());
        PanelBridgePair panelBridgePair = new PanelBridgePair(player2, new GamePanel(player2.getGame(), boxSize, converters), scorePanel2);

        secondPanel.add(panelBridgePair.getGamePanel());
        panelBridgePair.getGamePanel().updateView();
        activeGames.add(panelBridgePair);
        add(secondPanel);
        pack();
    }



    private void handleSelectPlayers(String str) throws Exception {
        remove(s);
        pack();
        if(str.compareTo("1") == 0) {
            startSinglePlayer();
        } else {
            setupPlayer2Login();
        }
    }

    private void showGameList() {
        //create a object
        s = new ListPanel();

        //String array to store weekdays
        String gameNames[] = new String[games.size()];
        gameMap = new HashMap<>();
        for(int i = 0; i < games.size(); i++) {
            gameNames[i] = games.get(i).getBridge().getName();
            gameMap.put(gameNames[i], games.get(i));
        }
        s.setCallBack((str) -> { handleSelectGame((String)str); });
        s.addList(gameNames);
        add(s);
        pack();
    }

    private void showPlayerSelectList() {
        s = new ListPanel();
        String numPlayers[] = new String[selectedGame.getBridge().getMaxPlayers()];
        for(int i = 0; i < selectedGame.getBridge().getMaxPlayers(); i++) {
            numPlayers[i] = (i + 1) + "";
        }
        s.addList(numPlayers);
        s.setCallBack((str) -> {
            try {
                handleSelectPlayers((String)str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        add(s);
        pack();
    }

    public GameRunner() throws Exception {
        activeGames = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        boxSize = 64;
        player1LoggedIn = false;
        player2LoggedIn = false;
        setLayout(new GridLayout(3, 1));
        games = new ArrayList<>();
        games.add(new The2048GameBridgePair());
        games.add(new SameGameBridgePair());

        setupPlayer1Login();

    }
}
