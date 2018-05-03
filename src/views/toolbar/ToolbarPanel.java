package views.toolbar;

import controllers.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ToolbarPanel extends JPanel
{
    private JComboBox<String> selectionComboBox;
    private JTextField nameTextfield = new JTextField("Rotten Durian"); // Remember to commit and push before evacuation
    private JTextField initialBetTextfield = new JTextField("1000");
    private JTextField setBetTextfield = new JTextField("500");
    private JButton placeBetButton = new JButton("Place bet");
    private JButton addPlayerButton = new JButton("Add player");
    private JButton houseButton = new JButton("House bet");

    public ToolbarPanel(GameController gameController)
    {
        // User border layout to separate two parts of the toolbar
        super(new FlowLayout());

        // Initialise combobox, resize it
        this.selectionComboBox = new JComboBox<>();
        this.selectionComboBox.setPreferredSize(new Dimension(150, 20));

        // Resize two buttons (way too large)
        this.placeBetButton.setPreferredSize(new Dimension(120, 20));
        this.houseButton.setPreferredSize(new Dimension(120, 20));
        this.addPlayerButton.setPreferredSize(new Dimension(120,20));

        // Resize text boxes
        this.setBetTextfield.setPreferredSize(new Dimension(50, 20));
        this.nameTextfield.setPreferredSize(new Dimension(150, 20));
        this.initialBetTextfield.setPreferredSize(new Dimension(50, 20));

        // Add tooltip for to clearify the function
        this.setBetTextfield.setToolTipText("Place a bet here");
        this.initialBetTextfield.setToolTipText("Set the initial bet balance here");
        this.nameTextfield.setToolTipText("Set the player name here");
        this.selectionComboBox.setToolTipText("Select a player here");

        // Add listener for buttons
        // For teachers who is marking this assignment: please change it to non-lambda
        this.addPlayerButton.addActionListener(gameController::handleAddPlayerRequest);
        this.placeBetButton.addActionListener(gameController::handleBetPlacementRequest);

        // Add components
        // player selection
        this.add(this.selectionComboBox);
        this.add(this.setBetTextfield);
        this.add(this.placeBetButton);

        // Add a separator between player selection and player adder
        this.add(Box.createHorizontalStrut(30));

        // player adder
        this.add(this.nameTextfield);
        this.add(this.initialBetTextfield);
        this.add(this.addPlayerButton);

        // Add a separator between player adder and house
        this.add(Box.createHorizontalStrut(30));

        // Add house button
        this.add(this.houseButton);

    }

    public JComboBox<String> getSelectionComboBox()
    {
        return selectionComboBox;
    }

    public JTextField getNameTextfield()
    {
        return nameTextfield;
    }

    public JTextField getInitialBetTextfield()
    {
        return initialBetTextfield;
    }

    public JTextField getSetBetTextfield()
    {
        return setBetTextfield;
    }

    public JButton getPlaceBetButton()
    {
        return placeBetButton;
    }

    public JButton getAddPlayerButton()
    {
        return addPlayerButton;
    }
}
