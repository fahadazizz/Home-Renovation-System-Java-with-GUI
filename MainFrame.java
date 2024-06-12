import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private JButton clientButton, adminButton;

    public MainFrame() {
        setTitle("Home Renovation System");
        setSize(300, 630);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0,150));
        setForeground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clientButton = new JButton("Client");
        clientButton.setPreferredSize(new Dimension(130, 40));
        clientButton.setBorderPainted(false);
        clientButton.setBackground(Color.yellow);
        clientButton.setSize(120, 35);

        adminButton = new JButton("Admin");
        adminButton.setPreferredSize(new Dimension(130, 40));
        adminButton.setBorderPainted(false);
        adminButton.setBackground(Color.yellow);
        adminButton.setSize(120, 35);

        clientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClientLoginFrame();
                dispose();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminLoginFrame();
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.insets = new Insets(10, 0, 20, 0);
        panel.add(clientButton, g);

        g.gridx = 0;
        g.gridy = 3;
        g.insets = new Insets(20, 0, 25, 0);
        panel.add(adminButton, g);
        panel.setAlignmentY(CENTER_ALIGNMENT);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
