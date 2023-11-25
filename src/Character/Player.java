package Character;

import Game.GamePanel;
import Game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOError;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

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
}