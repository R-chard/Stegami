package gui;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;
import javax.imageio.ImageIO;

// Reusable preview class
public class PreviewPanel extends JPanel {

    private final String SUPPORTED_FILE_TYPE = "png";
    private final String DEFAULT_IMAGE_PATH = "asset/default.png";
    private String selectedImagePath = DEFAULT_IMAGE_PATH; 

    public PreviewPanel(){
        try{
            BufferedImage selectedImg = ImageIO.read(new File(DEFAULT_IMAGE_PATH));
            JButton button = createButton(selectedImg);
            add(button);
        } catch(IOException ioe){};
        
    };
    
    public void setSelectedImage(String filePath){
        selectedImagePath = filePath;
    }

    private JButton createButton(BufferedImage image){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT));
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

						File file = chooser.getSelectedFile();
                        DecodePanel.getInstance().getImage(file.getPath());

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

        return button;
    }

    public void updateImage(BufferedImage image){
        removeAll();
        add(createButton(image));
        validate();
    }
    
}
