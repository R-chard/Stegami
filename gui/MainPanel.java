package gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class MainPanel extends JPanel {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private JPanel iconPanel;
    private JButton encodeButton;
    private JButton decodeButton;

    public MainPanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        iconPanel = new JPanel();
        iconPanel.setBounds(200, 50, 200, 200);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon(new ImageIcon("asset/icon.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        iconPanel.add(iconLabel);
        iconPanel.setBackground(Color.BLACK);
        this.add(iconPanel);

        encodeButton = new JButton("Encode");
        encodeButton.setBounds(200, 300, 100, 50);
        this.add(encodeButton);

        
        decodeButton = new JButton("Decode");
        decodeButton.setBounds(300, 300, 100, 50);
        this.add(decodeButton);

        addListener();
    }

    
    private void addListener() {
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppMain.getInstance().toPanel("ENCODE");
            }
        });
    }
}
