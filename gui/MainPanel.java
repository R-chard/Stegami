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
    private static MainPanel instance = null;

    public static MainPanel getInstance() {
        if (instance == null)
            instance = new MainPanel();
        return instance;
    }

    private static final String[] IMAGE_TYPES = new String[]{"Grayscale", "Coloured"};
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private JPanel iconPanel;
    private JButton encodeButton, decodeButton, imageTypeButton;

    private Boolean isGrayscale = true;

    public MainPanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        iconPanel = new JPanel();
        iconPanel.setBounds(200, 50, 200, 200);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon(new ImageIcon("asset/icon.png").getImage().getScaledInstance(200, 180, Image.SCALE_DEFAULT)));
        iconPanel.add(iconLabel);
        iconPanel.setBackground(Color.BLACK);
        this.add(iconPanel);

        encodeButton = new JButton("Encode");
        encodeButton.setBackground(Color.WHITE);
        encodeButton.setBounds(200, 300, 100, 40);
        this.add(encodeButton);

        
        decodeButton = new JButton("Decode");
        decodeButton.setBackground(Color.WHITE);
        decodeButton.setBounds(300, 300, 100, 40);
        this.add(decodeButton);

        
        imageTypeButton = new JButton(IMAGE_TYPES[0]);
        imageTypeButton.setBackground(Color.WHITE);
        imageTypeButton.setBounds(200, 350, 200, 30);
        this.add(imageTypeButton);


        addListener();
    }

    public Boolean is_grayscale() {
        return this.isGrayscale;
    }

    public void toggle_image_type() {
        this.isGrayscale = !this.isGrayscale;
        imageTypeButton.setText(IMAGE_TYPES[this.isGrayscale? 0 : 1]);
    }

    private void addListener() {
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppMain.getInstance().toPanel(PanelName.ENCODE);
            }
        });

        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppMain.getInstance().toPanel(PanelName.DECODE);
            }
        });

        imageTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.getInstance().toggle_image_type();
            }
        });
    }
}
