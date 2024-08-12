package main;

import entity.Player;
import item.Item;
import item.ItemManager;
import tile.TileManager;

import java.awt.*;

import javax.swing.JPanel;

import static java.lang.Math.abs;

public class Game extends JPanel implements Runnable{

    //settings
    final int originalTileSize = 16; // tile res 16x16 px tiles
    final int scale = 3; //scaling for pixels to res

    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;

    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;
    int FPS = 60;

    //world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 30;

    ItemManager itemManager;
    ItemAdder itemAdder;
    public final int maxWorldHeight = tileSize * maxWorldRow;
    public final int maxWorldWidth = tileSize * maxWorldCol;
//TODO OOPs
    public CollisionChecker collisionChecker;
    Thread gameThread;
    KeyHandler keyHandler;

    TileManager tileManager;
    public Player player;

    public Game() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.keyHandler = new KeyHandler();
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true);
        this.tileManager = new TileManager(this);
        this.player = new Player(this, this.keyHandler);
        this.collisionChecker = new CollisionChecker(this);
        this.itemManager = new ItemManager(this);
        this.itemAdder = new ItemAdder(this);
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

    public boolean isInPlayerView(int worldX, int worldY) {
        int viewWidth = this.screenWidth/2;
        int viewHeight = this.screenHeight/2;
        boolean inH = viewWidth > (abs(worldX - this.player.worldX) - tileSize);
        boolean inV =  viewHeight > (abs(worldY - this.player.worldY) - tileSize);
        return inH && inV;
    }

    public boolean isCollisionOn(int worldX, int worldY){
        int x = worldX/this.tileSize;
        int y = worldY/this.tileSize;
        if(x < 0 || x >this.maxWorldCol-1 || y < 0 || y > maxWorldRow -1 ) return false;
        return this.tileManager.getTile(y, x).collision;
    }
    public Item getItem(int worldX, int worldY){
        return this.itemManager.getItem(worldX, worldY);
    }

    public void update(){
        this.player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.tileManager.draw(g2);
        this.itemManager.draw(g2);
        this.player.draw(g2);
        g2.dispose();
    }
}
