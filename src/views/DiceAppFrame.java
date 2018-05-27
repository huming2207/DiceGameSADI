package views;

import controllers.DialogController;
import model.GameStatus;
import views.components.menubar.MainMenuBar;
import views.components.panel.InfoPanel;
import views.components.panel.StatusBarPanel;
import views.components.toolbar.DiceToolbar;
import views.components.toolbar.ToolbarPanel;

import javax.swing.*;
import java.awt.*;

public class DiceAppFrame extends JFrame
{
    private ToolbarPanel toolbarPanel;
    private InfoPanel infoPanel;
    private StatusBarPanel statusBarPanel;

    public DiceAppFrame()
    {
        // Initialise window frame
        super("SADI Dice Game GUI");
        this.setBounds(100, 100, 1000, 300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Initialise controllers
        GameStatus gameStatus = new GameStatus(this);
        DialogController dialogController = new DialogController();

        // Initialise menu bar
        this.setJMenuBar(new MainMenuBar(gameStatus, dialogController));

        // Initialise toolbar
        this.toolbarPanel = new ToolbarPanel(gameStatus);
        DiceToolbar toolbar = new DiceToolbar(this.toolbarPanel);

        // Initialise info panel
        this.infoPanel = new InfoPanel();

        // Initialise status bar
        this.statusBarPanel = new StatusBarPanel();

        // Add components to the window frame
        this.add(toolbar, BorderLayout.NORTH);
        this.add(this.infoPanel, BorderLayout.CENTER);
        this.add(this.statusBarPanel, BorderLayout.SOUTH);
        this.setVisible(true);

        // Load game engine callbacks
        gameStatus.loadCallbacks();
    }


    public ToolbarPanel getToolbarPanel()
    {
        return this.toolbarPanel;
    }

    public InfoPanel getInfoPanel()
    {
        return this.infoPanel;
    }

    public StatusBarPanel getStatusBarPanel()
    {
        return this.statusBarPanel;
    }
}
