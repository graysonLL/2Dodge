package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Bread extends Food {
    public Bread(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth -100;
        this.screenHeight = screenHeight -100;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Items/Bread.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        respawnBox();
    }


}
