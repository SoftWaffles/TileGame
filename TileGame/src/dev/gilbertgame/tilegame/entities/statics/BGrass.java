package dev.gilbertgame.tilegame.entities.statics;

import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.gfx.Assets;
import dev.gilbertgame.tilegame.tiles.Tile;

import java.awt.*;

public class BGrass extends StaticEntity {

    public BGrass(Handler handler, float x, float y){
        super(handler, x, y, Tile.DEFAULT_TILE_WIDTH, Tile.DEFAULT_TILE_HEIGHT);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
    }
    public void update(){}
    public void render(Graphics g){
        g.setColor(Color.black);
        //g.drawRect((int)(x + bounds.x - handler.getCamera().getxOffset()),
                //(int)(y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        g.drawImage(Assets.border_grass, (int)(x-handler.getCamera().getxOffset()),
                (int)(y - handler.getCamera().getyOffset()), width, height, null);
    }
    public void die(){

    }
}

