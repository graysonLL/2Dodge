package Objects;

import java.awt.*;
import java.util.Random;

public abstract class FlyingObject extends Rectangle {
    private static final int tileSize = 16;
    private static final int maxScreenCol = 48;
    private static final int maxScreenRow = 36;
    private static final int screenWidth = tileSize * maxScreenCol;
    private static final int screenHeight = tileSize * maxScreenRow;
    private boolean hasCollided;


    public int x, y;
    private int speed; // Speed in the X direction
    private int directionX, directionY; // Direction in the X axis

    public FlyingObject() {

        Random random = new Random();
        if (random.nextBoolean()) {
            x = 0;
            directionX = 1;
        } else {
            x = screenWidth - tileSize;
            directionX = -1;
        }
        if (random.nextBoolean()) {
            y = 0;
            directionY = 1;
        } else {
            y = screenHeight - tileSize;
            directionY = -1;
        }
        speed = 5;
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

    public void spawnRandom() {
        Random random = new Random();
        if (random.nextBoolean()) {
            x = 0;
            directionX = 1;
        } else {
            x = screenWidth - tileSize;
            directionX = -1;
        }
        if (random.nextBoolean()) {
            y = 0;
            directionY = 1;
        } else {
            y = screenHeight - tileSize;
            directionY = -1;
        }
    }

    public void resetCollisionFlag() {
        hasCollided = false;
    }

    public boolean hasCollided() {
        return hasCollided;
    }

    public void setCollided() {
        hasCollided = true;
    }
}
