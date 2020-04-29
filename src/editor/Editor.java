package editor;

import javax.swing.*;
import java.io.IOException;

public class Editor  {

    public static void main (String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() { // Run new Thread
            @Override
            public void run() {
                new Frame();
            }
        });
    }
}
