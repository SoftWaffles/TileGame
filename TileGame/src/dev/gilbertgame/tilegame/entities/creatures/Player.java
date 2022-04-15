package dev.gilbertgame.tilegame.entities.creatures;

import dev.gilbertgame.tilegame.Game;
import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.gfx.Animation;
import dev.gilbertgame.tilegame.gfx.Assets;
import dev.gilbertgame.tilegame.gfx.Text;
import dev.gilbertgame.tilegame.inventory.Inventory;
import dev.gilbertgame.tilegame.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    private Animation animStill, animRun, animDust;
    private Inventory inventory;

    public Player(Handler handler, float x, float y){
        super(handler, x,y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 20;
        bounds.y = 30;
        bounds.width = 26;
        bounds.height = 26;
        DEFAULT_HEALTH = 20;
        health = DEFAULT_HEALTH;

        //Animations
        animStill = new Animation(200, Assets.player_still);
        animRun = new Animation(200, Assets.player_run);
        animDust = new Animation(50, Assets.run_dust);

        inventory = new Inventory(handler);
    }
    public void update(){
        if(hurting)
            hurting  = getAnimOffset() <= 2;
        facingOrigin = !(handler.getMouseManager().getMouseX() + handler.getCamera().getxOffset() >= this.x);
        animStill.tick();
        animRun.tick();
        animDust.tick();
        getInput();
        move();
        handler.getCamera().centerOnEntity(this);
        inventory.update();
    }
    private void getInput(){
        xMove = 0;
        yMove = 0;
        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }
    public void render(Graphics g) {
        //g.drawRect((int) (bounds.x + x - handler.getCamera().getxOffset()), (int) (bounds.y + y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
        if (xMove != 0 || yMove != 0){
            for (int i = 1; i < 2; i++) {
                g.drawImage(animDust.getCurrentFrame(), (int) (bounds.x + x - handler.getCamera().getxOffset() - i*xMove*10), (int) (bounds.y + y - handler.getCamera().getyOffset() - i*yMove*10), null);
            }
        }

        dirRender(getCurrentAnimationFrame(), g);
        inventory.render(g);
        renderHealth(g);
    }
    @Override
    public void hurt(double amt){
        handler.getSoundManager().playSound("res/sounds/hurt.wav", -30.0f);
        dmgTaken = amt;
        health -= amt;
        if(health <= 0) {
            die();
            active = false;
        }
    }
    public void die(){
        handler.getGame().gameOver = true;
        handler.getSoundManager().playSound("res/sounds/downer.wav", -10.0f);
        handler.getGame().getGameState().endScreen();
    }
    public BufferedImage getCurrentAnimationFrame(){
        if(hurting)
            return Utils.rotate(Assets.playerHurt, -15);
        if(xMove != 0 || yMove != 0){
            return animRun.getCurrentFrame();
        }else{
            return animStill.getCurrentFrame();
        }
    }
    public int getAnimOffset(){
        return animStill.getIndex();
    }
}
