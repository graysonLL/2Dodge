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

        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {
        x = 300;
        y = 300;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/up_idle1.png"));
            up_idle2 = ImageIO.read(getClass().getResourceAsStream("/Player/up_idle2.png"));
            up_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/up_move1.png"));
            up_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/up_move2.png"));

            upright_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/upright_move1.png"));
            upright_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/upright_move1.png"));
            upright_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/upright_move2.png"));

            down_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/down_idle1.png"));
            down_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/down_move1.png"));
            down_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/down_move2.png"));

            left_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/left_idle1.png"));
            left_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/left_move1.png"));
            left_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/left_move2.png"));

            right_idle1 = ImageIO.read(getClass().getResourceAsStream("/Player/right_idle1.png"));
            right_move1 = ImageIO.read(getClass().getResourceAsStream("/Player/right_move1.png"));
            right_move2 = ImageIO.read(getClass().getResourceAsStream("/Player/right_move2.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if(keyH.upPressed == true && keyH.rightPressed == true) {
                direction = "up_right";
                y -= speed;
            }
            else if(keyH.upPressed == true) {
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
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {

            case "up_right":
                System.out.print(direction);
                if(spriteNum == 1) {
                    image = upright_move1;
                }
                if(spriteNum == 2) {
                    image = upright_move2;
                }
                break;

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
                    image = down_idle1;
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
                    image = left_idle1;
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
                    image = right_idle1;
                }
                break;
        }
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }
}
