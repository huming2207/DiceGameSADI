package controllers;

import model.common.GameEngineImpl;
import model.common.SimplePlayer;
import model.console.ConsoleCallback;
import model.gui.GuiCallback;
import model.gui.GuiPlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import views.DiceAppFrame;
import views.components.panel.InfoPanel;
import views.helpers.LabelUpdateHelper;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

public class GameController
{
    private DiceAppFrame appFrame;
    private final GameEngine gameEngine;
    private Player selectedPlayer;
    private int playerId = 0;

    public GameController()
    {
        DialogController dialogController = new DialogController();

        // Start UI with a new UI thread
        try {
            SwingUtilities.invokeAndWait(() -> this.appFrame = new DiceAppFrame(this, dialogController));
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }


        // Leave game engine on "main" thread instead
        this.gameEngine = new GameEngineImpl();
        this.gameEngine.addGameEngineCallback(new GuiCallback(this));
        this.gameEngine.addGameEngineCallback(new ConsoleCallback());
        System.out.println(String.format("Main thread ID: %d", Thread.currentThread().getId()));
    }


    public void handleAddPlayerRequest(ActionEvent event)
    {
        String playerName = this.appFrame.getToolbarPanel().getNameTextfield().getText();

        Integer initBet;

        if((initBet = validateInput(this.appFrame.getToolbarPanel().getInitialBetTextfield().getText())) == null)
            return;

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
        Integer bet;

        if((bet = validateInput(this.appFrame.getToolbarPanel().getSetBetTextfield().getText())) == null)
            return;

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
        LabelUpdateHelper.updateLabelTextAsync(
                this.getAppFrame().getInfoPanel().getPlayerBalanceLabel(),
                String.format("Balance: %d", this.selectedPlayer.getPoints()));
    }

    public void handleQuitEvent(ActionEvent actionEvent)
    {
        System.exit(0);
    }



    public DiceAppFrame getAppFrame()
    {
        return this.appFrame;
    }

    public Player getCurrentPlayer()
    {
        return this.selectedPlayer;
    }

    private Integer validateInput(String textBoxInput)
    {
        int initBet = 0;

        try {
            initBet = Integer.parseInt(textBoxInput);
        } catch (NumberFormatException numFormatException) {
            JOptionPane.showMessageDialog(null, "Initial bet is not a number!",
                    "Format error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return initBet;
    }
}
