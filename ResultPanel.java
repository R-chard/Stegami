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
    BufferedImage resultImg;
    public ResultPanel(BufferedImage resultImg){
        add(createSaveButton());
        this.resultImg = resultImg;
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
		
                        File outputfile = new File(fileName);
                        ImageIO.write(resultImg, "png", outputfile);
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
}
