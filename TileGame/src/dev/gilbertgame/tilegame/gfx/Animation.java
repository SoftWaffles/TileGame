package dev.gilbertgame.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Animation {
    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;
    private int loopsDone;

    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis();
    }
    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(timer > speed){
            index++;
            timer = 0;
            if(index >= frames.length){
                loopsDone++;
                index = 0;
            }
        }
    }

    public long getTimer(){
        return timer;
    }
    public int getIndex(){
        return index;
    }
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
    public int getLoopsDone(){
        return loopsDone;
    }
    public void setLoopsDone(int a){
        loopsDone = a;
    }
}
