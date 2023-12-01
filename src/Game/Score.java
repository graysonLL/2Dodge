package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Score {
    public int totalPoints = 0;
    private BufferedImage[] numberArray;
    private final int totalImages = 10;

    public Score() {
        loadNumberImages();
    }

    public void addPoint() {
        totalPoints += 1;
    }

    private void loadNumberImages() {
        numberArray = new BufferedImage[totalImages];

        for (int i = 0; i < totalImages; i++) {
            try {
                numberArray[i] = ImageIO.read(getClass().getResourceAsStream("/Numbers/Number" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception (e.g., load a default image)
            }
        }
    }

    public void displayScore(Graphics2D g2) {
        int score = totalPoints;
        int digitWidth = 32; // Adjust the width of each digit
        int digitHeight = 32; // Adjust the height of each digit
        int spacing = 5; // Adjust the spacing between digits
        int rightMargin = 5; // Adjust the margin from the right
        int x = g2.getClipBounds().width - digitWidth - rightMargin;

        do {
            int digit = score % 10;
            BufferedImage digitImage = numberArray[digit];
            int digitX = x - digitWidth;
            int digitY = 10; // Fixed vertical position at the top
            g2.drawImage(digitImage, digitX, digitY, digitWidth, digitHeight, null);

            x -= (digitWidth + spacing);
            score /= 10;
        } while (score > 0);
    }
}
