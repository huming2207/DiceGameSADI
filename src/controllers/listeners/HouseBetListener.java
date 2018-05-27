package controllers.listeners;

import model.GameStatus;
import model.interfaces.Player;
import views.DiceAppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HouseBetListener implements ActionListener
{
    private DiceAppFrame appFrame;
    private GameStatus gameStatus;

    public HouseBetListener(GameStatus gameStatus)
    {
        this.appFrame = gameStatus.getAppFrame();
        this.gameStatus = gameStatus;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // If there is no player selected, then something must went wrong in the user side.
        if(this.gameStatus.getSelectedPlayer() == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "House bet must comes after at least one player bet",
                    "Selection error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if all player has placed their bet
        for(Player player : this.gameStatus.getGameEngine().getAllPlayers()) {
            if(player.getRollResult() == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "At least one player(s) haven't placed bet yet, please check again",
                        "Selection error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Roll house is a blocking method, so just create a new thread instead.
        new Thread(() ->
                this.gameStatus.getGameEngine().rollHouse(1, 1000, 100)
        ).start();
    }
}
