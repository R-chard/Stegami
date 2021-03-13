import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

public class JFileChooserDemo extends JPanel {

 private JTextArea textArea;

 public JFileChooserDemo() {
  super(new BorderLayout());
   createPanel();
   }

 private void createPanel() {
JButton openFileChooser = new JButton("Apri File");
JButton saveFileChooser = new JButton("Salva File");
openFileChooser.addActionListener(new OpenFileChooser());
saveFileChooser.addActionListener(new SaveFileChooser());
textArea = new JTextArea(10, 20);
add(new JScrollPane(textArea), BorderLayout.CENTER);
JPanel panelButton = new JPanel();
panelButton.add(openFileChooser);
panelButton.add(saveFileChooser);
 add(panelButton, BorderLayout.SOUTH);
  }

   private class OpenFileChooser implements ActionListener {

   public void actionPerformed(ActionEvent e) {
  try {
    textArea.setText("");
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new TxtFileFilter());
    int n = fileChooser.showOpenDialog(JFileChooserDemo.this);
    if (n == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      BufferedReader read = new BufferedReader(new FileReader(f));
      String line = read.readLine();
      while(line != null) {
        textArea.append(line);
        line = read.readLine();
      }
      read.close();
    }
   } catch (Exception ex) {}
   }
 }

  private class SaveFileChooser implements ActionListener {

 public void actionPerformed(ActionEvent e) {
  try {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new TxtFileFilter());
    int n = fileChooser.showSaveDialog(JFileChooserDemo.this);
    if (n == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      BufferedWriter write = new BufferedWriter(new FileWriter(f));
      write.append(textArea.getText());
      write.flush();
      write.close();
    }
  } catch (Exception ex) {}
  }
 }

 private class TxtFileFilter extends FileFilter {

public boolean accept(File file) {
  if (file.isDirectory()) return true;
    String fname = file.getName().toLowerCase();
    return fname.endsWith("txt");
}

  public String getDescription() {
  return "File di testo";
  }
}

 public static void main(String[] argv) {
JFrame frame = new JFrame("JFileChooserDemo");
JFileChooserDemo demo = new JFileChooserDemo();
frame.getContentPane().add(demo);
frame.pack();
frame.setVisible(true);
 }
}
