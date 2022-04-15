package dev.gilbertgame.tilegame.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

public class Assets {
    private static final int width = 32, height = 32;

    public static Font font28;
    public static Font font80;
    public static Font font12;

    public static BufferedImage[] player_still = new BufferedImage[4];
    public static BufferedImage[] player_run  = new BufferedImage[4];
    public static BufferedImage[] run_dust = new BufferedImage[4];
    public static BufferedImage[] blue_slime_still = new BufferedImage[2];
    public static BufferedImage[] blue_slime_chase = new BufferedImage[4];
    public static BufferedImage[] slime_die = new BufferedImage[4];
    public static BufferedImage[] starterSword = new BufferedImage[4];
    public static BufferedImage[] swing = new BufferedImage[4];
    public static BufferedImage[] starterStaff = new BufferedImage[2];
    public static BufferedImage[] cursor = new BufferedImage[2];
    public static BufferedImage target;
    public static BufferedImage playerHurt;
    //TILES AND BARRIER OBJECTs
    public static BufferedImage dirt, dark_dark, grass, dark_grass, sand, dark_sand,
                                flowers, path_horizontal, path_vertical, pBend_Left_Down, pBend_Left_Up, pBend_Down_Right,
                                pBend_Up_Right, sign, rock, small_rock, bush, small_bush,
                                wall, border_grass, bGrass_B, bGrass_LT, bGrass_L, bGrass_LB,
                                bGrass_TR, bGrass_R, bGrass_RB, bGrass_CL, bGRass_CR, bGrass_CLD,
                                bGrass_CRD, bGrass_T;
    public static BufferedImage[] startButton = new BufferedImage[2];
    public static BufferedImage[] exitButton  = new BufferedImage[2];
    public static BufferedImage[] restartButton = new BufferedImage[2];
    public static void init(){
        font12 = FontLoader.loadFont("TileGame/res/fonts/retro.ttf", 12);
        font28 = FontLoader.loadFont("TileGame/res/fonts/retro.ttf", 28);
        font80 = FontLoader.loadFont("TileGame/res/fonts/retro.ttf", 80);

        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("TileGame/res/textures/TILES.png"));
        dirt = sheet1.crop(0,0, width, height);
        dark_dark = sheet1.crop(width, 0, width, height);
        grass = sheet1.crop(width*2, 0, width, height);
        dark_grass = sheet1.crop(width*3, 0, width, height);
        sand = sheet1.crop(width*4,0,width,height);
        dark_sand = sheet1.crop(width*5, 0,width,height);
        //
        flowers = sheet1.crop(0,height, width, height);
        path_horizontal = sheet1.crop(width, height, width, height);
        path_vertical = sheet1.crop(width*2, height, width, height);
        pBend_Left_Down = sheet1.crop(width*3, height, width, height);
        pBend_Left_Up = sheet1.crop(width*4,height,width,height);
        pBend_Down_Right = sheet1.crop(width*5, height,width,height);
        //
        pBend_Up_Right= sheet1.crop(0, height*2, width, height);
        sign = sheet1.crop(width, height*2, width, height);
        rock = sheet1.crop(width*2, height*2, width, height);
        small_rock = sheet1.crop(width*3, height*2, width, height);
        bush = sheet1.crop(width*4,height*2,width,height);
        small_bush = sheet1.crop(width*5, height*2,width,height);
        //
        wall= sheet1.crop(0, height*3, width, height);
        border_grass = sheet1.crop(width, height*3, width, height);
        bGrass_B = sheet1.crop(width*2, height*3, width, height);
        bGrass_LT = sheet1.crop(width*3, height*3, width, height);
        bGrass_L = sheet1.crop(width*4,height*3,width,height);
        bGrass_LB = sheet1.crop(width*5, height*3,width,height);
        //
        bGrass_TR= sheet1.crop(0, height*4, width, height);
        bGrass_R = sheet1.crop(width, height*4, width, height);
        bGrass_RB = sheet1.crop(width*2, height*4, width, height);
        bGrass_CL = sheet1.crop(width*3, height*4, width, height);
        bGRass_CR = sheet1.crop(width*4,height*4,width,height);
        bGrass_CLD = sheet1.crop(width*5, height*4,width,height);
        //
        bGrass_CRD= sheet1.crop(0, height*5, width, height);
        bGrass_T = sheet1.crop(width, height*5, width, height);
        //
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("TileGame/res/textures/CHARACTER_SPRITESHEET.png"));
        player_still[0] = sheet2.crop(0, 0, width, height);
        player_still[1] = sheet2.crop(width, 0, width, height);
        player_still[2] = sheet2.crop(width*2, 0, width, height);
        player_still[3] = sheet2.crop(width*3, 0, width, height);
        player_run[0] = sheet2.crop(0, height, width, height);
        player_run[1] = sheet2.crop(width, height, width, height);
        player_run[2] = sheet2.crop(width*2, height, width, height);
        player_run[3] = sheet2.crop(width*3, height, width, height);
        run_dust[0] = sheet2.crop(0, height*2, width, height);
        run_dust[1] = sheet2.crop(width, height*2, width, height);
        run_dust[2] = sheet2.crop(width*2, height*2, width, height);
        run_dust[3] = sheet2.crop(width*3, height*2, width, height);
        playerHurt = sheet2.crop(0, height*3, width, height);
        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.loadImage("TileGame/res/textures/ENEMIES.png"));
        blue_slime_still[0] = sheet3.crop(0,0, width, height);
        blue_slime_still[1] = sheet3.crop(width, 0, width, height);
        blue_slime_chase[0] = sheet3.crop(width*2, 0, width, height);
        blue_slime_chase[1] = sheet3.crop(width*3, 0, width, height);
        blue_slime_chase[2] = sheet3.crop(width*4, 0, width, height);
        blue_slime_chase[3] = sheet3.crop(width*5, 0, width, height);
        slime_die[0] = sheet3.crop(0, height, width, height);
        slime_die[1] = sheet3.crop(width, height, width, height);
        slime_die[2] = sheet3.crop(width*2, height, width, height);
        slime_die[3] = sheet3.crop(width*3, height, width, height);
        SpriteSheet sheet4 = new SpriteSheet(ImageLoader.loadImage("TileGame/res/textures/CURSOR_AND_MENU.png"));
        cursor[0] = sheet4.crop(0,0, 26, 26);
        cursor[1] = sheet4.crop(width*2, 0, 30, 30);
        startButton[0] = sheet4.crop(0, height*2, 64, 16);
        startButton[1] = sheet4.crop(width*2, height*2,64, 16);
        exitButton [0] = sheet4.crop(0, 80, 47, 16);
        exitButton[1] = sheet4.crop(width*2, 80, 47, 16);
        restartButton[0] = sheet4.crop(0, height*4, 64, 16);
        restartButton[1] = sheet4.crop(width*2, height*4,64, 16);
        SpriteSheet sheet5 = new SpriteSheet(ImageLoader.loadImage("TileGame/res/textures/WEAPONS.png"));
        target = sheet5.crop(0,0,width,height);
        swing[0] = sheet5.crop(width, 0, width, height);
        swing[1] = sheet5.crop(width*2, 0, width, height);
        swing[2] = sheet5.crop(width*3, 0, width, height);
        swing[3] = sheet5.crop(width*4, 0, width, height);
        starterSword[0] = sheet5.crop(width, height, width, height);
        starterSword[1] = sheet5.crop(width*2, height, width, height);
        starterSword[2] = sheet5.crop(width*3, height, width, height);
        starterSword[3] = sheet5.crop(width*4, height, width, height);
        starterStaff[0] = sheet5.crop(width, height*2, width, height);
        starterStaff[1] = sheet5.crop(width*2, height*2, width, height);
    }
}
