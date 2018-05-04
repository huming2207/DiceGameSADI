package model.gui;

import controllers.GameController;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import views.panel.InfoPanel;

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
        InfoPanel.appendLabelText(
                infoPanel.getPlayerBetLabel(),
                String.format("%d+%d=%d; ",
                        dicePair.getDice1(), dicePair.getDice2(), dicePair.getDice1() + dicePair.getDice2()));
    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine)
    {
        InfoPanel.appendLabelText(
                infoPanel.getPlayerResultLabel(),
                String.format("%d+%d=%d", result.getDice1(), result.getDice2(), result.getDice2() + result.getDice2()));
    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine)
    {
        InfoPanel.appendLabelText(
                infoPanel.getHouseBetLabel(),
                String.format("%d+%d=%d;; ",
                        dicePair.getDice1(), dicePair.getDice2(), dicePair.getDice1() + dicePair.getDice2()));
    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine)
    {
        InfoPanel.appendLabelText(
                infoPanel.getHouseResultLabel(),
                String.format("%d+%d=%d", result.getDice1(), result.getDice2(), result.getDice2() + result.getDice2()));

        int balance = this.gameController.getCurrentPlayer().getPoints();

        InfoPanel.updateLabelText(infoPanel.getPlayerBalanceLabel(), String.format("Balance: %d", balance));
    }
}
