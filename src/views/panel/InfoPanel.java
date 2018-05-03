package views.panel;

import controllers.GameController;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel
{
    private JLabel playerLabel = new JLabel("Unknown");
    private JLabel playerBalanceLabel = new JLabel("Unknown");
    private JLabel playerBetLabel = new JLabel("Unknown");
    private JLabel playerResultLabel = new JLabel("Result: Unknown");
    private JLabel houseLabel = new JLabel("Unknown");
    private JLabel houseBetLabel = new JLabel("Unknown");
    private JLabel houseResultLabel = new JLabel("Result: Unknown");

    private GameController gameController;

    public InfoPanel(GameController gameController)
    {
        super(new GridLayout(0, 2));

        // Enlarge title labels, i.e. player label and house label
        JLabel playerTitleLabel = new JLabel("Player");
        JLabel houseTitleLabel = new JLabel("House");
        playerTitleLabel.setFont(new Font("Serif", Font.BOLD, 25));
        houseTitleLabel.setFont(new Font("Serif", Font.BOLD, 25));

        // Set game controller
        this.gameController = gameController;

        // Add all components in
        this.add(playerTitleLabel);
        this.add(this.playerLabel);

        this.add(new JLabel("Balance: "));
        this.add(this.playerBalanceLabel);

        this.add(new JLabel("Bet: "));
        this.add(this.playerBetLabel);

        this.add(new JLabel("Result: "));
        this.add(this.playerResultLabel);

        this.add(houseTitleLabel);
        this.add(this.houseLabel);

        this.add(new JLabel("Bet: "));
        this.add(this.houseBetLabel);

        this.add(new JLabel("Balance: "));
        this.add(this.houseResultLabel);
    }
}
