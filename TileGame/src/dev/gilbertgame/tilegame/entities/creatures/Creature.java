package dev.gilbertgame.tilegame.entities.creatures;

import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.entities.Entity;
import dev.gilbertgame.tilegame.gfx.Assets;
import dev.gilbertgame.tilegame.gfx.Text;
import dev.gilbertgame.tilegame.tiles.Tile;

import java.awt.*;

public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
            DEFAULT_CREATURE_HEIGHT = 64;
    protected float speed;
    protected float xMove, yMove;
    protected double dmgTaken;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }
    public void move(){
        if(!checkEntityCollisions(xMove, 0f))
            moveX();
        if(!checkEntityCollisions(0f, yMove))
            moveY();
    }

    public void moveX(){
        if(xMove > 0){//Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.DEFAULT_TILE_WIDTH;

            if(walkableTile(tx, (int) (y + bounds.y) / Tile.DEFAULT_TILE_HEIGHT) &&
                    walkableTile(tx, (int) (y + bounds.y + bounds.height) / Tile.DEFAULT_TILE_HEIGHT)){
                x += xMove;
            }else{
                x = tx * Tile.DEFAULT_TILE_WIDTH - bounds.x - bounds.width - 1;
            }

        }else if(xMove < 0){//Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.DEFAULT_TILE_WIDTH;

            if(walkableTile(tx, (int) (y + bounds.y) / Tile.DEFAULT_TILE_HEIGHT) &&
                    walkableTile(tx, (int) (y + bounds.y + bounds.height) / Tile.DEFAULT_TILE_HEIGHT)){
                x += xMove;
            }else{
                x = tx * Tile.DEFAULT_TILE_WIDTH + Tile.DEFAULT_TILE_WIDTH - bounds.x;
            }

        }
    }

    public void moveY(){
        if(yMove < 0){//Up
            int ty = (int) (y + yMove + bounds.y) / Tile.DEFAULT_TILE_HEIGHT;

            if(walkableTile((int) (x + bounds.x) / Tile.DEFAULT_TILE_WIDTH, ty) &&
                    walkableTile((int) (x + bounds.x + bounds.width) / Tile.DEFAULT_TILE_WIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.DEFAULT_TILE_HEIGHT + Tile.DEFAULT_TILE_HEIGHT - bounds.y;
            }

        }else if(yMove > 0){//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.DEFAULT_TILE_HEIGHT;

            if(walkableTile((int) (x + bounds.x) / Tile.DEFAULT_TILE_WIDTH, ty) &&
                    walkableTile((int) (x + bounds.x + bounds.width) / Tile.DEFAULT_TILE_WIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.DEFAULT_TILE_HEIGHT - bounds.y - bounds.height - 1;
            }

        }
    }

    protected boolean walkableTile(int x, int y){
        return handler.getWorld().getTile(x, y).isWalkable();
    }
    @Override
    public void hurt(double amt){
        dmgTaken = amt;
        health -= amt;
        handler.getSoundManager().playSound("res/sounds/splat.wav", -30.0f);
        if(health <= 0) {
            die();
            active = false;
        }
    }
    protected void renderHealth(Graphics g){
        g.setColor(Color.WHITE);
        g.drawRect((int)(x + 10 - handler.getCamera().getxOffset()),(int)(y-10-handler.getCamera().getyOffset()), 40, 10);
        g.setColor(Color.RED);
        g.fillRect((int)(x + 10 - handler.getCamera().getxOffset()),(int)(y-10-handler.getCamera().getyOffset()), (int)(40*(health*1.0/DEFAULT_HEALTH)), 10);
        g.setColor(Color.BLACK);
        if(hurting)
            Text.drawString(g, String.format("%.2f", dmgTaken), (int)(x + 20 - handler.getCamera().getxOffset()),(int)(y - 30 -handler.getCamera().getyOffset()), true, Color.WHITE, Assets.font28);
    }
    //GETTERS SETTERS
    public boolean isCreature(){
        return true;
    }
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}