package controllers.listeners;

import controllers.GameController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import views.DiceAppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HouseBetListener implements ActionListener
{
    private DiceAppFrame appFrame;
    private GameEngine gameEngine;
    private GameController gameController;

    public HouseBetListener(GameController gameController)
    {
        this.appFrame = gameController.getAppFrame();
        this.gameController = gameController;
        this.gameEngine = gameController.getGameEngine();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(this.gameController.getSelectedPlayer() == null) {
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
}