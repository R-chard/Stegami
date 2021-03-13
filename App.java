import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Utils.*;

public class App extends JFrame{
    BufferedImage container, secret;
    ImageSteganography is = new ImageSteganography();
    private final Dimension APP_SIZE = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
			Toolkit.getDefaultToolkit().getScreenSize().height / 2);

    private final String SUPPORTED_FILE_TYPE = "png";

    public App(){
        
        setTitle("Image Hider");
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        mainPanel.add(createSecretButton());
        mainPanel.add(createContainerButton());
        mainPanel.add(createSaveButton());
        

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
						//FileInputStream fileInputStream = new FileInputStream(file);
                        
                        container = ImageIO.read(file);
                        // secret = ImageIO.read(new File("secret.jpg"));
                        
                        // is.to_grayscale(secret);

                        // is.encode(container, secret);
                        //fileInputStream.close();

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
						
                        
                        secret = ImageIO.read(file);

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

    private JButton createSaveButton() {
        
        JButton saveButton = new JButton("Save Image");
        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				try {

					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.setCurrentDirectory(new File("."));

					String fileName;

					// Manually creating the full path of the file
					if (chooser.showSaveDialog(null) == chooser.APPROVE_OPTION) {
						fileName = chooser.getSelectedFile().getAbsolutePath(); //TODO extension
                        is.encode(container, secret);
                        File outputfile = new File(fileName);
                        ImageIO.write(container, "png", outputfile);
					}
					
					System.out.println("saved");

				} catch (Exception err) {
					err.printStackTrace();
				}

			}
        });
        //File file = chooser.getSelectedFile();

        
        return saveButton;
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


