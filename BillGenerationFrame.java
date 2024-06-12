import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class BillGenerationFrame extends JFrame {
    private String username;
    private JTextArea billDetails;

    public BillGenerationFrame(String username) {
        this.username = username;
        setTitle("Generate Bill");
        setForeground(Color.white);
        setSize(300, 630);
        setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        billDetails = new JTextArea(10, 25);

        JButton button = new JButton("Back");
        button.setPreferredSize(new Dimension(100,40));
        button.setBackground(Color.yellow);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new ClientHomeFrame(username);
            }
        });
        JPanel panel = new JPanel();
        JScrollPane pane = new JScrollPane((billDetails));
        pane.setPreferredSize(new Dimension(250,300));

        panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
        panel.setPreferredSize(new Dimension(250,500));

        panel.add(pane);
        panel.add(button); 
        
        loadSelectedServices();
        loadHomeDetails();

        add(panel);
        setVisible(true);
    }

    private void loadSelectedServices() {
        try (BufferedReader br = new BufferedReader(new FileReader(username + "_services.txt"))) {
            String line;
            billDetails.append("Selected Services:\n");
            while ((line = br.readLine()) != null) {
                billDetails.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHomeDetails() {
        try (BufferedReader br = new BufferedReader(new FileReader(username + "_home_details.txt"))) {
            String line;
            billDetails.append("\nHome Details:\n");
            while ((line = br.readLine()) != null) {
                billDetails.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
