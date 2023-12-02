package Character;

import Game.GamePanel;
import Game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity {
    private BufferedImage heartImage;
    private BufferedImage goldHeartImage;
    private BufferedImage sleepImage;
    private BufferedImage slash;
    private BufferedImage hungerImage;
    private BufferedImage[] numberArray;
    private final int totalImages = 6;
    GamePanel gp;
    KeyHandler keyH;

    public int hitPoints = 3;
    public int sleepMeter = 5;
    public int foodEaten = 0;

    public Player(GamePanel gp,KeyHandler heyH) {
        this.gp = gp;
        this.keyH = heyH;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;


        setDefaultValues();
        getPlayerImage();
        loadNumberImages();
    }

    private void loadNumberImages() {
        numberArray = new BufferedImage[totalImages];

        for (int i = 0; i < totalImages; i++) {
            try {
                numberArray[i] = ImageIO.read(getClass().getResourceAsStream("/NumbersAndLetters/Number" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            slash = ImageIO.read(getClass().getResourceAsStream("/NumbersAndLetters/Slash.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            hungerImage = ImageIO.read(getClass().getResourceAsStream("/Items/Hunger.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            goldHeartImage = ImageIO.read(getClass().getResourceAsStream("/Items/GoldHeart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setDefaultValues() {
        x = 340;
        y = 250;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/up_move1.png"));
            up_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/up_move2.png"));
            up_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/up_idle1.png"));
            up_idle2 = ImageIO.read(getClass().getResourceAsStream("/Player/up_idle2.png"));

            down_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/down_move1.png"));
            down_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/down_move2.png"));
            down_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/down_idle1.png"));
            down_idle2 = ImageIO.read(getClass().getResourceAsStream("/Player/down_idle2.png"));

            left_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/left_move1.png"));
            left_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/left_move2.png"));
            left_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/left_idle1.png"));
            left_idle2 = ImageIO.read(getClass().getResourceAsStream("/Player/left_idle2.png"));

            right_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/right_move1.png"));
            right_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/right_move2.png"));
            right_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/right_idle1.png"));
            right_idle2 = ImageIO.read(getClass().getResourceAsStream("/Player/right_idle2.png"));

            upright_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/upright_move1.png"));
            upright_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/upright_move2.png"));
            upright_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/upright_idle1.png"));
            upright_idle2 = ImageIO.read(getClass().getResourceAsStream("/Player/upright_idle2.png"));

            upleft_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/upleft_move1.png"));
            upleft_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/upleft_move2.png"));
            upleft_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/upleft_idle1.png"));
            upleft_idle2 = ImageIO.read(getClass().getResourceAsStream("/Player/upleft_idle2.png"));

            leftdown_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/leftdown_move1.png"));
            leftdown_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/leftdown_move2.png"));
            leftdown_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/leftdown_idle1.png"));
            leftdown_idle2 = ImageIO.read(getClass().getResourceAsStream("/Player/leftdown_idle2.png"));

            rightdown_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/rightdown_move1.png"));
            rightdown_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/rightdown_move2.png"));
            rightdown_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/rightdown_idle1.png"));
            rightdown_idle2 = ImageIO.read(getClass().getResourceAsStream("/Player/rightdown_idle2.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.rightPressed == true && keyH.upPressed == true) {
            direction = "upright";
            y -= speed;
            x += speed;
        }
        else if(keyH.rightPressed == true && keyH.downPressed == true) {
            direction = "downright";
            y += speed;
            x += speed;
        }
        else if(keyH.leftPressed == true && keyH.upPressed == true) {
            direction = "upleft";
            y -= speed;
            x -= speed;
        }
        else if(keyH.leftPressed == true && keyH.downPressed == true) {
            direction = "downleft";
            y += speed;
            x -= speed;
        }
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if(keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            }
            else if(keyH.downPressed == true) {
                direction = "down";
                y += speed;
            }
            if(keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            }
            if(keyH.rightPressed == true) {
                direction = "right";
                x += speed;
            }


        }
        collisionOn = false;

        spriteCounter++;
        if(spriteCounter > 5) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        outOfBoundsStopper(0, 128, gp.screenWidth - solidArea.width, gp.screenHeight - solidArea.height);
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(keyH.upPressed == true) {
                    if(spriteNum == 1) {
                        image = up_move1;
                    }
                    if(spriteNum == 2) {
                        image = up_move2;
                    }
                }
                else {
                    if(spriteNum == 1) {
                        image = up_idle1;
                    }
                    if(spriteNum == 2) {
                        image = up_idle2;
                    }
                }
                break;



            case "down":
                if(keyH.downPressed == true) {
                    if(spriteNum == 1) {
                        image = down_move1;
                    }
                    if(spriteNum == 2) {
                        image = down_move2;
                    }
                }
                else {
                    if(spriteNum == 1) {
                        image = down_idle1;
                    }
                    if(spriteNum == 2) {
                        image = down_idle2;
                    }
                }
                break;



            case "left":
                if(keyH.leftPressed == true) {
                    if(spriteNum == 1) {
                        image = left_move1;
                    }
                    if(spriteNum == 2) {
                        image = left_move2;
                    }
                }
                else {
                    if(spriteNum == 1) {
                        image = left_idle1;
                    }
                    if(spriteNum == 2) {
                        image = left_idle2;
                    }
                }
                break;

            case "right":
                if(keyH.rightPressed == true) {
                    if(spriteNum == 1) {
                        image = right_move1;
                    }
                    if(spriteNum == 2) {
                        image = right_move2;
                    }
                }
                else {
                    if(spriteNum == 1) {
                        image = right_idle1;
                    }
                    if(spriteNum == 2) {
                        image = right_idle2;
                    }
                }
                break;


            case "upright":
                if(keyH.rightPressed == true && keyH.upPressed == true) {
                    if(spriteNum == 1) {
                        image = upright_move1;
                    }
                    if(spriteNum == 2) {
                        image = upright_move2;
                    }
                }
                else {
                    if(spriteNum == 1) {
                        image = upright_idle1;
                    }
                    if(spriteNum == 2) {
                        image = upright_idle2;
                    }
                }
                break;

            case "upleft":
                if(keyH.leftPressed == true && keyH.upPressed == true) {
                    if(spriteNum == 1) {
                        image = upleft_move1;
                    }
                    if(spriteNum == 2) {
                        image = upleft_move2;
                    }
                }
                else {
                    if (spriteNum == 1) {
                        image = upleft_idle1;
                    }
                    if (spriteNum == 2) {
                        image = upleft_idle2;
                    }
                }
                break;

            case "downright":
                if(keyH.rightPressed == true && keyH.downPressed == true) {
                    if(spriteNum == 1) {
                        image = rightdown_move1;
                    }
                    if(spriteNum == 2) {
                        image = rightdown_move2;
                    }
                }
                else {
                    if(spriteNum == 1) {
                        image = rightdown_idle1;
                    }
                    if(spriteNum == 2) {
                        image = rightdown_idle2;
                    }
                }
                break;

            case "downleft":
                if(keyH.leftPressed == true && keyH.downPressed == true) {
                    if(spriteNum == 1) {
                        image = leftdown_move1;
                    }
                    if(spriteNum == 2) {
                        image = leftdown_move2;
                    }
                }
                else {
                    if(spriteNum == 1) {
                        image = leftdown_idle1;
                    }
                    if(spriteNum == 2) {
                        image = leftdown_idle2;
                    }
                }
                break;
        }
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }
    public int getCurrentX() {
        return x;
    }

    public int getCurrentY() {
        return y;
    }

    public void outOfBoundsStopper(int minX, int minY, int maxX, int maxY) {
        // Ensure the player stays within the bounds
        if (getCurrentX() < minX) {
            x = minX;
        } else if (getCurrentX() > maxX) {
            x = maxX;
        }

        if (getCurrentY() < minY) {
            y = minY;
        } else if (getCurrentY() > maxY) {
            y = maxY;
        }
    }

    public void displayLife(Graphics2D g2) {
        int heartSize = 32; // Adjust the size of the hearts as needed
        int spacing = 10; // Adjust the spacing between hearts as needed
        int leftMargin = 10;
        try {
            heartImage = ImageIO.read(getClass().getResourceAsStream("/Items/heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int x;
        for (x = 0; x < hitPoints; x++) {
            int heartX = x * (heartSize + spacing) + leftMargin; // Adjusted to include left margin
            int heartY = 10; // Fixed vertical position at the top
            g2.drawImage(heartImage, heartX, heartY, heartSize, heartSize, null);
        }
    }

    public void displaySleep(Graphics2D g2) {
        int sleepSize = 40; // Adjust the size of the hearts as needed
        int spacing = 10; // Adjust the spacing between hearts as needed
        int leftMargin = 10;

        if (sleepImage == null) {
            try {
                sleepImage = ImageIO.read(getClass().getResourceAsStream("/Items/SleepMeter.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int x = 0; x < sleepMeter; x++) {
            int sleepX = x * (sleepSize + spacing) + leftMargin; // Adjusted to include left margin
            int sleepY = 50; // Fixed vertical position at the top
            g2.drawImage(sleepImage, sleepX, sleepY, sleepSize, 33, null);
        }
    }


    public void displayEaten(Graphics2D g2) {
        int eaten = foodEaten;
        int digitWidth = 40; // Adjust the width of each digit
        int digitHeight = 40; // Adjust the height of each digit
        int spacing = 5; // Adjust the spacing between digits
        int leftMargin = 5; // Adjust the margin from the left
        int x = leftMargin; // Start drawing from the leftmost part of the screen
        int digitY = 90;
        g2.drawImage(hungerImage, 10, digitY, 50, 40, null);
        do {
            int digit = eaten % 10;
            BufferedImage digitImage = numberArray[digit];
            g2.drawImage(digitImage, 64, digitY, digitWidth, digitHeight, null);

            x += (digitWidth + spacing);
            eaten /= 10;
        } while (eaten > 0);
        g2.drawImage(slash, 96, digitY, digitWidth, digitHeight, null);
        g2.drawImage(numberArray[3], 128, digitY, digitWidth, digitHeight, null);

        if(foodEaten == 3) {
            g2.drawImage(goldHeartImage, 160, digitY, 80, digitHeight, null);
        }
    }



    public void removeHitPoint() {
        hitPoints-=1;
    }

    public void reset() {
        x = 340;
        y = 250;
        hitPoints = 3;
        sleepMeter = 5;
        foodEaten = 0;

        keyH.reset();
    }
}