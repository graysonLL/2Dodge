package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class PickUpObject extends Rectangle {
    public BufferedImage image;
    public int x; // x-coordinate of the box
    public int y; // y-coordinate of the box
    public int boxSize = 30; // Adjust the size of the box as needed
    public int screenWidth;
    public int screenHeight;

    public void respawnBox() {
        Random random = new Random();
        x = random.nextInt(screenWidth - boxSize); // Generate a random x-coordinate within screen width
        y = random.nextInt(screenHeight - 128) + 128; // Generate a random y-coordinate after 96 pixels
    }

    public PickUpObject() {
    }

    public int getCurrentX() {
        return x;
    }

    public int getCurrentY() {
        return y;
    }

    public boolean checkCollision(int currentY,int currentX) {
        if (currentY - this.getCurrentY() <= 35 &&
                currentY - this.getCurrentY() >= -35 &&
                currentX - this.getCurrentX() <= 35 &&
                currentX - this.getCurrentX() >= -35) {
            return true;
        }
        return false;
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, 40, 40, null);
    }
}
