package gui;

import java.awt.BorderLayout;
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
public class EncodePanel extends JPanel{

    private final String SUPPORTED_FILE_TYPE = "png";
	private BufferedImage container,secret;
	private PreviewPanel previewPanel;
	ImageSteganography is;

	private static EncodePanel instance = null;

    public static EncodePanel getInstance() {
        if (instance == null)
            instance = new EncodePanel();
        return instance;
    }

    public EncodePanel(){
		is = new ImageSteganography();
        setBackground(Color.CYAN);
		previewPanel = new PreviewPanel(); 
        add(previewPanel,BorderLayout.CENTER);
        add(createSecretButton());
        add(createContainerButton());
		add(createPrevButton());
		add(createEncodeButton(this));
    }

	public void removeImage() {
        this.container = null;
		this.secret = null;
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
						File file = chooser.getSelectedFile();
                        container = ImageIO.read(file);
                        

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

	private JButton createEncodeButton(JPanel panel) {
        
        JButton encodeButton = new JButton("Encode");
        encodeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				try{
					System.out.println("encode clicked");
					is.encode(container, secret);
					
					ResultPanel.getInstance().updateImage(container);
					System.out.println(container);
					AppMain.getInstance().toPanel(PanelName.PREVIEW);

				} catch (Exception err) {
					err.printStackTrace();
				}
			}
        });
        //File file = chooser.getSelectedFile();

        
        return encodeButton;
    }

	private JButton createPrevButton() {
        
        JButton prevButton = new JButton("Prev");
        prevButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				try{
					// TODO remove container, secret
					EncodePanel.getInstance().removeImage();
					AppMain.getInstance().toPanel(PanelName.MAIN);

				} catch (Exception err) {
					err.printStackTrace();
				}
			}
        });
        

        
        return prevButton;
    }
}


