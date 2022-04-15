package dev.gilbertgame.tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.entities.creatures.Player;
import dev.gilbertgame.tilegame.entities.creatures.Slime;
import dev.gilbertgame.tilegame.entities.statics.*;
import dev.gilbertgame.tilegame.gfx.Animation;
import dev.gilbertgame.tilegame.gfx.Assets;
import dev.gilbertgame.tilegame.states.State;
import dev.gilbertgame.tilegame.utils.Utils;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b){
            if(a.getY() + a.getHeight() <= b.getY() + b.getWidth())
                return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }

    public void update(){
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            e.update();
            if(!e.isActive()) {
                it.remove();
            }
        }
        entities.sort(renderSorter);
        if(handler.getKeyManager().spawnE)
            spawnEnemies(0, 2, 0);
    }

    public void render(Graphics g){
        for(Entity e : entities){
            e.render(g);
        }
    }

    public void addEntity(Entity e){
        entities.add(e);
    }
    public void addEntity(int i, int x, int y){
        if(i==0) {
            Slime s = new Slime(handler, x, y) ;
            entities.add(s);
            s.setHealth(s.getHealth()+ 2*State.getDiff());
            s.setWidth(s.getWidth() + 2*State.getDiff());
            s.setHeight(s.getHeight() + 2*State.getDiff());
            s.bounds.width = s.bounds.width + 2*State.getDiff();
            s.bounds.height = s.bounds.height + 2*State.getDiff();
        }
        if(i==1)
            entities.add(new BGrass(handler, x, y));
        if(i==2)
            entities.add(new BGrassB(handler, x, y));
        if(i==3)
            entities.add(new BGrassCL(handler, x, y));
        if(i==4)
            entities.add(new BGrassCLD(handler, x, y));
        if(i==5)
            entities.add(new BGrassCR(handler, x, y));
        if(i==6)
            entities.add(new BGrassCRD(handler, x, y));
        if(i==7)
            entities.add(new BGrassL(handler, x, y));
        if(i==8)
            entities.add(new BGrassLB(handler, x, y));
        if(i==9)
            entities.add(new BGrassLT(handler, x, y));
        if(i==10)
            entities.add(new BGrassR(handler, x, y));
        if(i==11)
            entities.add(new BGrassRB(handler, x, y));
        if(i==12)
            entities.add(new BGrassT(handler, x, y));
        if(i==13)
            entities.add(new BGrassTR(handler, x, y));
        if(i==14)
            entities.add(new BGrassTR(handler, x, y));
        if(i==15)
            entities.add(new Sign(handler, x, y));
        if(i==16)
            entities.add(new Rock(handler, x, y));
        if(i==17)
            entities.add(new SBush(handler, x, y));
        if(i==18)
            entities.add(new Bush(handler,x, y));
        if(i==19)
            entities.add(new SRock(handler,x, y));
    }

    //GETTERS SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public void spawnEnemies(int type, int amt, int diff){
        for(int i = 0; i < amt; i++){
            addEntity(type, Utils.polarConversion(400, (360*Math.random())).getX()+512, Utils.polarConversion(400, (360*Math.random())).getY()+512);
        }
    }
}
