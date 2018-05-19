package views.components.dialog;

import controllers.DialogController;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog
{
    public AboutDialog(JFrame parentFrame)
    {
        super(parentFrame, "About this app", true);
        this.setLayout(new BorderLayout());

        JLabel mainLabel = new JLabel(
                "<html>" +
                        "<p>This app is written by Ming Hu (s3554025) for SADI Assignment 2.</p><br>" +
                        "<b>Blog: </b> <a href='https://jacksonhu.com'>jacksonhu.com</a><br>" +
                        "<b>GitHub: </b><a href='https://github.com/huming2207'>github.com/huming2207</a>" +
                        "</html>"
                , SwingConstants.CENTER);

        DialogController dialogController = new DialogController();

        JButton hideButton = new JButton("Okay");
        hideButton.addActionListener(dialogController::handleHideDialogEvent);

        this.add(mainLabel, BorderLayout.CENTER);
        this.add(hideButton, BorderLayout.SOUTH);

        this.setSize(450, 150);
        this.setVisible(true);
    }
}
