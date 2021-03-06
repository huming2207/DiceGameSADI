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

    public SimplePlayer(String playerId, String playerName, int points)
    {
        this.playerId = playerId;
        this.playerName = playerName;
        this.points = points;
    }

    @Override
    public String getPlayerName()
    {
        return this.playerName;
    }

    @Override
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    @Override
    public int getPoints()
    {
        return this.points;
    }

    @Override
    public void setPoints(int points)
    {
        this.points = points;
    }

    @Override
    public String getPlayerId()
    {
        return this.playerId;
    }

    @Override
    public boolean placeBet(int bet)
    {
        if (getPoints() < bet) {
            return false;
        } else {
            this.bet = bet;
            return true;
        }
    }

    @Override
    public int getBet()
    {
        return this.bet;
    }

    @Override
    public DicePair getRollResult()
    {
        return this.dicePair;
    }

    @Override
    public void setRollResult(DicePair rollResult)
    {
        this.dicePair = rollResult;
    }

    @Override
    public String toString()
    {
        return String.format("id=%s, name=%s, points=%d",
                this.playerId, this.playerName, this.points);
    }
}
