package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Pizza extends Food {
    public Pizza(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth -100;
        this.screenHeight = screenHeight -100;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Items/Pizza.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        respawnBox();
    }


}
