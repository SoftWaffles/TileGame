package dev.gilbertgame.tilegame.states;

import dev.gilbertgame.tilegame.Game;
import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.properties.Vector2D;

import java.awt.*;

public abstract class State {
    //code for targeting
    protected static double angle;
    protected static Vector2D surround;
    protected static long lastTime;
    protected static int difficulty;
    //storage of the state of the games
    private static State currentState = null;

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }
    //CODE RELEVANT TO THE CLASS

    protected Handler handler;

    public static double getAngle() {
        return angle;
    }

    public static Vector2D getSurround() {
        return surround;
    }

    public static int getDiff(){
        return difficulty;
    }
    //constructor of the actual class
    public State(Handler handler){
        this.handler = handler;
    }

    public abstract  void update();

    public abstract  void render(Graphics g);

    public abstract void endScreen();
}
