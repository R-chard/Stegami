package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import Utils.ImageUtils;

@SuppressWarnings("serial")
public class ResultPanel extends JPanel{
    BufferedImage resultImg;

    private static ResultPanel instance = null;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    JPanel imagePanel;

    public static ResultPanel getInstance() {
        if (instance == null)
            instance = new ResultPanel();
        return instance;
    }
    
    public ResultPanel(){
        this.setLayout(null);
        
        imagePanel = new JPanel();
        imagePanel.setBounds(0,0,600,300);
        setBackground(Color.BLACK);

        add(imagePanel);
        JPanel bottomPanel = createBottomPanel();
        bottomPanel.setBounds(0,350,600,50);
        add(bottomPanel);
    }

    private JPanel createBottomPanel(){
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        JButton prevButton = createPrevButton();
		prevButton.setBounds(180,0,100,50);

        JButton saveButton = createSaveButton();
		saveButton.setBounds(320,0,100,50);

        bottomPanel.add(prevButton);
        bottomPanel.add(saveButton);
        bottomPanel.setBackground(Color.BLACK);
        return bottomPanel;
    }

    public void updateImage(BufferedImage resultImg) {
        this.resultImg = resultImg;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        imagePanel.removeAll();
        imagePanel.add(new JLabel(new ImageIcon(ImageUtils.resize(WIDTH, HEIGHT, resultImg))));
        validate();
    }

    public void removeImage() {
        resultImg = null;
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
        saveButton.setBackground(Color.WHITE);
        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser chooser = new JFileChooser();
                    chooser.setSelectedFile(new File("result.png"));
					chooser.setCurrentDirectory(new File("."));

					if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						String fileName = chooser.getSelectedFile().getAbsolutePath();
		
                        ResultPanel.getInstance().saveImage(fileName);
                        AppMain.getInstance().showDialog("Save image to " + fileName);
                        AppMain.getInstance().toPanel(PanelName.MAIN);
					} else {
                        AppMain.getInstance().showDialog("Unable to save result at the moment!");
                    }
				} catch (Exception err) {
					err.printStackTrace();
				}

			}
        });

        
        return saveButton;
    }

    private JButton createPrevButton() {
        
        JButton prevButton = new JButton("Prev");
        prevButton.setBackground(Color.WHITE);
        prevButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				try{
                    
                    ResultPanel.getInstance().removeImage();
					AppMain.getInstance().toPanel(PanelName.MAIN);
                    
				} catch (Exception err) {
					err.printStackTrace();
				}
			}
        });

        
        return prevButton;
    }
}
