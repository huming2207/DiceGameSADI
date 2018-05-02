package controllers;

import controllers.listeners.BetPlacementListener;
import controllers.listeners.ComboBoxListener;

public class GameController
{
    public ComboBoxListener getComboBoxListener()
    {
        return comboBoxListener;
    }

    public void setComboBoxListener(ComboBoxListener comboBoxListener)
    {
        this.comboBoxListener = comboBoxListener;
    }

    public BetPlacementListener getBetPlacementListener()
    {
        return betPlacementListener;
    }

    public void setBetPlacementListener(BetPlacementListener betPlacementListener)
    {
        this.betPlacementListener = betPlacementListener;
    }

    private ComboBoxListener comboBoxListener;
    private BetPlacementListener betPlacementListener;


}
