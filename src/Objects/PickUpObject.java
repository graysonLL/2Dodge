package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PickUpObject extends Items {
    private int x; // x-coordinate of the box
    private int y; // y-coordinate of the box
    private int boxSize = 30; // Adjust the size of the box as needed
    private int screenWidth;
    private int screenHeight;

    public PickUpObject(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        respawnBox();
    }

    private void respawnBox() {
        Random random = new Random();
        x = random.nextInt(screenWidth - boxSize); // Generate a random x-coordinate within screen width
        y = random.nextInt(screenHeight - boxSize); // Generate a random y-coordinate within screen height
    }


    public void draw(Graphics g) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Items/A_grade.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(image, x, y, 32,32,null);

    }

    public int getCurrentX() {
        return x;
    }

    public int getCurrentY() {
        return y;
    }
}
