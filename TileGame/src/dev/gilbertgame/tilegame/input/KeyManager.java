package dev.gilbertgame.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys, wasPressed, cannotPress;
    // a big array of all the keys on the keyboard + it stores their states as true or false
    public boolean up, down, left, right, spawnE;

    public KeyManager(){
        keys = new boolean[256];
        wasPressed = new boolean[keys.length];
        cannotPress = new boolean[keys.length];
    }
    public void update(){
        for(int i= 0; i< keys.length; i++){
            if(cannotPress[i] && !keys[i]){
                cannotPress[i] = false;
            }else if(wasPressed[i]){
                cannotPress[i] = true;
                wasPressed[i] = false;
            }
            if(!cannotPress[i] && keys[i]){
                wasPressed[i] = true;
            }
        }
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        spawnE = keys[KeyEvent.VK_E];
    }
    //required methods for implementation of key listener
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = true;
    }
    //made method
    public boolean wasPressed(int keyCode){
        if(keyCode < 0 || keyCode >= keys.length)
            return false;
        return wasPressed[keyCode];
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = false;
    }
    public void keyTyped(KeyEvent e){
    }
}
