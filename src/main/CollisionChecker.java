package main;

import entity.Entity;
import item.Item;

public class CollisionChecker {
    Game game;

    public CollisionChecker(Game game) {
        this.game = game;
    }

    public boolean checkTile(Entity entity) {
        int worldX = entity.worldX + entity.collisionBox.x;
        int worldY = entity.worldY + entity.collisionBox.y;
        int speed = entity.speed;
        int width = entity.collisionBox.width;
        int height = entity.collisionBox.height;
        boolean blocked = false;

        switch (entity.direction) {
            case "up":
                blocked = checkCollision(worldX, worldY - speed, worldX + width, worldY - speed);
                break;
            case "down":
                blocked = checkCollision(worldX, worldY + height + speed, worldX + width, worldY + height + speed);
                break;
            case "left":
                blocked = checkCollision(worldX - speed, worldY, worldX - speed, worldY + height);
                break;
            case "right":
                blocked = checkCollision(worldX + width + speed, worldY, worldX + width + speed, worldY + height);
                break;
        }
        return blocked;
    }

    private boolean checkCollision(int x1, int y1, int x2, int y2) {
        //todo: pls! learn some shit! wtf is this logic
        boolean collision = (this.game.isCollisionOn(x1, y1) || this.game.isCollisionOn(x2, y2));
        this.game.interactWithItem(x2, y2);
        this.game.interactWithItem(x2, y2);

        return  collision;
    }
}

