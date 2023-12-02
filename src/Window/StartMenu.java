package Window;

import Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartMenu extends JPanel {
    private JPanel cards;
    private ImageIcon scaledBackgroundImage;

    public StartMenu(JPanel cards, GamePanel gamePanel) {
        this.cards = cards;
        setLayout(null);

        ImageIcon originalBackgroundImage = new ImageIcon(getClass().getResource("/Background/BG_Project2.png"));
        Image originalImage = originalBackgroundImage.getImage();

        JLabel backgroundLabel = new JLabel(originalBackgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int scaledWidth = getWidth();
                int scaledHeight = getHeight();

                Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                scaledBackgroundImage = new ImageIcon(scaledImage);

                backgroundLabel.setIcon(scaledBackgroundImage);
                backgroundLabel.setBounds(0, 0, scaledWidth, scaledHeight);
            }
        });


        ImageIcon startButtonImage = new ImageIcon(getClass().getResource("/Background/Start.png"));


        JLabel startButtonLabel = new JLabel(startButtonImage);
        startButtonLabel.setBounds(230, 400, 300, 150);

        startButtonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "GamePanel");

                gamePanel.startGameThread();
                gamePanel.requestFocusInWindow();
            }
        });

        add(startButtonLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}