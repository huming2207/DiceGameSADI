package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player
{
    private String playerId;
    private String playerName;
    private int bet;
    private int points;
    private DicePair dicePair;

    public SimplePlayer(String playerId, String playerName, int bet)
    {
        this.playerId = playerId;
        this.playerName = playerName;
        this.bet = bet;
        this.dicePair = new DicePairImpl();
    }

    @Override
    public String getPlayerName()
    {
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    @Override
    public int getPoints()
    {
        return points;
    }

    @Override
    public void setPoints(int points)
    {
        this.points = points;
    }

    @Override
    public String getPlayerId()
    {
        return playerId;
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
        return dicePair;
    }

    @Override
    public void setRollResult(DicePair rollResult)
    {
        this.dicePair = rollResult;
    }
}
