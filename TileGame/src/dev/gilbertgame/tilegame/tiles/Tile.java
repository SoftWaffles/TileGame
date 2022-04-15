package dev.gilbertgame.tilegame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static Tile[] tiles = new Tile[64];
    public static Tile dirtTile = new DirtTile(0);
    public static Tile grassTile = new GrassTile(1);
    public static Tile dGrassTile = new DGrassTile(2);
    public static Tile flowerTile = new FlowersTile(3);
    public static Tile pathH = new PathHTile(4);
    public static Tile pathV = new PathVTile(5);
    public static Tile pBendDR = new PBendDwnRightTile(6);
    public static Tile pBendUR = new PBendUpRightTile(7);
    public static Tile pBendLD = new PBendLeftDwnTile(8);
    public static Tile pBendLU = new PBendLeftUpTile(9);


    public static final int DEFAULT_TILE_WIDTH = 64, DEFAULT_TILE_HEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }

    public void update(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT, null );
    }
    public boolean isWalkable(){
        return true;
    }
    public int getId(){
        return id;
    }
}
