import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EncodePanel extends JPanel{

    private final String SUPPORTED_FILE_TYPE = "png";

    public EncodePanel(){
        setBackground(Color.CYAN);
        add(createSecretButton());
        add(createContainerButton());
        //addImagePanel();

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

						// Retrieving data saved in file through input streams before data is
						// unmarshalled to an object
						File file = chooser.getSelectedFile();
						FileInputStream fileInputStream = new FileInputStream(file);

                        fileInputStream.close();

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
						FileInputStream fileInputStream = new FileInputStream(file);

                        fileInputStream.close();

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
}


