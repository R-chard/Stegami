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

public class DecodePanel extends JPanel{

    private final String SUPPORTED_FILE_TYPE = "png";
	private BufferedImage encodedImg, secret;
	ImageSteganography is;

    public DecodePanel(){
		is = new ImageSteganography();
        setBackground(Color.BLUE);
        add(createDecodeButton());
		add(createSaveButton());
    }

    private JButton createDecodeButton(){
        JButton normButton = new JButton("Upload encoded image");
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
						encodedImg = ImageIO.read(file);
						secret = is.decode(encodedImg);

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
