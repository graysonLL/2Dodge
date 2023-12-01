package Window;

import Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JPanel {
    JButton startButton;
    JPanel cards;

    public StartMenu(JPanel cards, GamePanel gamePanel) {
        this.cards = cards;
        setLayout(null);
        setBackground(Color.BLACK);

        JPanel titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.green);

        JLabel titleNameLabel = new JLabel("2Dodge");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(new Font("Sans serif", Font.BOLD, 30));

        titleNamePanel.add(titleNameLabel);

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.blue);

        startButton = new JButton("START");
        startButton.setForeground(Color.white);
        startButton.setBackground(Color.black);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "GamePanel");

                gamePanel.startGameThread();

                gamePanel.requestFocusInWindow();
            }
        });

        startButtonPanel.add(startButton);

        add(titleNamePanel);
        add(startButtonPanel);
    }
}

