package main;

import java.awt.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    //settings
    final int originalTileSize = 16; // tile res 16x16 px tiles
    final int scale = 3; //scaling for pixels to res

    final int tileSize = originalTileSize * scale;

    final int maxScreenCol = 16;
    final int maxScreenRow = 16;

    int screenWidth = tileSize * maxScreenCol;
    int screenHeight = tileSize * maxScreenRow;
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.keyHandler = new KeyHandler();
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >=1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        if(keyHandler.up){
            playerY -= playerSpeed;
        }
        if(keyHandler.down){
            playerY += playerSpeed;
        }
        if(keyHandler.left){
            playerX -= playerSpeed;
        }
        if(keyHandler.right){
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
