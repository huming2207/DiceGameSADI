package views.components.panel;

import javax.swing.*;
import java.awt.*;

public class StatusBarPanel extends JPanel
{
    private JLabel leftLabel = new JLabel("Unknown player", JLabel.LEFT);
    private JLabel rightLabel = new JLabel("Unknown status", JLabel.LEFT);

    public StatusBarPanel()
    {
        this.setLayout(new GridLayout(1, 2));

        this.leftLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.rightLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        this.add(this.leftLabel);
        this.add(this.rightLabel);
    }

    public JLabel getLeftLabel()
    {
        return this.leftLabel;
    }

    public JLabel getRightLabel()
    {
        return this.rightLabel;
    }
}
