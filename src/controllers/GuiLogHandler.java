package controllers;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.*;

public class GuiLogHandler extends StreamHandler
{
    public GuiLogHandler(JTextArea textArea)
    {
        super();

        // This section of code below comes from java.util.logging.ConsoleHandler with some changes
        this.setLevel(Level.FINE);
        this.setFormatter(new SimpleFormatter());

        // Set encoding
        try {
            this.setEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("This environment does not even support UTF-8, " +
                    "please update your OS or simply buy a new device."); // ...just kidding
            try {
                this.setEncoding(null); // try set to null
            } catch (UnsupportedEncodingException fatalEx) {
                fatalEx.printStackTrace(); // ...or if it fails, it really fails.
            }
        }

        // Bind output stream
        this.setOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException
            {
                textArea.append(String.valueOf(b));
            }
        });
    }

    @Override
    public void publish(LogRecord record)
    {
        // No need to flush here as the output stream not buffered.
        super.publish(record);
    }
}
