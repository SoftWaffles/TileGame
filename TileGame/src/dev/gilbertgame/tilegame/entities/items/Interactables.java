package dev.gilbertgame.tilegame.entities.items;

import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.entities.Entity;
import dev.gilbertgame.tilegame.entities.creatures.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Interactables extends Entity {

    protected Player parent;

    public Interactables(Handler handler, Player parent, int width, int height){
        super(handler, parent.getX(),parent.getY(), width, height);
        this.parent = parent;
    }

    public void attachParent(Player e, int xOffset, int raw_yOffset){
        int yOffset = raw_yOffset + (3*e.getAnimOffset());
        if(parent.getOrientation()) {
            x = e.getX() + xOffset;
            y = e.getY() + yOffset;
        }
        else {
            x = e.getX() + xOffset/2;
            y = e.getY() + yOffset;
        }
    }
    @Override
    public boolean isCreature() {
        return false;
    }
    public abstract void use();

    @Override
    public void dirRender(BufferedImage img, Graphics g){
        if(parent.getOrientation())
            g.drawImage(img, (int)(x + width - handler.getCamera().getxOffset()), (int)(y- handler.getCamera().getyOffset()), -width, height, null);
        else
            g.drawImage(img, (int)(x - handler.getCamera().getxOffset()), (int)(y- handler.getCamera().getyOffset()), width, height, null);
    }
}
