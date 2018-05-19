package views;

import controllers.DialogController;
import controllers.GameController;
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
        GameController gameController = new GameController(this);
        DialogController dialogController = new DialogController();

        // Initialise menu bar
        this.setJMenuBar(new MainMenuBar(gameController, dialogController));

        // Initialise toolbar
        this.toolbarPanel = new ToolbarPanel(gameController);
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

        // Print current thread ID
        System.out.println(String.format("UI Thread ID: %d", Thread.currentThread().getId()));

        // Load game engine callbacks
        gameController.loadCallbacks();
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
