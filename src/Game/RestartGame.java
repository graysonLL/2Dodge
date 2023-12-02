package Game;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    public void displayRestartWindow(Graphics2D g2) {
        // Draw the restart button
        g2.drawImage(restartImage, restartButtonBounds.x, restartButtonBounds.y, restartButtonBounds.width, restartButtonBounds.height, null);

        g2.drawImage(gameOverImage, restartButtonBounds.x-31, restartButtonBounds.y-200, restartButtonBounds.width+100, restartButtonBounds.height+100, null);
    }
}
