import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;

public class WorkRequestFrame extends JFrame {
    private String username;
    private JTextArea requestDetails;
    private JButton requestButton;

    public WorkRequestFrame(String username) {
        this.username = username;
        setTitle("Request for Work");
        setSize(300, 630);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        requestDetails = new JTextArea(10, 25);
        requestButton = new JButton("Request");
        requestButton.setPreferredSize(new Dimension(100,40));
        requestButton.setBackground(Color.yellow);

        requestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveRequest();
                new ClientHomeFrame(username);
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        panel.setPreferredSize(new Dimension(250,450));
        JScrollPane pane = new JScrollPane(requestDetails);
        pane.setPreferredSize(new Dimension(230,300));
        panel.add(pane);
        panel.add(requestButton);

        loadSelectedServices();
        loadHomeDetails();

        add(panel);
        setVisible(true);
    }

    private void loadSelectedServices() {
        try (BufferedReader br = new BufferedReader(new FileReader(username + "_services.txt"))) {
            String line;
            requestDetails.append("Selected Services:\n");
            while ((line = br.readLine()) != null) {
                requestDetails.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHomeDetails() {
        try (BufferedReader br = new BufferedReader(new FileReader(username + "_home_details.txt"))) {
            String line;
            requestDetails.append("\nHome Details:\n");
            while ((line = br.readLine()) != null) {
                requestDetails.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveRequest() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("requests.txt", true))) {
            bw.write("Username: " + username);
            bw.newLine();
            bw.write(requestDetails.getText());
            bw.newLine();
            bw.write("-----");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
