package Objects;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class PickUpObject extends Items {
    private int x; // x-coordinate of the box
    private int y; // y-coordinate of the box
    private int boxSize = 30; // Adjust the size of the box as needed
    private int screenWidth;
    private int screenHeight;
    private BufferedImage image;

    public void respawnBox() {
        Random random = new Random();
        x = random.nextInt(screenWidth - boxSize); // Generate a random x-coordinate within screen width
        y = random.nextInt(screenHeight - boxSize); // Generate a random y-coordinate within screen height
    }

    public PickUpObject(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth -100;
        this.screenHeight = screenHeight -100;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Items/A_grade.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        respawnBox();
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, 32, 32, null);
    }

    public int getCurrentX() {
        return x;
    }

    public int getCurrentY() {
        return y;
    }
}
