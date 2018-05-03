package controllers;

import model.common.GameEngineImpl;
import model.common.SimplePlayer;
import model.gui.GuiCallback;
import model.interfaces.GameEngine;
import views.DiceAppFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

public class GameController
{
    private static GameController gameController = null;
    private DiceAppFrame appFrame;
    private final GameEngine gameEngine;
    private int playerId = 0;

    public GameController()
    {
        // Start UI with a new UI thread
        try {
            SwingUtilities.invokeAndWait(() -> this.appFrame = new DiceAppFrame(this));
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }


        // ...here I run game engine on "main" thread instead
        this.gameEngine = new GameEngineImpl();
        this.gameEngine.addGameEngineCallback(new GuiCallback(this));
        System.out.println(String.format("Main thread ID: %d", Thread.currentThread().getId()));
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
            JOptionPane.showMessageDialog(null, "Initial bet is not a number!", "Format error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a new player
        SimplePlayer player = new SimplePlayer(playerName, Integer.toString(this.playerId), initBet);
        this.gameEngine.addPlayer(player);

        // Add to combo box
        SwingUtilities.invokeLater(() -> this.appFrame.getToolbarPanel().getSelectionComboBox().addItem(playerName));
        System.out.println("Added player: " + player.toString());

        // Increase player ID every time after use
        this.playerId += 1;
    }

    public void handleBetPlacementRequest(ActionEvent event)
    {

    }

    public void handleHouseBetRequest(ActionEvent event)
    {
        new Thread(() -> this.gameEngine.rollHouse(1, 1000, 100)).start();
    }

    public DiceAppFrame getAppFrame()
    {
        return this.appFrame;
    }
}
