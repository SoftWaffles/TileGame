package dev.gilbertgame.tilegame;

import dev.gilbertgame.tilegame.display.Display;
import dev.gilbertgame.tilegame.gfx.Assets;
import dev.gilbertgame.tilegame.gfx.GameCamera;
import dev.gilbertgame.tilegame.input.KeyManager;
import dev.gilbertgame.tilegame.input.MouseManager;
import dev.gilbertgame.tilegame.sound.SoundManager;
import dev.gilbertgame.tilegame.states.GameState;
import dev.gilbertgame.tilegame.states.MenuState;
import dev.gilbertgame.tilegame.states.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display display;
    private int width, height;
    private String title;

    private Thread thread;
    private boolean running = false;
    public boolean gameOver = false;

    private BufferStrategy bs;
    private Graphics g;
    //input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private SoundManager soundManager;

    //states and menus and stuff
    public State gameState;
    private State gameInit;
    public State menuState;

    //panning with the camera
    private GameCamera camera;


    //mega handler class
    private Handler handler;

    public Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        soundManager = new SoundManager();
    }

    public void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        camera = new GameCamera(handler,0,0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);
        soundManager.playMusic("res/sounds/cute_piano_backup_2.wav", -20.0f);
    }
    private void update(){
        keyManager.update();
        if(State.getState() != null)
            State.getState().update();
    }
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Make sure to clear the screen
        g.clearRect(0,0,width, height);
        //Drawing happens here!!
        if(State.getState() != null)
            State.getState().render(g);
        //End Drawing!
        bs.show();//goes through the various buffers that we've made
        g.dispose();//clear the graphics objecT!!
    }

    public void run(){
        init();
        //used for optimization and keeping the speed of the game teh same
        int fps = 60;
        //One million nano seconds = one second. Meaning you have 1/60 of second for each render
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        //to show the fps
        long timer = 0;
        int ticks = 0;

        while(running){
            now = System.nanoTime();
            //checking if enough time has passed
            delta += (now - lastTime)/timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if(delta >= 1){
                update();
                render();
                ticks++;
                delta--;
            }
            //to see how many ticks (updates) happened in the last second
            if(timer >= 1000000000){
                System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0; //clearing values
            }
        }
    }
    public State getGameState(){ return gameState; }
    public void restart(){
        gameState = new GameState(handler);
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public KeyManager getKeyManager(){
        return keyManager;
    }
    public MouseManager getMouseManager(){
        return mouseManager;
    }
    public SoundManager getSoundManager() {
        return soundManager;
    }

    public GameCamera getCamera(){
        return camera;
    }
    public Graphics getG() { return g; }
    public JFrame getFrame(){
        return display.getFrame();
    }

    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
