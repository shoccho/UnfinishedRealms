package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        this.screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);
        reset();
        loadImages();
    }

    public void reset(){
        this.worldX = gamePanel.tileSize * 0;
        this.worldY = gamePanel.tileSize * 0;
        this.speed = 5;
        this.direction = "down";
        this.spriteNumber = 1;
        this.frameCounter = 0;
    }

    public void update(){
        if (!(keyHandler.up || keyHandler.down || keyHandler.left || keyHandler.right )){
            return;
        }
        if(keyHandler.up){
            direction = "up";
            this.worldY -= this.speed;
        }
        if(keyHandler.down){
            direction = "down";
            this.worldY += this.speed;
        }
        if(keyHandler.left){
            direction = "left";
            this.worldX -= this.speed;
        }
        if(keyHandler.right){
            direction = "right";
            this.worldX += this.speed;
        }
        this.frameCounter++;
        if(this.frameCounter > 10){
            this.frameCounter = 0;
            this.spriteNumber = this.spriteNumber % 2 + 1;
        }
    }
    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(getImage(), this.screenX, this.screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void loadImages(){
        try {
            this.up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            this.up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            this.down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            this.down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            this.left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            this.left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
            this.right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            this.right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(){
        Class<?> clazz = this.getClass();

        try {
            Field f = clazz.getSuperclass().getDeclaredField(this.direction+ this.spriteNumber);
            return (BufferedImage) f.get(this);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
