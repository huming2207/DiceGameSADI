package controllers;

import views.DiceAppFrame;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameController
{
    private DiceAppFrame appFrame;

    public GameController()
    {
        SwingUtilities.invokeLater(() -> this.appFrame = new DiceAppFrame(this));
    }


    public void handleAddPlayerRequest(ActionEvent event)
    {
    }

    public void handleBetPlacementRequest(ActionEvent event)
    {

    }


}
