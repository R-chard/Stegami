package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private static DecodePanel instance = null;

	private BufferedImage encodedImg, secret, selectedImg;
    private PreviewPanel previewPanel;
	ImageSteganography is;

    public DecodePanel(){
        is = new ImageSteganography();
        setLayout(new BorderLayout());
        previewPanel = new PreviewPanel(); 
        add(previewPanel,BorderLayout.CENTER);
        add(createBottomPanel(),BorderLayout.SOUTH);
    }

    public static DecodePanel getInstance() {
        if (instance == null)
            instance = new DecodePanel();
        return instance;
    }
    
    public void getImage(String path) {
        try {
            selectedImg = ImageIO.read(new File(path));
            previewPanel.updateImage(selectedImg);
        }
        catch (IOException ioe) {}
    }

    private JPanel createBottomPanel(){
        JPanel bottomPanel = new JPanel();
        JButton decodeButton = createDecodeButton();
        
        bottomPanel.add(decodeButton);
        
        return bottomPanel;
    }

    private JButton createDecodeButton(){
        JButton saveButton = new JButton("Save Image");
        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(selectedImg == null){
                    JOptionPane.showMessageDialog(null, "Please select an image", "Error",
				JOptionPane.PLAIN_MESSAGE);
                }
                try{
					secret = is.decode(selectedImg);
					ResultPanel.getInstance().updateImage(secret);
					AppMain.getInstance().toPanel(PanelName.PREVIEW);
				} catch (Exception err) {
					err.printStackTrace();
				}

			}
        });
        //File file = chooser.getSelectedFile();
        
        return saveButton;
    }

	/*private JButton createDecodeButton() { 
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
    } */
}
