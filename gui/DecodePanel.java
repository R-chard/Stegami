package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Utils.ImageSteganography;

@SuppressWarnings("serial")
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
		//add(createPrevButton());
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
        JButton saveButton = new JButton("Decode");
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
				try{
					secret = is.decode(container);
					ResultPanel.getInstance().updateImage(secret);
					AppMain.getInstance().toPanel(PanelName.PREVIEW);
				} catch (Exception err) {
					err.printStackTrace();
				}
			}
        });
        return decodeButton;
    }*/

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
        return prevButton;
    } 
}
