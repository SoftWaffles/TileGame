package dev.gilbertgame.tilegame.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class SoundManager {
    private ArrayList<Sound> sounds = new ArrayList<>();

    public void update(){
        for(int i = sounds.size()-1; i >= 0; i--){
            if(!sounds.get(i).getSound().isRunning())
                sounds.remove(i);
        }
    }

    public void stopAll(){
        for(int i = sounds.size()-1 ;i >= 0; i--){
            sounds.get(i).getSound().close();
            sounds.remove(i);
        }
    }
    public void stopType(String a){
        for(Sound c: sounds){
            if(c.getType().equals(a) && c.getSound().isRunning())
                c.getSound().close();
        }
    }
    private boolean playingType(String a){
        for(Sound c: sounds){
            if(c.getType().equals(a) && c.getSound().isRunning())
                return true;
        }
        return false;
    }

    public void playSound(String path, float volume){
        if(playingType(path))
            return;
        try{
            File soundPath = new File(path);
            if(soundPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl =
                        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volume);
                clip.start();
                sounds.add(new Sound(clip, path));
            }else{
                System.out.println("Cant find sound file at: " + path);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void playMusic(String path, float volume){
        if(playingType(path))
            return;
        try{
            File soundPath = new File(path);
            if(soundPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl =
                        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volume);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                sounds.add(new Sound(clip, path));
            }else{
                System.out.println("Cant find sound file at: " + path);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
