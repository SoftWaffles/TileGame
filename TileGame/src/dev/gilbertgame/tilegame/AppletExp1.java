package dev.gilbertgame.tilegame;
import javax.swing.JApplet;
import java.awt.*;

public class AppletExp1 extends JApplet {
    public void init()
    {
        Game game = new Game("Gilbert's Slime Showdown! - Made by Kevin", 640, 640);
        //Creates the window
        game.start();
    }
  
    public void start()
    {
        System.out.println("Starting an applet");
    }
    public void stop()
  
    {
        System.out.println("Stopping an applet");
    }
    public void destroy()
  
    {
        System.out.println("Destroying an applet");
    }
}
