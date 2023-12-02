package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class GradeA extends PickUpObject {


    public GradeA(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth -100;
        this.screenHeight = screenHeight -100;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Items/A_grade.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        respawnBox();
    }

}
