package model.gui;

import model.console.SimplePlayer;

/*
    The main differencce between GuiPlayer and SimplePlayer is GuiPlayer shorten return value of
    toString() method to fit the JComboBox...
 */
public class GuiPlayer extends SimplePlayer
{
    public GuiPlayer(String playerId, String playerName, int points)
    {
        super(playerId, playerName, points);
    }

    @Override
    public String toString()
    {
        return String.format("#%s: %s, %d pts",
                super.getPlayerId(), super.getPlayerName(), super.getPoints());
    }
}
