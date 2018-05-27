package controllers.listeners;

import model.GameStatus;
import model.SimplePlayer;
import model.GuiPlayer;
import views.DiceAppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerListener implements ActionListener
{
    private DiceAppFrame appFrame;
    private GameStatus gameStatus;

    public AddPlayerListener(GameStatus gameStatus)
    {
        this.appFrame = gameStatus.getAppFrame();
        this.gameStatus = gameStatus;
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        String playerName = this.appFrame.getToolbarPanel().getNameTextfield().getText();

        // Set initial bet value, if it's not an integer, then show a warning to user.
        Integer initBet  = this.gameStatus.validateInput(
                this.appFrame.getToolbarPanel().getInitialBetTextfield().getText());

        // Stop here if user didn't input a correct number (warning message has already shown)
        if(initBet == null) return;

        // Create a new player
        SimplePlayer player = new GuiPlayer(Integer.toString(this.gameStatus.getPlayerId()), playerName, initBet);
        this.gameStatus.getGameEngine().addPlayer(player);

        // Add to combo box
        SwingUtilities.invokeLater(() -> this.appFrame.getToolbarPanel().getSelectionComboBox().addItem(player));
        System.out.println("Added player: " + player.toString());

        // Increase player ID
        this.gameStatus.setPlayerId(this.gameStatus.getPlayerId() + 1);
    }
}
