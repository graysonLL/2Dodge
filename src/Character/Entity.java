package Character;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage up_idle1, up_idle2,up_move1, up_move2, upright_move1, upright_move2, upright_idle1, upright_idle2, upleft_move1, upleft_move2, upleft_idle1, upleft_idle2, down_idle1, down_idle2, down_move1, down_move2, right_idle1, right_idle2, right_move1, right_move2, left_idle1, left_move1, left_move2, left_idle2, leftdown_move1, leftdown_move2, leftdown_idle1, leftdown_idle2, rightdown_move1, rightdown_move2, rightdown_idle1, rightdown_idle2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
