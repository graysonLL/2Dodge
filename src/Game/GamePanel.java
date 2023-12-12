package Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JPanel;
import Background.TileManager;
import Character.Player;
import Objects.*;


public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    private int foodNum = 1;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    private Score score = new Score();

    private int fGradeCount = 0;
    private boolean addedFGrade = false;
    private F_Grade[] fGrades;
    private RestartGame restartGameWindow;

    public boolean gameOver = false;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this,keyH);
    TileManager tileM = new TileManager(this);

    GradeA gradeA = new GradeA(screenWidth, screenHeight);

    //FPS
    int FPS = 60;

    private FlyingObject flyingObject;
    private FlyingObject flyingObject2;

    private Bread bread = new Bread(screenWidth,screenHeight);
    private Pizza pizza = new Pizza(screenWidth,screenHeight);
    private Taco taco = new Taco(screenWidth,screenHeight);
    private Sleep sleep = new Sleep(screenWidth,screenHeight);

    private Canvas canvas;  // New Canvas member variable

    public GamePanel() {
        this.setLayout(null); // Set layout to null
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocus();
        restartGameWindow = new RestartGame(this);

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
            if (bufferStrategy == null) {
                canvas.createBufferStrategy(2);  // Create a new buffer strategy if it doesn't exist
                System.out.println("BufferStrategy is null. Created a new one.");
                return;
            }

            Graphics g = null;
            try {
                g = bufferStrategy.getDrawGraphics();
                // Rest of the code
            } catch (NullPointerException e) {
                System.out.println("NullPointerException while getting draw graphics.");
                e.printStackTrace();
            } finally {
                if (g != null) {
                    g.dispose();  // Always dispose of graphics to release system resources
                }
            }
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
            checkCollisions();

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
        if (!flyingObject.hasCollided() && player.getCurrentY() - flyingObject.getCurrentY() <= 25 &&
                player.getCurrentY() - flyingObject.getCurrentY() >= -25 &&
                player.getCurrentX() - flyingObject.getCurrentX() <= 25 &&
                player.getCurrentX() - flyingObject.getCurrentX() >= -25) {
            player.removeHitPoint();
            flyingObject.setCollided();
            flyingObject.spawnRandom();
            flyingObject.resetCollisionFlag();
        }

        else if (!flyingObject2.hasCollided() && player.getCurrentY() - flyingObject2.getCurrentY() <= 25 &&
                player.getCurrentY() - flyingObject2.getCurrentY() >= -25 &&
                player.getCurrentX() - flyingObject2.getCurrentX() <= 25 &&
                player.getCurrentX() - flyingObject2.getCurrentX() >= -25) {
            player.removeHitPoint();
            flyingObject2.setCollided();
            flyingObject2.spawnRandom();
            flyingObject2.resetCollisionFlag();
        }

        if (gradeA.checkCollision(player.getCurrentY(),player.getCurrentX())) {
            score.addPoint();
            player.sleepMeter--;
            if(player.sleepMeter==0) {
                gameOver = true;
            }
            System.out.println(player.sleepMeter);
            System.out.println(score.totalPoints);
            gradeA.respawnBox();
            if(score.totalPoints % 5 == 0 && flyingObject.speed <= 12) {
                System.out.println(flyingObject.speed+" "+flyingObject2.speed);
                flyingObject.speed += 1;
                flyingObject2.speed += 1;
            }
        }

        if(foodNum == 1) {
            if (taco.checkCollision(player.getCurrentY(),player.getCurrentX())) {
                taco.respawnBox();
                if(player.foodEaten < 3) {
                    player.foodEaten++;
                }
                foodNum = 2;
            }
        }

        if(foodNum == 2) {
            if (pizza.checkCollision(player.getCurrentY(),player.getCurrentX())) {
                pizza.respawnBox();
                if(player.foodEaten < 3) {
                    player.foodEaten++;
                }
                foodNum = 3;
            }
        }
        if(foodNum == 3) {
            if (bread.checkCollision(player.getCurrentY(),player.getCurrentX())) {
                bread.respawnBox();
                if(player.foodEaten < 3) {
                    player.foodEaten++;
                }
                foodNum = 1;
            }
        }

        if (sleep.checkCollision(player.getCurrentY(),player.getCurrentX())) {
            sleep.respawnBox();
            if(player.sleepMeter < 5) {
                player.sleepMeter++;
            }
            System.out.println(player.sleepMeter);
        }

        if(player.hitPoints==0) {
            gameOver = true;
        }
        if(player.foodEaten == 3 && player.hitPoints < 3) {
            player.hitPoints++;
            player.foodEaten = 0;
        }
    }

    public void resetGameState() {
        // Reset game state or perform any necessary cleanup

        // Reset player state
        player.reset();
        // Reset score
        score.reset();

        flyingObject.speed = 3;
        flyingObject2.speed = 3;

        tileM.resetMap();
        // Reset other game objects if needed
        // ...

        // Ensure that game-over flag is set to false
        gameOver = false;

        startGameThread();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, screenWidth, screenHeight);
        tileM.draw(g2);
        score.displayScore(g2);
        player.displayLife(g2);
        player.displaySleep(g2);
        player.draw(g2);
        flyingObject.draw(g2);
        flyingObject2.draw(g2);
        gradeA.draw(g2);

        if(foodNum == 1) {
            taco.draw(g2);
        }
        if(foodNum == 2) {
            pizza.draw(g2);
        }
        if(foodNum == 3) {
            bread.draw(g2);
        }

        sleep.draw(g2);
        player.displayEaten(g2);

        if (gameOver) {
            if(score.totalPoints > score.highScore) {
                score.highScore = score.totalPoints;
            }
            restartGameWindow.displayRestartWindow(g2,score.highScore);
        }

        g2.dispose();
    }
}