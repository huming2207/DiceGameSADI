package controllers.listeners;

import controllers.GameController;
import views.DiceAppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BetPlacementListener implements ActionListener
{
    private DiceAppFrame appFrame;
    private GameController gameController;

    public BetPlacementListener(GameController gameController)
    {
        this.appFrame = gameController.getAppFrame();
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Integer bet = this.gameController.validateInput(
                this.appFrame.getToolbarPanel().getSetBetTextfield().getText());

        // Stop here if user didn't input a correct number (warning message has already shown)
        if(bet == null) return;

        // It's not possible to place a bet without a player...
        if(this.gameController.getSelectedPlayer() == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "You didn't select a correct player!",
                    "Selection error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Place bet via game engine
        this.gameController.getGameEngine().placeBet(this.gameController.getSelectedPlayer(), bet);

        // Roll player is a blocking method, so just create a new thread instead.
        new Thread(() ->
                this.gameController.getGameEngine().rollPlayer(
                        this.gameController.getSelectedPlayer(), 1, 1000, 100)
        ).start();
    }
}
