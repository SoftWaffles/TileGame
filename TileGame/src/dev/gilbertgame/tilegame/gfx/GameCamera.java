package dev.gilbertgame.tilegame.gfx;

import dev.gilbertgame.tilegame.Game;
import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.entities.Entity;
import dev.gilbertgame.tilegame.tiles.Tile;

public class GameCamera {

    private float xOffset, yOffset;
    private Handler handler;

    public GameCamera(Handler handler, float xOffset, float yOffset){
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    public void centerOnEntity(Entity e){
        xOffset = e.getX() - handler.getWidth()/2 + e.getWidth()/2;
        yOffset = e.getY() - handler.getHeight()/2 + e.getHeight()/2;
        checkBlank();
    }
    public void checkBlank(){
        if(xOffset < 0) {
            xOffset = 0;
        }else if(xOffset > handler.getWorld().getWidth()* Tile.DEFAULT_TILE_WIDTH - handler.getWidth()){
            xOffset = handler.getWorld().getWidth()* Tile.DEFAULT_TILE_WIDTH - handler.getWidth();
        }
        if(yOffset < 0) {
            yOffset = 0;
        }else if(yOffset > handler.getWorld().getHeight()* Tile.DEFAULT_TILE_HEIGHT - handler.getHeight()){
            yOffset = handler.getWorld().getHeight()* Tile.DEFAULT_TILE_HEIGHT - handler.getHeight();
        }
    }
    public void pan(float xAmt, float yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
    }

    public float getxOffset(){
        return xOffset;
    }
    public float getyOffset(){
        return yOffset;
    }
    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }
    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
