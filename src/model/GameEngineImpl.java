package model;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.util.Collection;

public class GameEngineImpl implements GameEngine
{
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

    }

    @Override
    public Player getPlayer(String id)
    {
        return null;
    }

    @Override
    public boolean removePlayer(Player player)
    {
        return false;
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
    {

    }

    @Override
    public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback)
    {
        return false;
    }

    @Override
    public Collection<Player> getAllPlayers()
    {
        return null;
    }
}
