package dev.gilbertgame.tilegame.sound;

import javax.sound.sampled.Clip;

public class Sound{
    Clip sound;
    private String type;

    public Sound(Clip sound, String type){
        this.sound = sound;
        this.type = type;
    }

    public Clip getSound() {
        return sound;
    }

    public String getType() {
        return type;
    }
}
