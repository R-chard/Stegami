package gui;

import java.awt.CardLayout;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AppMain extends JFrame {
    private static AppMain instance = null;
    private JPanel mainPanel, encodePanel, decodePanel;
    private CardLayout card;
    private JPanel contentPanel;

    public static AppMain getInstance() {
        if (instance == null)
            instance = new AppMain();
        return instance;
    }

    public AppMain() {
        super("Hi");

        contentPanel = new JPanel();
        card = new CardLayout();
        contentPanel.setLayout(card);

        mainPanel = new MainPanel();
        encodePanel = new JPanel();
        decodePanel = new JPanel();

        contentPanel.add("MAIN", mainPanel);
        contentPanel.add("ENCODE", encodePanel);
        contentPanel.add("DECODE", decodePanel);
        
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.add(contentPanel, BorderLayout.CENTER);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        card.show(contentPanel, "MAIN");
    }

    public void toPanel(String name) {
        card.show(contentPanel, name);
    }
}
