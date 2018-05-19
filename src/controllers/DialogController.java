package controllers;

import views.components.dialog.AboutDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DialogController
{
    public void handleHideDialogEvent(ActionEvent actionEvent)
    {
        JButton sourceButton = (JButton) actionEvent.getSource();
        JDialog dialog = (JDialog)SwingUtilities.getRoot(sourceButton);

        dialog.setVisible(false);
    }

    public void handleShowAboutDialogEvent(ActionEvent actionEvent)
    {
        Component sourceComponent = (Component)actionEvent.getSource();
        JFrame rootFrame = (JFrame)SwingUtilities.getRoot(sourceComponent);

        this.createAboutDialog(rootFrame);
    }

    private void createAboutDialog(JFrame parentFrame)
    {
        new AboutDialog(parentFrame);
    }
}
