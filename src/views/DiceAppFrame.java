package views;

import controllers.GameController;
import views.panel.InfoPanel;
import views.toolbar.DiceToolbar;
import views.toolbar.ToolbarPanel;

import javax.swing.*;
import java.awt.*;

public class DiceAppFrame extends JFrame
{
    private ToolbarPanel toolbarPanel;
    private InfoPanel infoPanel;

    public DiceAppFrame(GameController gameController)
    {
        // Initialise window frame
        super("SADI Dice Game GUI");
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Initialise toolbar
        this.toolbarPanel = new ToolbarPanel(gameController);
        DiceToolbar toolbar = new DiceToolbar(this.toolbarPanel);

        // Initialise info panel
        this.infoPanel = new InfoPanel(gameController);

        // Add components to the window frame
        this.add(toolbar, BorderLayout.NORTH);
        this.add(this.infoPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }


    public ToolbarPanel getToolbarPanel()
    {
        return this.toolbarPanel;
    }
}
