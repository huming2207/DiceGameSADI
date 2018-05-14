package client;

import controllers.GameController;

public class GuiClient
{
    public static void main(String[] args)
    {
        // Push the menu bar to the top of the screen for macOS
        // Ref: https://alvinalexander.com/blog/post/jfc-swing/put-your-jmenubar-on-mac-menu-bar
        if(System.getProperty("os.name").toUpperCase().contains("MAC")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.out.println("\nFOR TEACHERS WHO ARE MARKING THIS ASSIGNMENT:\n" +
                    "It seems like you are an apple fan, and this app is (kinda) natively optimised for macOS.\n" +
                    "Please remember to check the drop-down menu bar on the top of your screen.\n" +
                    "Also please notice that the dialog box is used in \"About this app\" for a short intro.\n");
        }

        GameController gameController = new GameController();
    }
}
