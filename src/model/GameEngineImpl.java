package model;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.util.ArrayList;
import java.util.Collection;

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
        return false;
    }

    @Override
    public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement)
    {

    }

    @Override
    public void rollHouse(int initialDelay, int finalDelay, int delayIncrement)
    {

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
        return playerList;
    }
}
