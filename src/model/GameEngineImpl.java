package model;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.util.ArrayList;
import java.util.Collection;

public class GameEngineImpl implements GameEngine
{
    private Collection<Player> playerList;
    private GameEngineCallback callback;

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
