package views;

import controllers.GameController;

import javax.swing.*;
import java.awt.*;

public class DiceToolbar extends JToolBar
{


    public DiceToolbar(ToolbarPanel toolbarPanel)
    {
        super();

        this.add(toolbarPanel);
    }


}
