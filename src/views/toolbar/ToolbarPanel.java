package views.toolbar;

import controllers.GameController;

import javax.swing.*;
import java.awt.*;

public class ToolbarPanel extends JPanel
{
    private JComboBox<String> selectionComboBox;
    private JTextField nameTextfield = new JTextField("Enter name here");
    private JTextField initialBetTextfield = new JTextField("Enter initial bet here");
    private JTextField setBetTextfield = new JTextField("Enter bet placement here");
    private JButton placeBetButton = new JButton("Place bet");
    private JButton addPlayerButton = new JButton("Add player");

    public ToolbarPanel(GameController gameController)
    {
        // User border layout to separate two parts of the toolbar
        super(new FlowLayout());

        // Initialise combobox, resize it
        this.selectionComboBox = new JComboBox<>();
        this.selectionComboBox.addActionListener(gameController.getComboBoxListener());
        this.selectionComboBox.setPreferredSize(new Dimension(150, 20));

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
