package main;

import item.ItemManager;

public class ItemAdder {
    ItemManager itemManager;
    Game game;

    public ItemAdder(Game game){
        this.game = game;
        this.itemManager = game.itemManager;
        addSomeChest();
    }

    public void addSomeChest(){
        this.itemManager.addItem(9,15, "chest");
        this.itemManager.addItem(21,3, "chest");
        this.itemManager.addItem(12,23, "chest");

        this.itemManager.addItem(19,15, "door");
        this.itemManager.addItem(23,22, "door");
        this.itemManager.addItem(25,27, "door");
    }

}
