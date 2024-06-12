import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

public class AdminHomeFrame extends JFrame {
    private JButton manageServicesButton, viewRequestsButton, logoutButton;

    public AdminHomeFrame() {
        setTitle("Admin Home");
        setSize(300, 630);
        setForeground(Color.white);
        setLayout(new FlowLayout(FlowLayout.CENTER,0,200));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        viewRequestsButton = new JButton("View Work Requests");
        viewRequestsButton.setBackground(Color.yellow);
        viewRequestsButton.setPreferredSize(new Dimension(130,40));

        logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.yellow);
        logoutButton.setPreferredSize(new Dimension(130,40));

        viewRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewRequestsFrame();
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
        panel.add(viewRequestsButton);
        panel.add(logoutButton);
        panel.setPreferredSize(new Dimension(150,300));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
        add(panel);
        setVisible(true);
    }
}
