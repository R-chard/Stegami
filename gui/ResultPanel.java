package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ResultPanel extends JPanel{
    private static ResultPanel instance = null;

    public static ResultPanel getInstance() {
        if (instance == null)
            instance = new ResultPanel();
        return instance;
    }
    BufferedImage resultImg;
    public ResultPanel(){
        add(createPrevButton());
        add(createSaveButton());
    }

    public void updateImage(BufferedImage resultImg) {
        System.out.println("update img method");
        System.out.println(resultImg);
        this.resultImg = resultImg;
    }

    public void saveImage(String fileName) {
        // save
        File outputfile = new File(fileName);
        try{
            ImageIO.write(resultImg, "png", outputfile);
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
        
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
		
                        System.out.println(resultImg);
                        ResultPanel.getInstance().saveImage(fileName);
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
