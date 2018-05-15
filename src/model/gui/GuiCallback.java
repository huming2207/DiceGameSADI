package model.gui;

import controllers.GameController;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import views.components.panel.InfoPanel;

import javax.swing.*;

public class GuiCallback implements GameEngineCallback
{

    private GameController gameController;
    private InfoPanel infoPanel;

    public GuiCallback(GameController gameController)
    {
        this.gameController = gameController;
        this.infoPanel = gameController.getAppFrame().getInfoPanel();
    }

    @Override
    public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
    {
        this.appendTextToLabel(this.infoPanel.getPlayerBetLabel(), String.format("%d+%d=%d; ",
                dicePair.getDice1(), dicePair.getDice2(), dicePair.getDice1() + dicePair.getDice2()));
    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine)
    {
        this.appendTextToLabel(this.infoPanel.getPlayerResultLabel(), String.format("%d+%d=%d",
                result.getDice1(), result.getDice2(), result.getDice1() + result.getDice2()));
    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine)
    {
        this.appendTextToLabel(this.infoPanel.getHouseBetLabel(), String.format("%d+%d=%d;; ",
                dicePair.getDice1(), dicePair.getDice2(), dicePair.getDice1() + dicePair.getDice2()));
    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine)
    {
        this.appendTextToLabel(this.infoPanel.getHouseResultLabel(), String.format("%d+%d=%d",
                result.getDice1(), result.getDice2(), result.getDice1() + result.getDice2()));

        this.setTextToLabel(this.infoPanel.getPlayerBalanceLabel(), String.format("Balance: %d",
                this.gameController.getCurrentPlayer().getPoints()));
    }

    private void appendTextToLabel(JLabel label, String text)
    {
        SwingUtilities.invokeLater(() -> label.setText(label.getText() + text));
    }

    private void setTextToLabel(JLabel label, String text)
    {
        SwingUtilities.invokeLater(() -> label.setText(text));
    }
}
