package model;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see model.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
	private Logger logger = Logger.getLogger("assignment1");

	public GameEngineCallbackImpl()
	{
		// FINE shows rolling output, INFO only shows result
		logger.setLevel(Level.FINE);
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
		logger.log(Level.FINE, String.format("%s: *RESULT* %s", player.getPlayerName(), result.toString()));
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine)
	{
        logger.log(Level.FINE, String.format("House: ROLLING %s", dicePair.toString()));
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine)
	{
        logger.log(Level.FINE, String.format("House: ROLLING %s", result.toString()));
	}

    @Override
    public void displayResult(Collection<Player> players, DicePair houseResult)
    {
        for(Player player : players) {
            int finalResult  = player.getRollResult().getDice1() + player.getRollResult().getDice2();
            int finalHouseResult = houseResult.getDice1() + houseResult.getDice2();

            // Condition: if this player's dice pair result is lower than the house result, then do the deduction
            if(finalResult < finalHouseResult) {
                player.setPoints(player.getPoints() - player.getBet());
            }
        }
    }

}
