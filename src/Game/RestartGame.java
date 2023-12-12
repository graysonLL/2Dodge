package Game;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;
import Game.Score;

public class RestartGame {
    private BufferedImage restartImage;
    private Rectangle restartButtonBounds;
    private BufferedImage gameOverImage;
    private GamePanel gamePanel;

    public RestartGame(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        try {
            restartImage = ImageIO.read(getClass().getResourceAsStream("/Background/Restart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            gameOverImage = ImageIO.read(getClass().getResourceAsStream("/Background/GameOver.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set initial bounds for the button (adjust as needed)
        int buttonWidth = 300;
        int buttonHeight = 150;
        int x = (gamePanel.screenWidth - buttonWidth) / 2; // Centered horizontally
        int y = (gamePanel.screenHeight - buttonHeight) / 2 + 100; // Centered vertically
        restartButtonBounds = new Rectangle(x, y, buttonWidth, buttonHeight);

        // Add a mouse listener to the button
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if the mouse click is within the button bounds
                if (restartButtonBounds.contains(e.getPoint())) {
                    // Restart the game when the button is clicked
                    gamePanel.resetGameState();
                }
            }
        });
    }

    public void displayRestartWindow(Graphics2D g2, int highScore) {
        // Draw the restart button
        g2.drawImage(restartImage, restartButtonBounds.x, restartButtonBounds.y, restartButtonBounds.width, restartButtonBounds.height, null);

        g2.drawImage(gameOverImage, restartButtonBounds.x-31, restartButtonBounds.y-200, restartButtonBounds.width+100, restartButtonBounds.height+100, null);

        // Draw the high score with a black outline
        Font font = new Font("Arial", Font.BOLD, 30);
        g2.setFont(font);
        String highScoreText = "High Score: " + highScore;
        int textWidth = g2.getFontMetrics().stringWidth(highScoreText);
        int textX = (gamePanel.screenWidth - textWidth) / 2;
        int textY = restartButtonBounds.y + restartButtonBounds.height + 50; // Adjust the vertical position

        g2.setColor(Color.BLACK);
        g2.drawString(highScoreText, textX - 4, textY); // Draw the outline to the left
        g2.drawString(highScoreText, textX + 4, textY); // Draw the outline to the right
        g2.drawString(highScoreText, textX, textY - 4); // Draw the outline above
        g2.drawString(highScoreText, textX, textY + 4); // Draw the outline below

        // Draw the white text on top
        g2.setColor(Color.WHITE);
        g2.drawString(highScoreText, textX, textY);
    }

}
