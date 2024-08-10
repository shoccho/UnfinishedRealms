package main;

import entity.Player;

import java.awt.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    //settings
    final int originalTileSize = 16; // tile res 16x16 px tiles
    final int scale = 3; //scaling for pixels to res

    public final int tileSize = originalTileSize * scale;

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
    Player player;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.keyHandler = new KeyHandler();
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true);
        this.player = new Player(this, this.keyHandler);
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
        this.player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.player.draw(g2);
        g2.dispose();
    }
}
