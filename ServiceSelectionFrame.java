import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;

public class ServiceSelectionFrame extends JFrame {
    private String username;
    private JCheckBox[] serviceBoxes;
    private JButton saveButton;

    public ServiceSelectionFrame(String username) {
        this.username = username;
        setTitle("Select Services");
        setSize(300, 630);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0,120));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        serviceBoxes = new JCheckBox[6];
        serviceBoxes[0] = new JCheckBox("Painting - $100");
        serviceBoxes[1] = new JCheckBox("Plumbing - $200");
        serviceBoxes[2] = new JCheckBox("Electrical - $150");
        serviceBoxes[3] = new JCheckBox("Cleaning - $50");
        serviceBoxes[4] = new JCheckBox("Carpentry - $120");
        serviceBoxes[5] = new JCheckBox("Roofing - $250");

        saveButton = new JButton("Save");
        saveButton.setBackground(Color.orange);
        saveButton.setForeground(Color.white);
        saveButton.setPreferredSize(new Dimension(150,40));

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveSelectedServices();
                new ClientHomeFrame(username);
                dispose();
            }
        });

        JPanel panel = new JPanel();
        for (JCheckBox box : serviceBoxes) {
            panel.add(box);
        }
        panel.add(saveButton);
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0,15));
        panel.setPreferredSize(new Dimension(150,500));

        add(panel);
        setVisible(true);
    }

    private void saveSelectedServices() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(username + "_services.txt"))) {
            for (JCheckBox box : serviceBoxes) {
                if (box.isSelected()) {
                    bw.write(box.getText());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
