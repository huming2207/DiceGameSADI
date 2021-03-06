package controllers.listeners;

import model.GameStatus;
import views.DiceAppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BetPlacementListener implements ActionListener
{
    private DiceAppFrame appFrame;
    private GameStatus gameStatus;

    public BetPlacementListener(GameStatus gameStatus)
    {
        this.appFrame = gameStatus.getAppFrame();
        this.gameStatus = gameStatus;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Integer bet = this.gameStatus.validateInput(
                this.appFrame.getToolbarPanel().getSetBetTextfield().getText());

        // Stop here if user didn't input a correct number (warning message has already shown)
        if(bet == null) return;

        // It's not possible to place a bet without a player...
        if(this.gameStatus.getSelectedPlayer() == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "You didn't select a correct player!",
                    "Selection error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Place bet via game engine
        this.gameStatus.getGameEngine().placeBet(this.gameStatus.getSelectedPlayer(), bet);

        // Roll player is a blocking method, so just create a new thread instead.
        new Thread(() -> this.gameStatus.getGameEngine().rollPlayer(
                this.gameStatus.getSelectedPlayer(), 1, 1000, 100)).start();
    }
}
