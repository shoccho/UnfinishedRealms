package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean up, down, left, right;
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        switch (code){
            case KeyEvent.VK_W -> up = true;
            case KeyEvent.VK_S -> down = true;
            case KeyEvent.VK_A -> left = true;
            case KeyEvent.VK_D -> right = true;
            default -> throw new IllegalStateException("Unexpected value: " + code);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W -> {
                up = false;
            }
            case KeyEvent.VK_S -> {
                down = false;
            }
            case KeyEvent.VK_A -> {
                left = false;
            }
            case KeyEvent.VK_D -> {
                right = false;
            }
        }
    }
}
