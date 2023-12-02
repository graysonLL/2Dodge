package Objects;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class F_Grade extends FlyingObject {
    private BufferedImage image;

    public F_Grade() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Items/F_grade.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, 40, 40, null);
    }
}
