package views.components.panel;

import controllers.GameController;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel
{
    private JLabel playerLabel = new JLabel("Player");
    private JLabel playerBalanceLabel = new JLabel("Player balance: ");
    private JLabel playerBetLabel = new JLabel("Player bet: ");
    private JLabel playerResultLabel = new JLabel("Player final result:");
    private JLabel houseLabel = new JLabel("House");
    private JLabel houseBetLabel = new JLabel("House bet: ");
    private JLabel houseResultLabel = new JLabel("House final result: ");

    private GameController gameController;

    public InfoPanel(GameController gameController)
    {
        super(new GridLayout(0, 1));

        // Enlarge title labels, i.e. player label and house label
        this.playerLabel.setFont(new Font("Sans-Serif", Font.BOLD, 25));
        this.houseLabel.setFont(new Font("Sans-Serif", Font.BOLD, 25));

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


    public JLabel getPlayerLabel()
    {
        return this.playerLabel;
    }

    public JLabel getPlayerBalanceLabel()
    {
        return this.playerBalanceLabel;
    }

    public JLabel getPlayerBetLabel()
    {
        return this.playerBetLabel;
    }

    public JLabel getPlayerResultLabel()
    {
        return this.playerResultLabel;
    }

    public JLabel getHouseLabel()
    {
        return this.houseLabel;
    }

    public JLabel getHouseBetLabel()
    {
        return this.houseBetLabel;
    }

    public JLabel getHouseResultLabel()
    {
        return this.houseResultLabel;
    }

    public GameController getGameController()
    {
        return this.gameController;
    }

    public void cleanUp()
    {
        this.playerLabel.setText("Player");
        this.playerBalanceLabel.setText("Player balance: ");
        this.playerBetLabel.setText("Player bet: ");
        this.playerResultLabel.setText("Player final result:");
        this.houseLabel.setText("House");
        this.houseBetLabel.setText("House bet: ");
        this.houseResultLabel.setText("House final result: ");
    }
}
