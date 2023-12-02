package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Sleep extends PickUpObject {
    public Sleep(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth -100;
        this.screenHeight = screenHeight -100;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Items/Sleeping.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        respawnBox();
    }

    @Override
    public boolean checkCollision(int currentY,int currentX) {
        if (currentY - this.getCurrentY() <= 30 &&
                currentY - this.getCurrentY() >= -30 &&
                currentX - this.getCurrentX() <= 50 &&
                currentX - this.getCurrentX() >= -50) {
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, 60, 40, null);
    }
}
