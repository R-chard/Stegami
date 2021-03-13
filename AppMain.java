import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Image;

public class AppMain extends JFrame{
    private final Dimension APP_SIZE = new Dimension(650,600);

    public AppMain(){
        setTitle("Image Hider");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));
        mainPanel.add(createImagePanel());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setVisible(true);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(createEncodePageButton(bottomPanel));
        bottomPanel.add(createDecodePageButton(bottomPanel));

        mainPanel.add(bottomPanel);

        getContentPane().add(mainPanel);
    
        setSize(APP_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createEncodePageButton(JPanel bottomPanel){

        JButton encodePageButton = new JButton("Encode");
        encodePageButton.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e) {
                JPanel encodePanel = new EncodePanel();
                bottomPanel.removeAll();
                bottomPanel.add(encodePanel);
                bottomPanel.revalidate();
            }
        });
        
        return encodePageButton;
    }

    private JButton createDecodePageButton(JPanel bottomPanel){

        JButton encodePageButton = new JButton("Decode");
        encodePageButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JPanel decodePanel = new DecodePanel();
                bottomPanel.removeAll();
                bottomPanel.add(decodePanel);
                bottomPanel.revalidate();
            }
        });
        
        return encodePageButton;
    }

    private JPanel createImagePanel(){
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        //imagePanel.setPreferredSize(new Dimension(100, 100));
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("asset/icon.png").getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT));
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(imageIcon);
        imagePanel.add(iconLabel);
        return imagePanel;
    }
}
