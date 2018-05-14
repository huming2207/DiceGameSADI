package views.menubar;

import controllers.DialogController;
import controllers.GameController;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MainMenuBar extends JMenuBar
{
    public MainMenuBar(GameController gameController, DialogController dialogController)
    {
        // Top level menu bar sections
        JMenu playerMenu = new JMenu("Player");
        JMenu aboutMenu = new JMenu("About");

        this.add(playerMenu);
        this.add(aboutMenu);

        // "File" menu section:
        // "Add player" section and shortcut key
        JMenuItem addPlayerItem = new JMenuItem("Add player", KeyEvent.VK_A);
        addPlayerItem.addActionListener(gameController::handleAddPlayerRequest);
        addPlayerItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));

        // "Place bet" section and shortcut key
        JMenuItem placeBetItem = new JMenuItem("Place bet", KeyEvent.VK_P);
        placeBetItem.addActionListener(gameController::handleBetPlacementRequest);
        placeBetItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));

        // "House bet" section and shortcut key
        JMenuItem houseBetItem = new JMenuItem("House bet", KeyEvent.VK_H);
        houseBetItem.addActionListener(gameController::handleHouseBetRequest);
        houseBetItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));

        // "Quit" section and shortcut key
        JMenuItem quitItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        quitItem.addActionListener(gameController::handleQuitEvent);
        quitItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));

        playerMenu.add(addPlayerItem);
        playerMenu.add(placeBetItem);
        playerMenu.add(houseBetItem);
        playerMenu.addSeparator();
        playerMenu.add(quitItem);

        // "About" menu section:
        // "About this program" section and shortcut key
        JMenuItem aboutItem = new JMenuItem("About this app", KeyEvent.VK_A);
        aboutItem.addActionListener(dialogController::handleShowAboutDialogEvent);

        aboutMenu.add(aboutItem);
    }
}
