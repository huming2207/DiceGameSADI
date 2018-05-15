package views.helpers;

import javax.swing.*;

public class LabelUpdateHelper
{
    public static void updateLabelText(JLabel label, String str)
    {
        label.setText(str);
    }

    public static void appendLabelText(JLabel label, String str)
    {
        label.setText(label.getText() + str);
    }

    public static void updateLabelTextAsync(JLabel label, String str)
    {
        SwingUtilities.invokeLater(() -> label.setText(str));
    }

    public static void appendLabelTextAsync(JLabel label, String str)
    {
        SwingUtilities.invokeLater(() -> label.setText(label.getText() + str));
    }
}
