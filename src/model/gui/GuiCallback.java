package model.gui;

import controllers.GameController;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import views.components.panel.InfoPanel;
import views.helpers.LabelUpdateHelper;

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
        LabelUpdateHelper.appendLabelTextAsync(
                infoPanel.getPlayerBetLabel(),
                String.format("%d+%d=%d; ",
                        dicePair.getDice1(), dicePair.getDice2(), dicePair.getDice1() + dicePair.getDice2()));
    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine)
    {
        LabelUpdateHelper.appendLabelTextAsync(
                infoPanel.getPlayerResultLabel(),
                String.format("%d+%d=%d", result.getDice1(), result.getDice2(), result.getDice1() + result.getDice2()));
    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine)
    {
        LabelUpdateHelper.appendLabelTextAsync(
                infoPanel.getHouseBetLabel(),
                String.format("%d+%d=%d;; ",
                        dicePair.getDice1(), dicePair.getDice2(), dicePair.getDice1() + dicePair.getDice2()));
    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine)
    {
        LabelUpdateHelper.appendLabelTextAsync(
                infoPanel.getHouseResultLabel(),
                String.format("%d+%d=%d", result.getDice1(), result.getDice2(), result.getDice1() + result.getDice2()));

        LabelUpdateHelper.updateLabelTextAsync(infoPanel.getPlayerBalanceLabel(), String.format("Balance: %d",
                this.gameController.getCurrentPlayer().getPoints()));
    }
}
