import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;

public class ClientSignUpFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signUpButton;

    public ClientSignUpFrame() {
        setTitle("Client Sign Up");
        setSize(300, 630);
        setResizable(false);
        setForeground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER,0,150));

        usernameField = new JTextField(15);
        usernameField.setPreferredSize(new Dimension(100,35));

        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(100,35));

        signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(100,35));
        signUpButton.setBackground(new Color(211,186,197));

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (register(username, password)) {
                    JOptionPane.showMessageDialog(null, "Registration successful. Please login.");
                    new ClientLoginFrame();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username already exists.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(signUpButton);
        panel.setPreferredSize(new Dimension(250,150));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,5,15));
        panel.setForeground(Color.white);
        
        add(panel);
        setVisible(true);
    }

    private boolean register(String username, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("clients.txt", true))) {
            BufferedReader br = new BufferedReader(new FileReader("clients.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return false;
                }
            }
            br.close();
            bw.write(username + "," + password);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
