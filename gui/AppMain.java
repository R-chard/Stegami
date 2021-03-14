package gui;

import java.awt.CardLayout;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AppMain extends JFrame {
    private static AppMain instance = null;
    private JPanel mainPanel, encodePanel, decodePanel, contentPanel, resultPanel;
    private CardLayout card;

    public static AppMain getInstance() {
        if (instance == null)
            instance = new AppMain();
        return instance;
    }

    public AppMain() {

        contentPanel = new JPanel();
        card = new CardLayout();
        contentPanel.setLayout(card);

        mainPanel = new MainPanel();
        encodePanel = EncodePanel.getInstance();
        decodePanel = DecodePanel.getInstance();
        resultPanel = ResultPanel.getInstance();

        contentPanel.add(PanelName.MAIN.getName(), mainPanel);
        contentPanel.add(PanelName.ENCODE.getName(), encodePanel);
        contentPanel.add(PanelName.DECODE.getName(), decodePanel);
        contentPanel.add(PanelName.PREVIEW.getName(), resultPanel);
        
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.add(contentPanel, BorderLayout.CENTER);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        card.show(contentPanel, PanelName.MAIN.getName());
    }

    public void toPanel(PanelName nxt) {
        card.show(contentPanel, nxt.getName());
    }
}
