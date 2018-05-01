package views;

import javax.swing.*;
import java.awt.*;

public class DiceConsole extends JTextArea
{
    public DiceConsole()
    {
        // Font workaround (In my opinion, Menlo and Consolas is clearer than Courier)
        if(System.getProperty("os.name").toLowerCase().contains("windows")) {
            this.setFont(new Font("Consolas", Font.PLAIN, 16)); // Consolas only available on Windows...
        } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            this.setFont(new Font("Menlo", Font.PLAIN, 16)); // Menlo only available on macOS...
        } else {
            this.setFont(new Font("Courier New", Font.PLAIN, 16)); // ...On Linux or some other platforms??
        }
    }
}
