package views;

import javax.swing.*;
import java.awt.*;

public class DiceAppFrame extends JFrame
{
    public DiceAppFrame()
    {
        super("SADI Dice Game GUI");
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(new DiceToolbar(), BorderLayout.NORTH);
        this.add(new DiceConsole(), BorderLayout.CENTER);
        this.setVisible(true);
    }
}
