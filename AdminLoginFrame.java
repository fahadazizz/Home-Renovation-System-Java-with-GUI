import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;

public class AdminLoginFrame extends JFrame {
    private JTextField adminIdField;
    private JPasswordField adminPasswordField;
    private JButton loginButton, signUpButton;

    public AdminLoginFrame() {
        setTitle("Admin Login");
        setSize(300, 630);
        setLayout(new FlowLayout(FlowLayout.CENTER,0,150));
        setForeground(Color.white);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        adminIdField = new JTextField(15);
        adminIdField.setPreferredSize(new Dimension(100,35));

        adminPasswordField = new JPasswordField(15);
        adminPasswordField.setPreferredSize(new Dimension(100,35));

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100,35));
        loginButton.setBackground(new Color(211,186,197));

        signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(100,35));
        signUpButton.setBackground(new Color(211,186,197));

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adminId = adminIdField.getText();
                String adminPassword = new String(adminPasswordField.getPassword());

                if (authenticateAdmin(adminId, adminPassword)) {
                    new AdminHomeFrame();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid admin ID or password.");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminSignUpFrame();
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Admin ID:"));
        panel.add(adminIdField);
        panel.add(new JLabel("Password:"));
        panel.add(adminPasswordField);
        panel.add(loginButton);
        panel.add(signUpButton);
        panel.setPreferredSize(new Dimension(250,150));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,5,15));
        panel.setForeground(Color.white);
        add(panel);
        setVisible(true);
    }

    private boolean authenticateAdmin(String adminId, String adminPassword) {
        try (BufferedReader br = new BufferedReader(new FileReader("admin.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(adminId) && parts[1].equals(adminPassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
