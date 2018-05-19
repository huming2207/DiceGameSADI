package controllers;

import model.common.GameEngineImpl;
import model.console.ConsoleCallback;
import model.gui.GuiCallback;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import views.DiceAppFrame;

import javax.swing.*;

public class GameController
{
    private DiceAppFrame appFrame;
    private GameEngine gameEngine;
    private Player selectedPlayer;
    private int playerId = 0;

    public GameController(DiceAppFrame appFrame)
    {
        // Start UI with a new UI thread
        this.appFrame = appFrame;

        System.out.println(String.format("Main thread ID: %d", Thread.currentThread().getId()));
    }

    /**
     * Callbacks must be loaded AFTER app frame is loaded, otherwise some UI components will become null.
     */
    public void loadCallbacks()
    {
        this.gameEngine = new GameEngineImpl();
        this.gameEngine.addGameEngineCallback(new GuiCallback(this));
        this.gameEngine.addGameEngineCallback(new ConsoleCallback());
    }

    public int getPlayerId()
    {
        return this.playerId;
    }

    public void setPlayerId(int id)
    {
        this.playerId = id;
    }

    public void setGameEngine(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
    }

    public GameEngine getGameEngine()
    {
        return this.gameEngine;
    }

    public void setAppFrame(DiceAppFrame appFrame)
    {
        this.appFrame = appFrame;
    }

    public Player getSelectedPlayer()
    {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer)
    {
        this.selectedPlayer = selectedPlayer;
    }

    public DiceAppFrame getAppFrame()
    {
        return this.appFrame;
    }

    public Player getCurrentPlayer()
    {
        return this.selectedPlayer;
    }

    public Integer validateInput(String textBoxInput)
    {
        int initBet = 0;

        try {
            initBet = Integer.parseInt(textBoxInput);
        } catch (NumberFormatException numFormatException) {
            JOptionPane.showMessageDialog(null, "Initial bet is not a number!",
                    "Format error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return initBet;
    }
}
