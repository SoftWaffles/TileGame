package dev.gilbertgame.tilegame.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage loadImage(String path){
        try{
            return ImageIO.read(new FileInputStream(path));
        }catch ( IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
