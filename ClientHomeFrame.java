import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;

public class ClientHomeFrame extends JFrame {
    private String username;
    private JButton selectServiceButton, homeDetailButton, requestWorkButton, generateBillButton, editProfileButton, logoutButton;

    public ClientHomeFrame(String username) {
        this.username = username;

        setTitle("Client Home");
        setSize(300, 630);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER,0,50));

        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        selectServiceButton = new JButton("Select Service");
        selectServiceButton.setBackground(Color.orange);
        selectServiceButton.setForeground(Color.white);
        selectServiceButton.setPreferredSize(new Dimension(150,40));
        
        homeDetailButton = new JButton("Home Detail");
        homeDetailButton.setForeground(Color.white);
        homeDetailButton.setBackground(Color.orange);
        homeDetailButton.setPreferredSize(new Dimension(150,40));

        requestWorkButton = new JButton("Request for Work");
        requestWorkButton.setForeground(Color.white);
        requestWorkButton.setBackground(Color.orange);
        requestWorkButton.setPreferredSize(new Dimension(150,40));

        generateBillButton = new JButton("Generate Bill");
        generateBillButton.setForeground(Color.white);
        generateBillButton.setBackground(Color.orange);
        generateBillButton.setPreferredSize(new Dimension(150,40));

        editProfileButton = new JButton("Edit Profile");
        editProfileButton.setForeground(Color.white);
        editProfileButton.setBackground(Color.orange);
        editProfileButton.setPreferredSize(new Dimension(150,40));
        
        logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.white);
        logoutButton.setBackground(Color.orange);
        logoutButton.setPreferredSize(new Dimension(150,40));
        
        
        selectServiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ServiceSelectionFrame(username);
                dispose();
            }
        });

        homeDetailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomeDetailFrame(username);
                dispose();
            }
        });

        requestWorkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WorkRequestFrame(username);
                dispose();
            }
        });

        generateBillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BillGenerationFrame(username);
                dispose();
            }
        });

        editProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditProfileFrame(username);
                dispose();
            }
        });
        
        logoutButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               new MainFrame();
               dispose();
           }
        });

        JPanel panel = new JPanel();
        panel.add(welcomeLabel);
        panel.add(selectServiceButton);
        panel.add(homeDetailButton);
        panel.add(requestWorkButton);
        panel.add(generateBillButton);
        panel.add(editProfileButton);
        panel.add(logoutButton);
        panel.setPreferredSize(new Dimension(150,450));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,20));

        add(panel);
        setVisible(true);
    }
}
