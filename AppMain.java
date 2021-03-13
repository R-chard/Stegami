import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AppMain extends JFrame{
    private final Dimension APP_SIZE = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
			Toolkit.getDefaultToolkit().getScreenSize().height / 2);

    public AppMain(){
        setTitle("Image Hider");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.add(createImagePanel(),BorderLayout.NORTH);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setVisible(true);
        setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(createEncodePageButton(bottomPanel));
        bottomPanel.add(createDecodePageButton(bottomPanel));

        mainPanel.add(bottomPanel,BorderLayout.SOUTH);

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
        imagePanel.setPreferredSize(new Dimension(500, 400));
        try{
            BufferedImage myPicture = ImageIO.read(new File("asset/icon.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            imagePanel.add(picLabel);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        return imagePanel;
        
    }
}
