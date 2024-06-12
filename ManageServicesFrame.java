import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.io.*;

public class ManageServicesFrame extends JFrame {
    private JCheckBox[] serviceBoxes;
    private JButton saveButton;

    public ManageServicesFrame() {
        setTitle("Manage Services");
        setSize(300, 630);
        setResizable(false);
        setForeground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        serviceBoxes = new JCheckBox[6];
        serviceBoxes[0] = new JCheckBox("Painting - $100");
        serviceBoxes[1] = new JCheckBox("Plumbing - $200");
        serviceBoxes[2] = new JCheckBox("Electrical - $150");
        serviceBoxes[3] = new JCheckBox("Cleaning - $50");
        serviceBoxes[4] = new JCheckBox("Carpentry - $120");
        serviceBoxes[5] = new JCheckBox("Roofing - $250");

        saveButton = new JButton("Save");
        saveButton.setBorderPainted(false);
        saveButton.setBackground(new Color(211,186,197));
        saveButton.setSize(120, 35);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveServices();
                new AdminHomeFrame();
                dispose();
            }
        });

        JPanel panel = new JPanel();
        for (JCheckBox box : serviceBoxes) {
            panel.add(box);
        }
        panel.add(saveButton);

        loadServices();

        add(panel);
        setVisible(true);
    }

    private void loadServices() {
        try (BufferedReader br = new BufferedReader(new FileReader("services.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (JCheckBox box : serviceBoxes) {
                    if (line.equals(box.getText())) {
                        box.setSelected(true);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveServices() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("services.txt"))) {
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
