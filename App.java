

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

import java.awt.event.*;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App extends JFrame{
    private final Dimension APP_SIZE = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
			Toolkit.getDefaultToolkit().getScreenSize().height / 2);

    private final String SUPPORTED_FILE_TYPE = "png";

    public App(){
        setTitle("Image Hider");
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        addDecodeButton();

        mainPanel.add(createSecretButton());
        mainPanel.add(createContainerButton());
        

        getContentPane().add(mainPanel);

        addImagePanel();

        setSize(APP_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createContainerButton(){
        JButton normButton = new JButton("Upload Container Image");
        normButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("." + SUPPORTED_FILE_TYPE, SUPPORTED_FILE_TYPE));
				chooser.setCurrentDirectory(new File("."));
				chooser.setDialogTitle("Open File");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int response = chooser.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					try {

						// Retrieving data saved in file through input streams before data is
						// unmarshalled to an object
						File file = chooser.getSelectedFile();
						FileInputStream fileInputStream = new FileInputStream(file);

                        fileInputStream.close();

					} catch (Exception err) {
						// Exception is displayed as a JOptionPane to the user
						JOptionPane.showMessageDialog(normButton, "An error occured while loading file", "Error",
								JOptionPane.PLAIN_MESSAGE);
						err.printStackTrace();
					}

				}
			}
        });
        return normButton;
    }

    private JButton createSecretButton(){

        JButton normButton = new JButton("Upload Secret Image");
        normButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("." + SUPPORTED_FILE_TYPE, SUPPORTED_FILE_TYPE));
				chooser.setCurrentDirectory(new File("."));
				chooser.setDialogTitle("Open File");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int response = chooser.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					try {

						// Retrieving data saved in file through input streams before data is
						// unmarshalled to an object
						File file = chooser.getSelectedFile();
						FileInputStream fileInputStream = new FileInputStream(file);

                        fileInputStream.close();

					} catch (Exception err) {
						// Exception is displayed as a JOptionPane to the user
						JOptionPane.showMessageDialog(normButton, "An error occured while loading file", "Error",
								JOptionPane.PLAIN_MESSAGE);
						err.printStackTrace();
					}

				}
			}
        });
        return normButton;
    }

    private void addDecodeButton(){
        
    }

    private void addImagePanel(){
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setPreferredSize(new Dimension(500, 400));
        try{
            BufferedImage myPicture = ImageIO.read(new File("icon.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            imagePanel.add(picLabel);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        getContentPane().add(imagePanel, BorderLayout.NORTH);
        
    }
}


