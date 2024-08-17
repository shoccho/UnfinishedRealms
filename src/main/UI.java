package main;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class UI {
    Game game;
    Font arial_32;
    String message;
    boolean showMessage;
    int messageFrameCount = 0;
    public UI(Game game){
        this.game = game;
        this.arial_32 = new Font("Arial", Font.PLAIN, 32);
    }

    public void setMessage(String message){
        this.message = message;
    }
    public void setShowMessage(boolean showMessage){
        this.showMessage = showMessage;
        this.messageFrameCount= 0;
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setFont(arial_32);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("Key: "+game.player.keys, 50, 50);
        if(this.showMessage){
            int messageWidth = (int) graphics2D.getFontMetrics().getStringBounds(message, graphics2D).getWidth();
            graphics2D.drawString(message, (game.screenWidth -messageWidth)/2, game.screenHeight/2 - 32);
            this.messageFrameCount++;
            if(this.messageFrameCount>90){
                setShowMessage(false);
            }
        }
    }

}
