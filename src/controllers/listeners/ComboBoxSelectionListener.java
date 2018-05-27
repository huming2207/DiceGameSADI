package controllers.listeners;

import model.GameStatus;
import model.GuiPlayer;
import views.DiceAppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxSelectionListener implements ActionListener
{
    private DiceAppFrame appFrame;
    private GameStatus gameStatus;

    public ComboBoxSelectionListener(GameStatus gameStatus)
    {
        this.gameStatus = gameStatus;
        this.appFrame = gameStatus.getAppFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Clean up the label
        // Here I do not consider the concurrency issue as required in assignment spec
        this.appFrame.getInfoPanel().cleanUp();

        // Here I assume the player is not null, because only valid player will be shown in the combo box
        this.gameStatus.setSelectedPlayer(
                (GuiPlayer)this.appFrame.getToolbarPanel().getSelectionComboBox().getSelectedItem());

        // Update balance label
        JLabel playerBalanceLabel = this.appFrame.getInfoPanel().getPlayerBalanceLabel();
        playerBalanceLabel.setText(String.format("Balance: %d", this.gameStatus.getSelectedPlayer().getPoints()));
    }
}
