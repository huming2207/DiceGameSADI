package views.panel;

import controllers.GameController;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel
{
    private JLabel playerLabel = new JLabel("Player: Unknown");
    private JLabel playerBalanceLabel = new JLabel("Balance: Unknown");
    private JLabel playerBetLabel = new JLabel("Bet: Unknown");
    private JLabel playerResultLabel = new JLabel("Result: Unknown");
    private JLabel houseLabel = new JLabel("House");
    private JLabel houseBetLabel = new JLabel("Bet: Unknown");
    private JLabel houseResultLabel = new JLabel("Result: Unknown");

    private GameController gameController;

    public InfoPanel(GameController gameController)
    {
        super(new GridLayout(0, 1));

        // Enlarge title labels, i.e. player label and house label
        this.playerLabel.setFont(new Font("Serif", Font.BOLD, 25));
        this.houseLabel.setFont(new Font("Serif", Font.BOLD, 25));

        // Set game controller
        this.gameController = gameController;

        // Add all components in
        this.add(this.playerLabel);
        this.add(this.playerBalanceLabel);
        this.add(this.playerBetLabel);
        this.add(this.playerResultLabel);
        this.add(this.houseLabel);
        this.add(this.houseBetLabel);
        this.add(this.houseResultLabel);
    }
}
