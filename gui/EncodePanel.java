package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Font;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import Utils.ImageSteganography;
import Utils.SteganographyException;

@SuppressWarnings("serial")
public class EncodePanel extends JPanel{

	private static EncodePanel instance = null;
	private BufferedImage container, secret;
	private PreviewPanel containPreviewPanel,secretPreviewPanel;
	private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
	ImageSteganography is;

    public static EncodePanel getInstance() {
        if (instance == null)
            instance = new EncodePanel();
        return instance;
    }

    public EncodePanel(){
		is = new ImageSteganography();
        this.setLayout(null);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
        containPreviewPanel = new PreviewPanel(1); 
		containPreviewPanel.setBounds(0, 0, 300, 300);
		containPreviewPanel.updateImage(null);

		JTextArea containerText = new JTextArea("Select Container Image");
        containerText.setForeground(Color.WHITE);
        containerText.setBackground(Color.BLACK);
        containerText.setBounds(60, 300, 200, 50);
		Font boldFontC=new Font(containerText.getFont().getName(), Font.BOLD, containerText.getFont().getSize()+5);
        containerText.setFont(boldFontC);

		secretPreviewPanel = new PreviewPanel(2);
		secretPreviewPanel.setBounds(300, 0, 300, 300);
		secretPreviewPanel.updateImage(null);
		
		JTextArea secretText = new JTextArea("Select Image to Hide");
        secretText.setForeground(Color.WHITE);
        secretText.setBackground(Color.BLACK);
        secretText.setBounds(370, 300, 200, 50);
		Font boldFontS=new Font(secretText.getFont().getName(), Font.BOLD, secretText.getFont().getSize()+5);
        secretText.setFont(boldFontS);

		JPanel bottomPanel = createBottomPanel();
		bottomPanel.setBounds(0,350,600,100);

		add(containPreviewPanel);
		add(containerText);
		add(secretPreviewPanel);
		add(secretText);
		add(bottomPanel);
    }

	public void getContainerImage(String path) {
        try {
            container = ImageIO.read(new File(path));
            containPreviewPanel.updateImage(container);
        }
        catch (IOException ioe) {}
    }

	public void getSecretImage(String path) {
        try {
            secret = ImageIO.read(new File(path));
			secretPreviewPanel.updateImage(secret);
        }
        catch (IOException ioe) {}
    }

	private JPanel createBottomPanel(){
        JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);

		JButton prevButton = createPrevButton();
		prevButton.setBounds(180,5,100,50);

        JButton encodeButton = createEncodeButton();
		encodeButton.setBounds(320,5,100,50);
        
		bottomPanel.add(prevButton);
        bottomPanel.add(encodeButton);
		bottomPanel.setBackground(Color.BLACK);
        return bottomPanel;
    }


	private JButton createEncodeButton() {
        
        JButton encodeButton = new JButton("Encode");
		encodeButton.setBackground(Color.WHITE);
        encodeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				if(container == null || secret == null){
                    JOptionPane.showMessageDialog(null, "Please select an image", "Error",
					JOptionPane.PLAIN_MESSAGE);
					return;
                }
				try{
					is.encode(container, secret);
					ResultPanel.getInstance().updateImage(container);
					AppMain.getInstance().toPanel(PanelName.PREVIEW);

				} catch(SteganographyException se){
					JOptionPane.showMessageDialog(null, se.getMessage(), "Error",
				    JOptionPane.PLAIN_MESSAGE);
				}
				catch (Exception err) {
					err.printStackTrace();
				}
			}
        });

        return encodeButton;
    }

	private JButton createPrevButton() {
        
        JButton prevButton = new JButton("Prev");
		prevButton.setBackground(Color.WHITE);
        prevButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				try{
					// TODO remove container, secret
					// EncodePanel.getInstance().removeImage();
					AppMain.getInstance().toPanel(PanelName.MAIN);

				} catch (Exception err) {
					err.printStackTrace();
				}
			}
        });
        return prevButton;
    }
}


