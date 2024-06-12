import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;

public class EditProfileFrame extends JFrame {
    private String oldUsername;
    private JTextField oldUsernameField, newUsernameField;
    private JPasswordField oldPasswordField, newPasswordField;
    private JButton saveButton;

    public EditProfileFrame(String oldUsername) {
        this.oldUsername = oldUsername;
        setTitle("Edit Profile");
        setForeground(Color.white);
        setResizable(false);
        setSize(300, 630);
        setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        oldUsernameField = new JTextField(15);
        oldUsernameField.setPreferredSize(new Dimension(120,35));

        oldPasswordField = new JPasswordField(15);
        oldPasswordField.setPreferredSize(new Dimension(120,35));

        newUsernameField = new JTextField(15);
        newUsernameField.setPreferredSize(new Dimension(120,35));

        newPasswordField = new JPasswordField(15);
        newPasswordField.setPreferredSize(new Dimension(120,35));

        saveButton = new JButton("Save");
        saveButton.setBackground(Color.yellow);
        saveButton.setPreferredSize(new Dimension(100,40));

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (authenticate(oldUsernameField.getText(), new String(oldPasswordField.getPassword()))) {
                    updateProfile(newUsernameField.getText(), new String(newPasswordField.getPassword()));
                    new ClientHomeFrame(newUsernameField.getText());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid old username or password.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Old Username:"));
        panel.add(oldUsernameField);
        panel.add(new JLabel("Old Password:"));
        panel.add(oldPasswordField);
        panel.add(new JLabel("New Username:"));
        panel.add(newUsernameField);
        panel.add(new JLabel("New Password:"));
        panel.add(newPasswordField);
        panel.add(saveButton);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
        panel.setPreferredSize(new Dimension(250,400));

        add(panel);
        setVisible(true);
    }

    private boolean authenticate(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("clients.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void updateProfile(String newUsername, String newPassword) {
        File inputFile = new File("clients.txt");
        File tempFile = new File("clients_temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(oldUsername)) {
                    line = newUsername + "," + newPassword;
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.out.println("Error updating profile.");
        }
    }
}
