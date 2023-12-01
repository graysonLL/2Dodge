package Game;

import Window.StartMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2Dodge");

        JPanel cards = new JPanel(new CardLayout());

        GamePanel gamePanel = new GamePanel();
        StartMenu startMenu = new StartMenu(cards,gamePanel);

        cards.add(startMenu, "StartMenu");
        cards.add(gamePanel, "GamePanel");

        window.add(cards);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}