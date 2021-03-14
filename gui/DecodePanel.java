package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Utils.ImageSteganography;

@SuppressWarnings("serial")
public class DecodePanel extends JPanel{
    private static DecodePanel instance = null;

	private BufferedImage secret, selectedImg;
    private PreviewPanel previewPanel;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
	ImageSteganography is;

    public DecodePanel(){
        is = new ImageSteganography();
        this.setLayout(null);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        previewPanel = new PreviewPanel(0);
        previewPanel.setBounds(0, 0, 600, 300);
        previewPanel.updateImage(null); 

        JTextArea textArea = new JTextArea("Select Image to Decode");
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);
        Font boldFont=new Font(textArea.getFont().getName(), Font.BOLD, textArea.getFont().getSize()+5);
        textArea.setFont(boldFont);
        textArea.setBounds(210, 300, 200, 50);

        JPanel bottomPanel = createBottomPanel();
        bottomPanel.setBounds(0,350,600,50);

        add(previewPanel);
        add(textArea);
        add(bottomPanel);
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
        bottomPanel.setLayout(null);
        JButton prevButton = createPrevButton();
		prevButton.setBounds(180,5,100,50);

        JButton decodeButton = createDecodeButton();
		decodeButton.setBounds(320,5,100,50);

        bottomPanel.add(prevButton);
        bottomPanel.add(decodeButton);
        bottomPanel.setBackground(Color.BLACK);
        return bottomPanel;
    }

    private JButton createDecodeButton(){
        JButton saveButton = new JButton("Decode");
        saveButton.setBackground(Color.WHITE);
        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(selectedImg == null){
                    JOptionPane.showMessageDialog(null, "Please select an image", "Error",
				    JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                try{
                    if (MainPanel.getInstance().is_grayscale()) secret = is.decode_grayscale(selectedImg);
                    else secret = is.decode_color(selectedImg);
                    
					ResultPanel.getInstance().updateImage(secret);
					AppMain.getInstance().toPanel(PanelName.PREVIEW);
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
					AppMain.getInstance().toPanel(PanelName.MAIN);

				} catch (Exception err) {
					err.printStackTrace();
				}
			}
        });
        
        return prevButton;
    } 
}
