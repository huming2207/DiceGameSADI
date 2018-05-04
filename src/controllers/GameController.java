package controllers;

import model.common.GameEngineImpl;
import model.console.SimplePlayer;
import model.gui.GuiCallback;
import model.gui.GuiPlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import views.DiceAppFrame;
import views.panel.InfoPanel;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

public class GameController
{
    private static GameController gameController = null;
    private DiceAppFrame appFrame;
    private final GameEngine gameEngine;
    private Player selectedPlayer;
    private int playerId = 0;

    public GameController()
    {
        // Start UI with a new UI thread
        try {
            SwingUtilities.invokeAndWait(() -> this.appFrame = new DiceAppFrame(this));
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }


        // Leave game engine on "main" thread instead
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
        SimplePlayer player = new GuiPlayer(Integer.toString(this.playerId), playerName, initBet);
        this.gameEngine.addPlayer(player);

        // Add to combo box
        SwingUtilities.invokeLater(() -> this.appFrame.getToolbarPanel().getSelectionComboBox().addItem(player));
        System.out.println("Added player: " + player.toString());

        // Increase player ID every time after use
        this.playerId += 1;
    }

    public void handleBetPlacementRequest(ActionEvent event)
    {
        int bet;

        try {
            bet = Integer.parseInt(this.appFrame.getToolbarPanel().getSetBetTextfield().getText());
        } catch(NumberFormatException numFormatException) {
            JOptionPane.showMessageDialog(
                    null,
                    "Initial bet is not a number!",
                    "Format error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(this.selectedPlayer == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "You didn't select a correct player!",
                    "Selection error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.gameEngine.placeBet(this.selectedPlayer, bet);
        new Thread(() -> this.gameEngine.rollPlayer(selectedPlayer, 1, 1000, 100))
                .start();
    }

    public void handleHouseBetRequest(ActionEvent event)
    {
        if(this.selectedPlayer == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "House bet must comes after at least one player bet",
                    "Selection error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        for(Player player : this.gameEngine.getAllPlayers()) {
            if(player.getRollResult() == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "At least one player(s) haven't placed bet yet, please check again",
                        "Selection error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        new Thread(() -> this.gameEngine.rollHouse(1, 1000, 100)).start();
    }

    public void handleComboBoxSelection(ActionEvent actionEvent)
    {
        // Clean up the label
        // Here I do not consider the concurrency issue as required in assignment spec
        this.appFrame.getInfoPanel().cleanUp();

        // Here I assume the player is not null, because only valid player will be shown in the combo box
        this.selectedPlayer = ((GuiPlayer)this.appFrame.getToolbarPanel().getSelectionComboBox().getSelectedItem());

        // Update balance label
        InfoPanel.updateLabelText(
                this.getAppFrame().getInfoPanel().getPlayerBalanceLabel(),
                String.format("Balance: %d", this.selectedPlayer.getPoints()));
    }

    public DiceAppFrame getAppFrame()
    {
        return this.appFrame;
    }

    public Player getCurrentPlayer()
    {
        return this.selectedPlayer;
    }
}
