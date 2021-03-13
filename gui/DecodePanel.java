package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Utils.ImageSteganography;

@SuppressWarnings("serial")
public class DecodePanel extends JPanel{

    private final String SUPPORTED_FILE_TYPE = "png";
	private BufferedImage container, secret;
	ImageSteganography is;

    public DecodePanel(){
		is = new ImageSteganography();
        setBackground(Color.BLUE);
		add(createContainerButton());
        add(createDecodeButton());
		add(createPrevButton());
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

    private JButton createDecodeButton(){
        JButton decodeButton = new JButton("Decode");
        decodeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				try{
					secret = is.decode(container);
					ResultPanel.getInstance().updateImage(secret);
					AppMain.getInstance().toPanel(PanelName.PREVIEW);
				} catch (Exception err) {
					err.printStackTrace();
				}
			}
        });
        return decodeButton;
    }

	private JButton createPrevButton() {
        
        JButton prevButton = new JButton("Prev");
        prevButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				try{
					AppMain.getInstance().toPanel(PanelName.MAIN);

				} catch (Exception err) {
					err.printStackTrace();
				}
			}
        });
        //File file = chooser.getSelectedFile();

        
        return prevButton;
    }
}
