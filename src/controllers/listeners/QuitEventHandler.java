package controllers.listeners;

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
