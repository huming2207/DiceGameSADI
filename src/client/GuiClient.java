package client;

import views.DiceAppFrame;

import javax.swing.*;

public class GuiClient
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(DiceAppFrame::new);
    }
}
