package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;
import Utils.ImageSteganography;

public class DecodePanel extends JPanel{

    private final String SUPPORTED_FILE_TYPE = "png";
	private BufferedImage encodedImg, secret;
	ImageSteganography is;

    public DecodePanel(){
        setLayout(new BorderLayout());
        add(createDefaultImgPanel(),BorderLayout.CENTER);
        add(createBottomPanel(),BorderLayout.SOUTH);
    }

    private JPanel createBottomPanel(){
        JPanel bottomPanel = new JPanel();
        JButton decodeButton = createDecodeButton();
        
        bottomPanel.add(decodeButton);
        return bottomPanel;
    }

    private JPanel createDefaultImgPanel(){
        JPanel defaultImgPanel = new JPanel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("asset/default.png").getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT));
        JButton button = new JButton(imageIcon);

        button.addActionListener(new ActionListener(){
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
						encodedImg = ImageIO.read(file);
						secret = is.decode(encodedImg);

					} catch (Exception err) {
						// Exception is displayed as a JOptionPane to the user
						JOptionPane.showMessageDialog(button, "An error occured while loading file", "Error",
								JOptionPane.PLAIN_MESSAGE);
						err.printStackTrace();
					}
				}
			}
        });

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        defaultImgPanel.add(button);

        return defaultImgPanel;
    }

	private JButton createDecodeButton() { 
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
						fileName = chooser.getSelectedFile().getAbsolutePath(); 
                        
                        File outputfile = new File(fileName);
                        ImageIO.write(secret, "png", outputfile);
					}

				} catch (Exception err) {
					err.printStackTrace();
				}

			}
        });
        //File file = chooser.getSelectedFile();
        
        return saveButton;
    }
}
