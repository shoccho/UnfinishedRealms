package item;

import entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Item {

    private BufferedImage image;
    int size;
    int x;
    int y;
    public String name;
    boolean removed = false; //todo: pls make it better

    public Item(int tileX, int tileY, int size, String name){
        this.x = tileX;
        this.y = tileY;
        this.size = size;
        this.name = name;
        try {
            this.image = ImageIO.read(getClass().getResource("/items/"+ name +".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D graphics2D, int screenX, int screenY){
        if(this.removed) return;
        graphics2D.drawImage(this.image, screenX , screenY , this.size, this.size, null);
    }

}
