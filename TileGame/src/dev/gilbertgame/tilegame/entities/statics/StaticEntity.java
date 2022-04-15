package dev.gilbertgame.tilegame.entities.statics;

import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.entities.Entity;


public abstract class StaticEntity extends Entity {
    public StaticEntity(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
    }
    public boolean isCreature(){
        return false;
    }
}
