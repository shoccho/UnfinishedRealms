package tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    public BufferedImage image;
    public boolean collision = false;
    public int x, y;
    public int size;

    public Tile(String type, int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        try {
            this.image = ImageIO.read(getClass().getResource("/tiles/"+ type+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D graphics2D, int screenX, int screenY){
        graphics2D.drawImage(this.image, screenX , screenY , size, size, null);
    }

}
