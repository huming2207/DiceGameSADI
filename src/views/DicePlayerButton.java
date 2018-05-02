package views;

import controllers.PlayerButtonListener;

import javax.swing.*;

public class DicePlayerButton extends JButton
{
    public DicePlayerButton(PlayerButtonListener listener)
    {
        super("Select player &");
        this.addActionListener(listener);
    }
}
