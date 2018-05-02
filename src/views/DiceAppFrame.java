package views;

import javax.swing.*;
import java.awt.*;

public class DiceAppFrame extends JFrame
{
    private DiceToolbar diceToolbar = new DiceToolbar();
    private DiceConsole diceConsole = new DiceConsole();

    public DiceAppFrame()
    {
        // Initialise window frame
        super("SADI Dice Game GUI");
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Add components to the window frame
        this.add(this.diceToolbar, BorderLayout.NORTH);
        this.add(this.diceConsole, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public DiceToolbar getDiceToolbar()
    {
        return diceToolbar;
    }

    public DiceConsole getDiceConsole()
    {
        return diceConsole;
    }
}
