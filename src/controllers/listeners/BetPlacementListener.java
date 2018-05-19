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

        if(bet == null) return;

        if(this.gameController.getSelectedPlayer() == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "You didn't select a correct player!",
                    "Selection error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.gameController.getGameEngine().placeBet(this.gameController.getSelectedPlayer(), bet);
        new Thread(() ->
                this.gameController.getGameEngine().rollPlayer(
                        this.gameController.getSelectedPlayer(), 1, 1000, 100)
        ).start();
    }
}
