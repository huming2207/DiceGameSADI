package controllers.listeners;

import model.interfaces.GameEngine;
import views.DiceAppFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitEventHandler implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }
}
