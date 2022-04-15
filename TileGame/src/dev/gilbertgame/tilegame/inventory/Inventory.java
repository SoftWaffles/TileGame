package dev.gilbertgame.tilegame.inventory;

import dev.gilbertgame.tilegame.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    //private ArrayList<Item> inventoryItems;

    public Inventory(Handler handler){
        this.handler = handler;
    }

    public void update(){
        if (handler.getKeyManager().wasPressed(KeyEvent.VK_E))
                active = !active;
        if(!active)
            return;
    }
    public void render(Graphics g){
        if(!active)
            return;
    }
    //public void addItem(Item item){ }
    //GETTERS AND SETTERS
    public Handler getHandler() {
        return handler;
    }
}
