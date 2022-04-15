package dev.gilbertgame.tilegame.worlds;

import java.awt.Graphics;
import java.awt.geom.GeneralPath;

import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.entities.EntityManager;
import dev.gilbertgame.tilegame.entities.creatures.Player;
import dev.gilbertgame.tilegame.entities.creatures.Slime;
import dev.gilbertgame.tilegame.entities.statics.Wall;
import dev.gilbertgame.tilegame.tiles.Tile;
import dev.gilbertgame.tilegame.utils.Utils;

public class World {

    private Handler handler;
    private int width, height;
    private int[][] tiles;

    public World(Handler handler, String path){
        this.handler = handler;
        loadWorld(path);
    }

    public void update(){ }

    public void render(Graphics g){
        int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.DEFAULT_TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.DEFAULT_TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.DEFAULT_TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.DEFAULT_TILE_HEIGHT + 1);

        for(int y = yStart;y < yEnd;y++){
            for(int x = xStart;x < xEnd;x++){
                getTile(x, y).render(g, (int) (x * Tile.DEFAULT_TILE_WIDTH - handler.getCamera().getxOffset()),
                        (int) (y * Tile.DEFAULT_TILE_HEIGHT - handler.getCamera().getyOffset()));
            }
        }
    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.grassTile;
        return t;
    }

    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        tiles = new int[width][height];
        for(int y = 0;y < height;y++){
            for(int x = 0;x < width;x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}

