package Objects;

import Background.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.io.IOException;

public abstract class FlyingObject extends Items {
    private static final int tileSize = 16;
    private static final int maxScreenCol = 48;
    private static final int maxScreenRow = 36;
    private static final int screenWidth = tileSize * maxScreenCol;
    private static final int screenHeight = tileSize * maxScreenRow;

    public int x, y;
    private int speed; // Speed in the X direction
    private int directionX, directionY; // Direction in the X axis

    public FlyingObject() {
        spawnRandom();
        speed = 6;
    }

    private void spawnRandom() {
        Random random = new Random();
        int spawnType = random.nextInt(3); // 0, 1, or 2

        switch (spawnType) {
            case 0: // Vertical spawn
                x = random.nextInt(700 - 68) + 68; // Random x between 68 and 700
                y = random.nextBoolean() ? 0 : screenHeight - tileSize;
                directionX = random.nextBoolean() ? 1 : -1;
                directionY = 0;
                break;



            case 1: // Horizontal spawn
                x = random.nextBoolean() ? 0 : screenWidth - tileSize;
                y = random.nextBoolean() ? random.nextInt(screenHeight - 200) : screenHeight - 100;
                directionX = 0;
                directionY = random.nextBoolean() ? 1 : -1;
                break;

            case 2: // Diagonal spawn
                x = random.nextBoolean() ? random.nextInt(screenWidth) : screenWidth;
                y = random.nextBoolean() ? random.nextInt(screenHeight) : screenHeight;
                directionX = random.nextBoolean() ? 1 : -1;
                directionY = random.nextBoolean() ? 1 : -1;
                break;
        }
    }

    public void update() {
        x += speed * directionX;
        y += speed * directionY;

        if (x < 0 || x > screenWidth - tileSize || y < 0 || y > screenHeight - tileSize) {
            spawnRandom();
        }
    }

    public abstract void draw(Graphics2D g);

    public int getCurrentX() {
        return x;
    }

    public int getCurrentY() {
        return y;
    }
}

