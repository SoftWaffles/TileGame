package dev.gilbertgame.tilegame.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.entities.creatures.Creature;
import dev.gilbertgame.tilegame.gfx.Assets;

public abstract class Entity {

    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected double health;
    protected boolean active = true;
    protected boolean facingOrigin = false;
    public double DEFAULT_HEALTH = 20;
    public boolean hurting = false;

    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = DEFAULT_HEALTH;

        bounds = new Rectangle(0, 0, width, height);
    }
    public abstract boolean isCreature();

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void die();

    public void hurt(double amt){
        health -= amt;
        if(health <= 0) {
            die();
            active = false;
        }
    }
    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e : handler.getObjects().getEntityManager().getEntities()){
            if(e.equals(this) || e.isCreature())
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    //SETTERS AND GETTERS
    public void setHurting(boolean hurting) {
        this.hurting = hurting;
    }

    public boolean getOrientation(){
        return facingOrigin;
    }

    public void dirRender(BufferedImage img, Graphics g){
        if(getOrientation())
            g.drawImage(img, (int)(x + width - handler.getCamera().getxOffset()), (int)(y- handler.getCamera().getyOffset()), -width, height, null);
        else
            g.drawImage(img, (int)(x - handler.getCamera().getxOffset()), (int)(y- handler.getCamera().getyOffset()), width, height, null);
    }
    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}