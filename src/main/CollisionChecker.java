package main;

import entity.Entity;
import item.Item;

public class CollisionChecker {
    Game game;

    public CollisionChecker(Game game) {
        this.game = game;
    }

    public void checkTile(Entity entity) {
        int worldX = entity.worldX + entity.collisionBox.x;
        int worldY = entity.worldY + entity.collisionBox.y;
        int speed = entity.speed;
        int width = entity.collisionBox.width;
        int height = entity.collisionBox.height;

        switch (entity.direction) {
            case "up":
                entity.hittable = checkCollision(worldX, worldY - speed, worldX + width, worldY - speed);
                break;
            case "down":
                entity.hittable = checkCollision(worldX, worldY + height + speed, worldX + width, worldY + height + speed);
                break;
            case "left":
                entity.hittable = checkCollision(worldX - speed, worldY, worldX - speed, worldY + height);
                break;
            case "right":
                entity.hittable = checkCollision(worldX + width + speed, worldY, worldX + width + speed, worldY + height);
                break;
        }
    }

    private boolean checkCollision(int x1, int y1, int x2, int y2) {
        //todo: pls! learn some shit! wtf is this logic
        boolean collision = (this.game.isCollisionOn(x1, y1) || this.game.isCollisionOn(x2, y2));
        Item item = this.game.getItem(x1, y1);
        if (item != null){
            collision |= item.onInteract(this.game.player);
        }
        Item item2 = this.game.getItem(x2, y2);
        if (item2 != null){
            collision |= item2.onInteract(this.game.player);
        }
        return  collision;
    }
}

