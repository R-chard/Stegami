import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Image;

public class DecodePanel extends JPanel{

    private final String SUPPORTED_FILE_TYPE = "png";

    public DecodePanel(){
        add(createDefaultImgPanel());
        add(createBottomPanel());
    }

    private JPanel createBottomPanel(){
        JPanel bottomPanel = new JPanel();
        JButton decodeButton = new JButton();

        bottomPanel.add(decodeButton);
        return bottomPanel;
    }

    private JPanel createDefaultImgPanel(){
        JPanel defaultImgPanel = new JPanel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("asset/default.png").getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT));
        JButton button = new JButton(imageIcon);

        button.addActionListener(new ActionListener(){
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
						JOptionPane.showMessageDialog(button, "An error occured while loading file", "Error",
								JOptionPane.PLAIN_MESSAGE);
						err.printStackTrace();
					}

				}
			}
        });

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        defaultImgPanel.add(button);

        return defaultImgPanel;
    }
}
