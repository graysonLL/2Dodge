package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Grade_5 extends FlyingObject {
    public void draw(Graphics g) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Items/F_grade.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(image, x, y, 32,32,null);

    }
}
