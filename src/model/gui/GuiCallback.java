package model.gui;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GuiCallback implements GameEngineCallback
{

    @Override
    public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
    {

    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine)
    {

    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine)
    {

    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine)
    {

    }
}
