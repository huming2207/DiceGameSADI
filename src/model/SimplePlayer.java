package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player
{
    @Override
    public String getPlayerName()
    {
        return null;
    }

    @Override
    public void setPlayerName(String playerName)
    {

    }

    @Override
    public int getPoints()
    {
        return 0;
    }

    @Override
    public void setPoints(int points)
    {

    }

    @Override
    public String getPlayerId()
    {
        return null;
    }

    @Override
    public boolean placeBet(int bet)
    {
        return false;
    }

    @Override
    public int getBet()
    {
        return 0;
    }

    @Override
    public DicePair getRollResult()
    {
        return null;
    }

    @Override
    public void setRollResult(DicePair rollResult)
    {

    }
}
