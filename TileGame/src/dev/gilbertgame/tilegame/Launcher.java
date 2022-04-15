package dev.gilbertgame.tilegame;

public class Launcher {

    public static void main(String[] args){
        
        Game game = new Game("Gilbert's Slime Showdown! - Made by Kevin", 640, 640);
        //Creates the window
        game.start();
    }
}
