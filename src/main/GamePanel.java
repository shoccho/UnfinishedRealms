package main;

import entity.Player;
import tile.TileManager;

import java.awt.*;

import javax.swing.JPanel;

import static java.lang.Math.abs;

public class GamePanel extends JPanel implements Runnable{

    //settings
    final int originalTileSize = 16; // tile res 16x16 px tiles
    final int scale = 3; //scaling for pixels to res

    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 20;
    public final int maxScreenRow = 20;

    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;
    int FPS = 60;

    //world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    public final int maxWorldHeight = tileSize * maxWorldRow;
    public final int maxWorldWidth = tileSize * maxWorldCol;


    Thread gameThread;
    KeyHandler keyHandler;

    TileManager tileManager;
    public Player player;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.keyHandler = new KeyHandler();
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true);
        this.tileManager = new TileManager(this);
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
    public int[] translateToScreenView(int worldX, int worldY) {
        return new int[]{
                worldX - this.player.worldX + this.player.screenX,
             worldY - this.player.worldY + this.player.screenY,
        };
    }

    public boolean inPlayerView(int worldX, int worldY) {
        int viewWidth = this.screenWidth/2;
        int viewHeight = this.screenHeight/2;
        boolean inH = viewWidth > (abs(worldX - this.player.worldX) - tileSize);
        boolean inV =  viewHeight > (abs(worldY - this.player.worldY) - tileSize);
        return inH && inV;
    }


    public void update(){
        this.player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        this.player.draw(g2);
        g2.dispose();
    }
}
