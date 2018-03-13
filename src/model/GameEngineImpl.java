package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class GameEngineImpl implements GameEngine
{
    private Collection<Player> playerList;
    private GameEngineCallback callback;

    public GameEngineImpl()
    {
        playerList = new ArrayList<>();
    }

    @Override
    public boolean placeBet(Player player, int bet)
    {
        return player.placeBet(bet);
    }

    @Override
    public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement)
    {
        int rollCount = (finalDelay - initialDelay) / delayIncrement;
        int delay = initialDelay;

        if (rollCount < 1) rollCount = 1; // Just in case for someone input something wrong...

        for (int count = 0; count < rollCount; count += 1) {
            callback.intermediateResult(player, new DicePairImpl(), this);
            delay += delayIncrement;
            try {
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        callback.result(player, new DicePairImpl(), this);
    }

    @Override
    public void rollHouse(int initialDelay, int finalDelay, int delayIncrement)
    {
        int rollCount = (finalDelay - initialDelay) / delayIncrement;
        int delay = initialDelay;

        if (rollCount < 1) rollCount = 1; // Just in case for someone input something wrong...

        for (int count = 0; count < rollCount; count += 1) {
            callback.intermediateHouseResult(new DicePairImpl(), this);
            delay += delayIncrement;
            try {
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Generate the house result
        DicePair dicePair = new DicePairImpl();


        // Iterate the players and print for comparing with the house to decide who win or who lose
        for(Player player : playerList) {
            int finalResult  = player.getRollResult().getDice1() + player.getRollResult().getDice2();
            int finalHouseResult = dicePair.getDice1() + dicePair.getDice2();

            // Condition: if this player's dice pair result is lower than the house result, then do the deduction
            if(finalResult < finalHouseResult) {
                player.setPoints(player.getPoints() - player.getBet());
            }
        }

        // Log the final result AFTER the comparison
        callback.houseResult(dicePair, this);

    }

    @Override
    public void addPlayer(Player player)
    {
        playerList.add(player);
    }

    @Override
    public Player getPlayer(String id)
    {
        for(Player player : playerList) {
            if(player.getPlayerId().equals(id)) return player;
        }

        return null;
    }

    @Override
    public boolean removePlayer(Player player)
    {
        return playerList.remove(player);
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
    {
        callback = gameEngineCallback;
    }

    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback)
    {
        if(callback == null) {
            return false;
        } else {
            callback = null;
            return true;
        }
    }

    @Override
    public Collection<Player> getAllPlayers()
    {
        return playerList;
    }
}
