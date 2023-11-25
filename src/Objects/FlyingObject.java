package Objects;

import Background.Tile;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
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
    private Random random;


    private int characterX;
    private int characterY;
    private int characterWidth;
    private int characterHeight;


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


        // Collision detection with the character
        if (x < characterX + characterWidth &&
                x + tileSize > characterX &&
                y < characterY + characterHeight &&
                y + tileSize > characterY) {
            // Collision occurred, handle game over or any other actions
            System.out.println("Game Over - Collision Detected!");
            // You might trigger a game over action here
            // For example: gameEnded = true;
            System.out.print(x + characterX);
        }

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
    public abstract void draw(Graphics g);

    public int getCurrentX() {
        return x;
    }

    public int getCurrentY() {
        return y;
    }
}
