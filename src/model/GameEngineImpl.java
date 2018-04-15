package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class GameEngineImpl implements GameEngine
{
    private Collection<Player> playerList;
    private Collection<GameEngineCallback> callbackList;

    public GameEngineImpl()
    {
        this.playerList = new ArrayList<>();
        this.callbackList = new ArrayList<>();
    }

    @Override
    public boolean placeBet(Player player, int bet)
    {
        if(player == null || bet < 1) return false;
        return player.placeBet(bet);
    }

    @Override
    public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement)
    {
        int rollCount = (finalDelay - (initialDelay - 1)) / delayIncrement;
        int delay = initialDelay;

        if (rollCount < 1) rollCount = 1; // Just in case for someone input something wrong...

        for (int count = 0; count < rollCount; count += 1) {

            // Iterate all callbacks and print out the results
            for(GameEngineCallback callback : callbackList) {
                callback.intermediateResult(player,
                        new DicePairImpl(getRandomDiceFace(), getRandomDiceFace(), GameEngine.NUM_FACES), this);
            }

            // Start to delay
            try {
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Increase next delay time
            delay += delayIncrement;
        }

        // Iterate all callbacks and print out the results
        for(GameEngineCallback callback : callbackList) {
            DicePair dicePair = new DicePairImpl(getRandomDiceFace(), getRandomDiceFace(), GameEngine.NUM_FACES);
            callback.result(player, dicePair, this);
            player.setRollResult(dicePair);
        }
    }

    @Override
    public void rollHouse(int initialDelay, int finalDelay, int delayIncrement)
    {
        int rollCount = (finalDelay - (initialDelay - 1)) / delayIncrement;
        int delay = initialDelay;

        if (rollCount < 1) rollCount = 1; // Just in case for someone input something wrong...

        for (int count = 0; count < rollCount; count += 1) {

            for (GameEngineCallback callback : callbackList) {
                callback.intermediateHouseResult(
                        new DicePairImpl(getRandomDiceFace(), getRandomDiceFace(), GameEngine.NUM_FACES), this);
            }

            // Start to delay
            try {
                TimeUnit.MILLISECONDS.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Increase next delay time
            delay += delayIncrement;
        }

        // Generate the house result
        DicePair dicePair = new DicePairImpl(getRandomDiceFace(), getRandomDiceFace(), GameEngine.NUM_FACES);


        // Iterate the players and print for comparing with the house to decide who win or who lose
        for(Player player : playerList) {
            int finalResult  = player.getRollResult().getDice1() + player.getRollResult().getDice2();
            int finalHouseResult = dicePair.getDice1() + dicePair.getDice2();

            // Condition: if this player's dice pair result is lower than the house result, then do the deduction
            if(finalResult < finalHouseResult) {
                player.setPoints(player.getPoints() - player.getBet());
            } else if(finalResult > finalHouseResult) {
                player.setPoints(player.getPoints() + player.getBet());
            }
        }

        // Log the final result AFTER the comparison
        for(GameEngineCallback callback : callbackList) {
            callback.houseResult(dicePair, this);
        }
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
        callbackList.add(gameEngineCallback);
    }

    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback)
    {
        return callbackList.remove(gameEngineCallback);
    }

    @Override
    public Collection<Player> getAllPlayers()
    {
        return new ArrayList<>(playerList);
    }

    private int getRandomDiceFace()
    {
        // Generate a random integer between 1 to 6
        // It seems to be the best way to generate any random number with range in Java is using "ThreadLocalRandom"...
        //
        // Ref:
        // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java

        return ThreadLocalRandom.current().nextInt(1, GameEngine.NUM_FACES + 1);
    }
}
