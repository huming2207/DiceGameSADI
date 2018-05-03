package controllers;

import model.common.GameEngineImpl;
import model.common.SimplePlayer;
import model.interfaces.GameEngine;
import views.DiceAppFrame;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameController
{
    private static GameController gameController = null;
    private DiceAppFrame appFrame;
    private final GameEngine gameEngine;
    private int playerId = 0;

    public GameController()
    {
        SwingUtilities.invokeLater(() -> this.appFrame = new DiceAppFrame(this));
        this.gameEngine = new GameEngineImpl();
    }

    /**
     * Singleton design
     * @return GameController
     */
    public static GameController getGameController()
    {
        if(gameController == null) {
            gameController = new GameController();
        }

        return gameController;
    }

    public void handleAddPlayerRequest(ActionEvent event)
    {
        String playerName = this.appFrame.getToolbarPanel().getNameTextfield().getText();

        int initBet = 0;

        // Try parse initial bet
        try {
            initBet = Integer.parseInt(this.appFrame.getToolbarPanel().getInitialBetTextfield().getText());
        } catch (NumberFormatException numFormatException) {
            // TODO: error handling
        }

        this.gameEngine.addPlayer(new SimplePlayer(playerName, Integer.toString(this.playerId), initBet));

        // Increase player ID every time after use
        this.playerId += 1;
    }

    public void handleBetPlacementRequest(ActionEvent event)
    {

    }

    public DiceAppFrame getAppFrame()
    {
        return this.appFrame;
    }
}
