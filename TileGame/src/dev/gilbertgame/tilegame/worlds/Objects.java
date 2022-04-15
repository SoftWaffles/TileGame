package dev.gilbertgame.tilegame.worlds;

import java.awt.Graphics;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.entities.Entity;
import dev.gilbertgame.tilegame.entities.EntityManager;
import dev.gilbertgame.tilegame.entities.creatures.Player;
import dev.gilbertgame.tilegame.entities.creatures.Slime;
import dev.gilbertgame.tilegame.entities.statics.Wall;
import dev.gilbertgame.tilegame.properties.Vector2D;
import dev.gilbertgame.tilegame.tiles.Tile;
import dev.gilbertgame.tilegame.utils.Utils;

public class Objects {

    private Handler handler;
    private int spawnX, spawnY;
    //Entities
    private EntityManager entityManager;

    public Objects(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, spawnX, spawnY));
        loadLayer(path);
        loadExtra(25);
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void update(){
        entityManager.update();
    }

    public void render(Graphics g){
        //Entities
        entityManager.render(g);
    }

    private void loadLayer(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        spawnX = Utils.parseInt(tokens[0]);
        spawnY = Utils.parseInt(tokens[1]);
        for(int i = 2; i < tokens.length; i+=3){
            entityManager.addEntity(Utils.parseInt(tokens[i]),Utils.parseInt(tokens[i+1]),Utils.parseInt(tokens[i+2]));
        }
    }
    private void loadExtra(int amt){
        for(int i = 0; i < amt; i++){
            Vector2D cords = Utils.polarConversion((300*Math.random()+100), 360*Math.random());
            if((Math.abs(spawnX - cords.getX()) > 50) && (Math.abs(spawnY - cords.getY()) > 50))
                entityManager.addEntity((int)(4*Math.random()+16),cords.getX()+512, cords.getY()+512);
        }
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }

}

