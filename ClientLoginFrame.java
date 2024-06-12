import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;

public class ClientLoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;

    public ClientLoginFrame() {
        setTitle("Client Login");
        setSize(300, 630);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER,0,150));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usernameField = new JTextField(15);
        usernameField.setPreferredSize(new Dimension(100,35));

        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(100,35));

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100,35));
        loginButton.setBackground(new Color(211,186,197));

        signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(100,35));
        signUpButton.setBackground(new Color(211,186,197));

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticate(username, password)) {
                    new ClientHomeFrame(username);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClientSignUpFrame();
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signUpButton);
        panel.setPreferredSize(new Dimension(250,150));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,5,15));
        panel.setForeground(Color.white);

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
}
