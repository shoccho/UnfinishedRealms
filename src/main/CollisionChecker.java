package main;

import entity.Entity;

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
        return (this.game.isCollisionOn(x1, y1) || this.game.isCollisionOn(x2, y2));
    }
}

