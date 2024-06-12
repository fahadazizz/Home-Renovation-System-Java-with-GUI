import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;

public class ViewRequestsFrame extends JFrame {
    private JTextArea requestDetails;
    private JButton backButton;

    public ViewRequestsFrame() {
        setTitle("View Work Requests");
        setSize(300, 630);
        setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setForeground(Color.white);

        requestDetails = new JTextArea(20, 35);
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100,40));
        backButton.setBackground(Color.yellow);


        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminHomeFrame();
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        panel.setPreferredSize(new Dimension(250,450));
        JScrollPane pane = new JScrollPane(requestDetails);
        pane.setPreferredSize(new Dimension(230,300));
        panel.add(pane);
        panel.add(backButton);

        loadRequests();

        add(panel);
        setVisible(true);
    }

    private void loadRequests() {
        try (BufferedReader br = new BufferedReader(new FileReader("requests.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                requestDetails.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
