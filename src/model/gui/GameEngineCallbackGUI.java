package model.gui;

import controllers.GameController;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import views.components.panel.InfoPanel;
import views.components.panel.StatusBarPanel;

import javax.swing.*;

public class GameEngineCallbackGUI implements GameEngineCallback
{

    private GameController gameController;
    private InfoPanel infoPanel;
    private StatusBarPanel statusBarPanel;

    public GameEngineCallbackGUI(GameController gameController)
    {
        this.gameController = gameController;
        this.infoPanel = gameController.getAppFrame().getInfoPanel();
        this.statusBarPanel = gameController.getAppFrame().getStatusBarPanel();
    }

    @Override
    public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
    {
        this.setTextToLabel(this.statusBarPanel.getLeftLabel(),
                String.format("Player betting: %s", player));

        this.appendTextToLabel(this.infoPanel.getPlayerBetLabel(), String.format("%d+%d=%d; ",
                dicePair.getDice1(), dicePair.getDice2(), dicePair.getDice1() + dicePair.getDice2()));
    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine)
    {
        this.setTextToLabel(this.statusBarPanel.getLeftLabel(),
                String.format("Player done: %s", player));

        this.appendTextToLabel(this.infoPanel.getPlayerResultLabel(), String.format("%d+%d=%d",
                result.getDice1(), result.getDice2(), result.getDice1() + result.getDice2()));

        this.setTextToLabel(this.statusBarPanel.getRightLabel(),
                String.format("Player result: %d", result.getDice1() + result.getDice2()));
    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine)
    {
        this.setTextToLabel(this.statusBarPanel.getLeftLabel(),"House betting...");

        this.appendTextToLabel(this.infoPanel.getHouseBetLabel(), String.format("%d+%d=%d;; ",
                dicePair.getDice1(), dicePair.getDice2(), dicePair.getDice1() + dicePair.getDice2()));
    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine)
    {
        this.setTextToLabel(this.statusBarPanel.getLeftLabel(),"House done");

        this.appendTextToLabel(this.infoPanel.getHouseResultLabel(), String.format("%d+%d=%d",
                result.getDice1(), result.getDice2(), result.getDice1() + result.getDice2()));

        this.setTextToLabel(this.infoPanel.getPlayerBalanceLabel(), String.format("Balance: %d",
                this.gameController.getCurrentPlayer().getPoints()));

        this.setTextToLabel(this.statusBarPanel.getRightLabel(),
                String.format("House result: %d", result.getDice1() + result.getDice2()));
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
