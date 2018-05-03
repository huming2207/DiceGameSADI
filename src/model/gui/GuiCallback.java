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

    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine)
    {

    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine)
    {

    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine)
    {

    }
}
