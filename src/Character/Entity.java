package Character;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage up_idle1, up_idle2,up_move1, up_move2, upright_move1, upright_move2, upright_idle1, upright_idle2, down_idle1, down_move1, down_move2, right_idle1, right_move1, right_move2, left_idle1, left_move1, left_move2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
