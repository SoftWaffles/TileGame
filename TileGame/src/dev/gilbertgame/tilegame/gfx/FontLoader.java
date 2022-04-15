package dev.gilbertgame.tilegame.gfx;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    public static Font loadFont(String path, float size){
        try{
            InputStream is = new FileInputStream(path);
            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
        } catch (FontFormatException | IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
