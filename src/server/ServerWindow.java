package server;

import server.configReader.ConfigReader;

import javax.swing.*;
import java.awt.*;
import java.net.ServerSocket;

public class ServerWindow extends AbstractVerticalPanelServer {
    private static final String filename = "ip";
    public ServerWindow(ServerSocket serverSocket) {
        super();
        JFrame frame = new JFrame();
        {
            frame.setTitle("Server");
            frame.setSize(200, 170);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setLayout(new FlowLayout());
        }
        super.verticalPanel.add(new JLabel("SERVER IS RUNNING", SwingConstants.CENTER));
        super.verticalPanel.add(new JLabel("IP: " + ConfigReader.getValue(filename,"ip"), SwingConstants.CENTER));
        super.verticalPanel.add(new JLabel("Port:  " + ConfigReader.getValue(filename, "port"), SwingConstants.CENTER));
        JButton closeServer = new JButton("CLOSE SERVER");
        closeServer.addActionListener(actionEvent -> {
            try {
                serverSocket.close();
            } catch (Exception e) {
                System.out.println("server socket already closed");
            }
            System.exit(0);
        });
        super.verticalPanel.add(closeServer);

        frame.add(super.verticalPanel);
        frame.setVisible(true);
    }
}


