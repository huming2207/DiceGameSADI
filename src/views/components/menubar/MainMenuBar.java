package views.components.menubar;

import controllers.*;
import controllers.listeners.AddPlayerListener;
import controllers.listeners.BetPlacementListener;
import controllers.listeners.HouseBetListener;
import controllers.listeners.QuitEventHandler;
import model.GameStatus;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MainMenuBar extends JMenuBar
{
    public MainMenuBar(GameStatus gameStatus, DialogController dialogController)
    {
        // Top level menu bar sections
        JMenu playerMenu = new JMenu("Player");
        JMenu aboutMenu = new JMenu("About");

        this.add(playerMenu);
        this.add(aboutMenu);

        // "File" menu section:
        // "Add player" section and shortcut key
        JMenuItem addPlayerItem = new JMenuItem("Add player", KeyEvent.VK_A);
        addPlayerItem.addActionListener(new AddPlayerListener(gameStatus));
        addPlayerItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));

        // "Place bet" section and shortcut key
        JMenuItem placeBetItem = new JMenuItem("Place bet", KeyEvent.VK_P);
        placeBetItem.addActionListener(new BetPlacementListener(gameStatus));
        placeBetItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));

        // "House bet" section and shortcut key
        JMenuItem houseBetItem = new JMenuItem("House bet", KeyEvent.VK_H);
        houseBetItem.addActionListener(new HouseBetListener(gameStatus));
        houseBetItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));

        // "Quit" section and shortcut key
        JMenuItem quitItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        quitItem.addActionListener(new QuitEventHandler());
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
