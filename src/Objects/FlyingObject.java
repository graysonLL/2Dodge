package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class FlyingObject {
    private static final int tileSize = 16;
    private static final int maxScreenCol = 48;
    private static final int maxScreenRow = 36;
    private static final int screenWidth = tileSize * maxScreenCol;
    private static final int screenHeight = tileSize * maxScreenRow;

    private int x, y;
    private int speed; // Speed in the X direction
    private int directionX, directionY; // Direction in the X axis
    private Random random;

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
        speed = 6;
    }

    public void update() {
        x += speed * directionX;
        y += speed * directionY;

        if (x < 0 || x > screenWidth - tileSize || y < 0 || y > screenHeight - tileSize) {
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
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, tileSize, tileSize);
    }
}
