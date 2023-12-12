package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Score {
    public int totalPoints = 0;
    private BufferedImage[] numberArray;
    private final int totalImages = 10;
    BufferedImage[] scoreImages;
    public int highScore = 0;

    public Score() {
        loadNumberImages();
    }

    public void addPoint() {
        totalPoints += 1;
    }

    private void loadNumberImages() {
        numberArray = new BufferedImage[totalImages];
        scoreImages = new BufferedImage[5];

        for (int i = 0; i < totalImages; i++) {
            try {
                numberArray[i] = ImageIO.read(getClass().getResourceAsStream("/NumbersAndLetters/Number" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception (e.g., load a default image)
            }
        }
        String word = "score";
        try {
            scoreImages[0] = ImageIO.read(getClass().getResourceAsStream("/NumbersAndLetters/LetterS.png"));
            scoreImages[1] = ImageIO.read(getClass().getResourceAsStream("/NumbersAndLetters/LetterC.png"));
            scoreImages[2] = ImageIO.read(getClass().getResourceAsStream("/NumbersAndLetters/LetterO.png"));
            scoreImages[3] = ImageIO.read(getClass().getResourceAsStream("/NumbersAndLetters/LetterR.png"));
            scoreImages[4] = ImageIO.read(getClass().getResourceAsStream("/NumbersAndLetters/LetterE.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void displayScore(Graphics2D g2) {
        int score = totalPoints;
        int digitWidth = 45; // Adjust the width of each digit
        int digitHeight = 45; // Adjust the height of each digit
        int spacing = 5; // Adjust the spacing between digits
        int rightMargin = 5; // Adjust the margin from the right
        int x = g2.getClipBounds().width - digitWidth - rightMargin;
        g2.drawImage(scoreImages[0], x-digitWidth-128, 10, digitWidth, digitHeight, null);
        g2.drawImage(scoreImages[1], x-digitWidth-96, 10, digitWidth, digitHeight, null);
        g2.drawImage(scoreImages[2], x-digitWidth-64, 10, digitWidth, digitHeight, null);
        g2.drawImage(scoreImages[3], x-digitWidth-32, 10, digitWidth, digitHeight, null);
        g2.drawImage(scoreImages[4], x-digitWidth, 10, digitWidth, digitHeight, null);
        do {
            int digit = score % 10;
            BufferedImage digitImage = numberArray[digit];
            int digitX = x - digitWidth;
            int digitY = 50; // Fixed vertical position at the top
            g2.drawImage(digitImage, digitX, digitY, digitWidth, digitHeight, null);

            x -= (digitWidth + spacing);
            score /= 10;
        } while (score > 0);
    }

    public void reset() {
        totalPoints = 0;
    }

}
