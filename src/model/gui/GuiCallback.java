package model.gui;

import controllers.GameController;
import controllers.GuiLogHandler;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuiCallback implements GameEngineCallback
{
    private GameController gameController;
    private Logger logger = Logger.getLogger("asg2-gui");

    public GuiCallback(GameController gameController)
    {
        this.gameController = gameController;

        // FINE shows rolling output, INFO only shows result
        logger.setLevel(Level.FINE);
        logger.setUseParentHandlers(false);

        // Then, add our own handler.
        logger.addHandler(new GuiLogHandler(gameController.getAppFrame().getDiceConsole()));
    }

    @Override
    public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
    {
        logger.log(Level.FINE, String.format("%s: ROLLING %s", player.getPlayerName(), dicePair.toString()));
    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine)
    {
        // final results logged at Level.INFO
        logger.log(Level.INFO, String.format("%s: *RESULT* %s", player.getPlayerName(), result.toString()));
    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine)
    {
        logger.log(Level.FINE, String.format("House: ROLLING %s", dicePair.toString()));
    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine)
    {
        logger.log(Level.FINE, String.format("House: *RESULT* %s", result.toString()));

        for(Player player : gameEngine.getAllPlayers()) {
            displayFinalResult(player);
        }
    }

    private void displayFinalResult(Player currentPlayer)
    {
        logger.log(Level.INFO, String.format("Player: %s", currentPlayer.toString()));
    }
}
