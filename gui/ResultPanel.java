package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ResultPanel extends JPanel{
    BufferedImage resultImg;
    private PreviewPanel previewPanel;

    private static ResultPanel instance = null;
    private final String DEFAULT_IMAGE_PATH = "asset/default.png";
    
    private JPanel resultPanel;

    public static ResultPanel getInstance() {
        if (instance == null)
            instance = new ResultPanel();
        return instance;
    }
    
    public ResultPanel(){
        try{
            resultImg = ImageIO.read(new File(DEFAULT_IMAGE_PATH));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(createPrevButton());
        add(createSaveButton());
        resultPanel = new JPanel();
        add(resultPanel);
    }

    public void getImage(String path) {
        try {
            resultImg = ImageIO.read(new File(path));
            System.out.println("result img" + resultImg);
            previewPanel.updateImage(resultImg);
            System.out.println("result img");
            System.out.println(resultImg);
        }
        catch (IOException ioe) {}
    }

    public void updateImage(BufferedImage resultImg) {
        System.out.println("update img method");
        System.out.println(resultImg);
        this.resultImg = resultImg;
        JLabel resultLabel = new JLabel();
        resultLabel.setIcon(new ImageIcon(resultImg));
        resultPanel.removeAll();
        resultPanel.add(resultLabel);
    }

    public void removeImage() {
        try{
            resultImg = ImageIO.read(new File("assest/default.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
					if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
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
                    // TODO remove result img + encode img
                    ResultPanel.getInstance().removeImage();
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
