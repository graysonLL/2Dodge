package Game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JPanel;
import Background.TileManager;
import Character.Player;
import Objects.FlyingObject;
import Objects.F_Grade;
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

    private Canvas canvas;  // New Canvas member variable

    public GamePanel() {
        this.setLayout(null); // Set layout to null
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocus();

        // Create a Canvas and add it to the JPanel
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(screenWidth, screenHeight));
        canvas.setFocusable(true);
        this.add(canvas);


        flyingObject2 = new F_Grade();
        flyingObject = new F_Grade();
    }

    public void startGameThread() {
        canvas.createBufferStrategy(2);
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
            BufferStrategy bufferStrategy = canvas.getBufferStrategy();
            Graphics g = bufferStrategy.getDrawGraphics();
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
//            checkCollisions();

            if(timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
            g.dispose();
            bufferStrategy.show();
        }

    }

    public void update() {
        player.update();
    }

    private void checkCollisions() {
        if (player.getCurrentY() - flyingObject.getCurrentY() <= 20 &&
                player.getCurrentY() - flyingObject.getCurrentY() >= -20 &&
                player.getCurrentX() - flyingObject.getCurrentX() <= 20 &&
                player.getCurrentX() - flyingObject.getCurrentX() >= -20) {
            gameOver = true;
        }

        if (player.getCurrentY() - flyingObject2.getCurrentY() <= 20 &&
                player.getCurrentY() - flyingObject2.getCurrentY() >= -20 &&
                player.getCurrentX() - flyingObject2.getCurrentX() <= 20 &&
                player.getCurrentX() - flyingObject2.getCurrentX() >= -20) {
            gameOver = true;
        }

        if (player.getCurrentY() - randomBox.getCurrentY() <= 30 &&
                player.getCurrentY() - randomBox.getCurrentY() >= -30 &&
                player.getCurrentX() - randomBox.getCurrentX() <= 30 &&
                player.getCurrentX() - randomBox.getCurrentX() >= -30) {
            score += 1;
            System.out.println(score);
            randomBox.respawnBox();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, screenWidth, screenHeight);
        tileM.draw(g2);
        player.draw(g2);
        flyingObject.draw(g2);
        flyingObject2.draw(g2);
        randomBox.draw(g2);
        g2.dispose();
    }
}
