package Game;

import java.awt.*;

import javax.swing.JPanel;

import Background.TileManager;
import Character.Player;
import Objects.FlyingObject;
import Objects.Grade_5;
import Objects.PickUpObject;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    private int score = 0;

    public boolean gameOver = false;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this,keyH);
    TileManager tileM = new TileManager(this);

    PickUpObject randomBox = new PickUpObject(screenWidth, screenHeight);

    //FPS
    int FPS = 60;

    private FlyingObject flyingObject;
    private FlyingObject flyingObject2;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        flyingObject2 = new Grade_5();
        flyingObject = new Grade_5();
    }

    public void startGameThread() {
        gameThread =  new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null && gameOver == false) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
                flyingObject.update();
                flyingObject2.update();
            }
            if(player.getCurrentY()-flyingObject.getCurrentY() <= 20 && player.getCurrentY()-flyingObject.getCurrentY() >= -20 && player.getCurrentX()-flyingObject.getCurrentX() <= 20 && player.getCurrentX()-flyingObject.getCurrentX() >= -20) {
                System.out.println("you  Lose 1");
                gameOver = true;
            }
            if(player.getCurrentY()-flyingObject2.getCurrentY() <= 20 && player.getCurrentY()-flyingObject2.getCurrentY() >= -20 && player.getCurrentX()-flyingObject2.getCurrentX() <= 20 && player.getCurrentX()-flyingObject2.getCurrentX() >= -20) {
                System.out.println("you Lose 2");
            }
            if(player.getCurrentY()-randomBox.getCurrentY() <= 30 && player.getCurrentY()-randomBox.getCurrentY() >= -30 && player.getCurrentX()-randomBox.getCurrentX() <= 30 && player.getCurrentX()-randomBox.getCurrentX() >= -30) {
                score+=1;
                System.out.println(score);
                randomBox = new PickUpObject(screenWidth, screenHeight);
            }

                if(timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);
        flyingObject.draw(g2);
        flyingObject2.draw(g2);
        randomBox.draw(g2);
        g2.dispose();
    }
}
