package views;

import controllers.ComboBoxListener;
import model.interfaces.Player;

import javax.swing.*;
import java.util.HashMap;

public class PlayerComboBox extends JComboBox<String>
{
    public PlayerComboBox(HashMap<String, Player> players, ComboBoxListener comboBoxListener)
    {
        super(players.keySet().toArray(new String[0]));
        this.addActionListener(comboBoxListener);
    }
}
