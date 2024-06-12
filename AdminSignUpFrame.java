import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;

public class AdminSignUpFrame extends JFrame {
    private JTextField adminIdField;
    private JPasswordField adminPasswordField;
    private JButton signUpButton;

    public AdminSignUpFrame() {
        setTitle("Admin Sign Up");
        setForeground(Color.white);
        setSize(300, 630);
        setLayout(new FlowLayout(FlowLayout.CENTER,0,150));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        adminIdField = new JTextField(15);
        adminIdField.setPreferredSize(new Dimension(100,35));

        adminPasswordField = new JPasswordField(15);
        adminPasswordField.setPreferredSize(new Dimension(100,35));

        signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(100,35));
        signUpButton.setBackground(new Color(211,186,197));

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adminId = adminIdField.getText();
                String adminPassword = new String(adminPasswordField.getPassword());

                if (registerAdmin(adminId, adminPassword)) {
                    JOptionPane.showMessageDialog(null, "Admin registration successful. Please login.");
                    new AdminLoginFrame();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Admin ID already exists.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Admin ID:"));
        panel.add(adminIdField);
        panel.add(new JLabel("Password:"));
        panel.add(adminPasswordField);
        panel.add(signUpButton);
        panel.setPreferredSize(new Dimension(250,150));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,5,15));
        panel.setForeground(Color.white);

        add(panel);
        setVisible(true);
    }

    private boolean registerAdmin(String adminId, String adminPassword) {
        try (BufferedReader br = new BufferedReader(new FileReader("admin.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(adminId)) {
                    return false; // Admin ID already exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("admin.txt", true))) {
            bw.write(adminId + "," + adminPassword);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
