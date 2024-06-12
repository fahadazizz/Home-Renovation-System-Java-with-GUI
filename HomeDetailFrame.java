import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;
import java.util.concurrent.Flow;

public class HomeDetailFrame extends JFrame {
    private String username;
    private JTextField roomsField, locationField, furnitureField, washroomField, otherInfoField;
    private JButton saveButton;

    public HomeDetailFrame(String username) {
        this.username = username;
        setTitle("Home Details");
        setSize(300, 630);
        setResizable(false);
        setForeground(Color.white);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0,50));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        roomsField = new JTextField(15);
        roomsField.setPreferredSize(new Dimension(120,40));
        locationField = new JTextField(15);
        locationField.setPreferredSize(new Dimension(120,40));
        furnitureField = new JTextField(15);
        furnitureField.setPreferredSize(new Dimension(120,40));
        washroomField = new JTextField(15);
        washroomField.setPreferredSize(new Dimension(120,40));
        otherInfoField = new JTextField(15);
        otherInfoField.setPreferredSize(new Dimension(120,80));

        saveButton = new JButton("Save");
        saveButton.setBackground(Color.yellow);
        saveButton.setPreferredSize(new Dimension(100,40));

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveHomeDetails();
                new ClientHomeFrame(username);
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("No. of Rooms:"));
        panel.add(roomsField);
        panel.add(new JLabel("No. of Appliences:"));
        panel.add(locationField);
        panel.add(new JLabel("No. of Furniture:"));
        panel.add(furnitureField);
        panel.add(new JLabel("No. of Washrooms:"));
        panel.add(washroomField);
        panel.add(new JLabel("Other Info:"));
        panel.add(otherInfoField);
        panel.add(saveButton);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        panel.setPreferredSize(new Dimension(200,500));

        add(panel);
        setVisible(true);
    }

    private void saveHomeDetails() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(username + "_home_details.txt"))) {
            bw.write("Rooms:" + roomsField.getText());
            bw.newLine();
            bw.write("Furniture:" + furnitureField.getText());
            bw.newLine();
            bw.write("Washrooms:" + washroomField.getText());
            bw.newLine();
            bw.write("Other Info:" + otherInfoField.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
