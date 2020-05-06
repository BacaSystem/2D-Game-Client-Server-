package editor;

import javax.swing.*;
import java.awt.event.*;

public class InputDialog extends JDialog {
    private Frame frame;
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField numVert;
    private JTextField numMet;
    private JTextField startPos;
    private JTextField gravity;
    private JComboBox landingSize;
    private JTextField landingPos;
    private JButton buttonCancel;

    public InputDialog() {
        frame = new Frame();
        setContentPane(contentPane);
        setModal(false);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    }

    private void onOK() {
        int numOfVert = Integer.parseInt(numVert.getText());

        //frame.level.loadTerrain(numOfVert);
        //dispose();
    }


}
