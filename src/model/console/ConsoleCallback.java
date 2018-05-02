package model.console;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.io.IOException;
import java.util.logging.*;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see model.interfaces.GameEngineCallback
 * 
 */
public class ConsoleCallback implements GameEngineCallback
{
	private Logger logger = Logger.getLogger("assignment1");


	public ConsoleCallback()
	{
		// FINE shows rolling output, INFO only shows result
		logger.setLevel(Level.FINE);

        // Workaround for "Level.FINE" level issue
        // Ref: https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger
        //      https://stackoverflow.com/questions/6315699/why-are-the-level-fine-logging-messages-not-showing

        // Firstly, disable parent handlers.
        logger.setUseParentHandlers(false);

        // Then, add our own handler.
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.FINE);
        logger.addHandler(consoleHandler);

        // By the way we also need to register a file handler...
        try {
            FileHandler fileHandler = new FileHandler("OutputTrace.txt");
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException | SecurityException exception) {
            logger.log(Level.SEVERE, "Failed to register FileHandler, I will not save logs to file then!");
            exception.printStackTrace();
        }
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
