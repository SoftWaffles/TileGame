package dev.gilbertgame.tilegame.utils;

import dev.gilbertgame.tilegame.properties.Vector2D;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null)
                builder.append(line + "\n");

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }
    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }
    public static BufferedImage rotate(BufferedImage img, int angle) {
        double sin = Math.abs(Math.sin(Math.toRadians(angle))),
                cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = img.getWidth(null), h = img.getHeight(null);
        int neww = (int) Math.floor(w*cos + h*sin),
                newh = (int) Math.floor(h*cos + w*sin);
        BufferedImage newImage = new BufferedImage(neww, newh, img.getType());
        Graphics2D g = newImage.createGraphics();
        g.translate((neww-w)/2, (newh-h)/2);
        g.rotate(Math.toRadians(angle), w/2, h/2);
        g.drawRenderedImage(img, null);
        g.dispose();
        return newImage;
    }
    public static double calcDis(int x1,int y1,int x2,int y2) {
        return (Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)));
    }
    public static double calcAngle(int x1,int y1,int x2,int y2){
        double angle = Math.toDegrees(Math.atan2((x2-x1),(y2-y1))) - 90;
        if(angle < 0)
            angle += 360;
        return angle;
    }
    public static Vector2D polarConversion(double radius, double angle){
        Vector2D point = new Vector2D();
        point.setY((int)(radius*Math.cos(Math.toRadians(angle-270))));
        point.setX((int)(radius*Math.sin(Math.toRadians(angle-270))));
        return point;
    }
    public static BufferedImage grayScale(BufferedImage b){
        BufferedImage result = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphic = result.createGraphics();
        graphic.drawImage(b, 0,0, Color.WHITE, null);
        graphic.dispose();
        return result;
    }
}
