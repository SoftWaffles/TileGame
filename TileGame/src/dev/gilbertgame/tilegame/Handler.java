package dev.gilbertgame.tilegame;

import dev.gilbertgame.tilegame.entities.creatures.Player;
import dev.gilbertgame.tilegame.gfx.GameCamera;
import dev.gilbertgame.tilegame.input.KeyManager;
import dev.gilbertgame.tilegame.input.MouseManager;
import dev.gilbertgame.tilegame.properties.Vector2D;
import dev.gilbertgame.tilegame.sound.SoundManager;
import dev.gilbertgame.tilegame.states.State;
import dev.gilbertgame.tilegame.worlds.Objects;
import dev.gilbertgame.tilegame.worlds.World;

import java.awt.*;

public class Handler {
    private Game game;
    private World world;
    private Objects objects;
    private Player player;

    public Handler(Game game){
        this.game = game;
    }
    public GameCamera getCamera(){
        return game.getCamera();
    }
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }
    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }
    public SoundManager getSoundManager(){ return game.getSoundManager();};
    public int getWidth(){
        return game.getWidth();
    }
    public int getHeight(){
        return game.getHeight();
    }
    public Graphics getGraphics(){ return game.getG();}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Objects getObjects(){
        return objects;
    }

    public void setObjects(Objects objects) {
        this.objects = objects;
    }

    public Player getPlayer(){
        return getObjects().getEntityManager().getPlayer();
    }

    public Vector2D getSurround(){
        return State.getSurround();
    }
    public double getAngle(){
        return State.getAngle();
    }
}
