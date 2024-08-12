package item;

import main.Game;

import java.awt.*;
import java.util.HashMap;

public class ItemManager {
    Game game;
    HashMap<String, Item> items;

    public ItemManager(Game game) {
        this.game = game;
        items = new HashMap<>();
    }

    public void addItem(int tileX, int tileY, String itemName) {
        Item item = new Item(tileX, tileY, game.tileSize, itemName, false);
        String key = tileX + " " + tileY;
        items.put(key, item);
    }

    public Item getItem(int worldX, int worldY) {
        String key = worldX/this.game.tileSize + " " + worldY/this.game.tileSize;
        return items.get(key);
    }

    public void draw(Graphics2D graphics2D) {
        for (HashMap.Entry<String, Item> entry : items.entrySet()) {
            Item item = entry.getValue();
            int[] screenPos = this.game.translateToScreenView(item.x * this.game.tileSize, item.y * this.game.tileSize);
            item.draw(graphics2D, screenPos[0], screenPos[1]);
        }
    }
}
