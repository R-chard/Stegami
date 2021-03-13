package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import Utils.ImageSteganography;

@SuppressWarnings("serial")
public class EncodePanel extends JPanel{

	private static EncodePanel instance = null;
	private BufferedImage container, secret;
	private PreviewPanel containPreviewPanel,secretPreviewPanel;
	ImageSteganography is;

    public static EncodePanel getInstance() {
        if (instance == null)
            instance = new EncodePanel();
        return instance;
    }

    public EncodePanel(){
		is = new ImageSteganography();
        setLayout(new BorderLayout());
        containPreviewPanel = new PreviewPanel(1); 
		secretPreviewPanel = new PreviewPanel(2);
		add(containPreviewPanel,BorderLayout.WEST);
		add(secretPreviewPanel,BorderLayout.EAST);
		add(createBottomPanel());
    }

	public void getContainerImage(String path) {
		System.out.println(path);
        try {
            container = ImageIO.read(new File(path));
            containPreviewPanel.updateImage(container);
        }
        catch (IOException ioe) {}
    }

	public void getSecretImage(String path) {
		System.out.println(path);
        try {
            secret = ImageIO.read(new File(path));
			secretPreviewPanel.updateImage(secret);
        }
        catch (IOException ioe) {}
    }

	private JPanel createBottomPanel(){
        JPanel bottomPanel = new JPanel();
        JButton decodeButton = createEncodeButton();
        JButton prevButton = createPrevButton();

        bottomPanel.add(decodeButton);
        bottomPanel.add(prevButton);
        
        return bottomPanel;
    }


	private JButton createEncodeButton() {
        
        JButton encodeButton = new JButton("Encode");
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

				} catch (Exception err) {
					err.printStackTrace();
				}
			}
        });

        return encodeButton;
    }

	private JButton createPrevButton() {
        
        JButton prevButton = new JButton("Prev");
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


