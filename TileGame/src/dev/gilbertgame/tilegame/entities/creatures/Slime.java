package dev.gilbertgame.tilegame.entities.creatures;

import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.entities.Entity;
import dev.gilbertgame.tilegame.gfx.Animation;
import dev.gilbertgame.tilegame.gfx.Assets;
import dev.gilbertgame.tilegame.states.State;
import dev.gilbertgame.tilegame.utils.Utils;
import org.w3c.dom.xpath.XPathResult;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Slime extends Creature{


    private final int aggroRadius = 400;
    private Animation animStill,animChase, animDie;
    private double distToPlayer;
    private int dir = 1;
    private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;

    public Slime(Handler handler, float x, float y){
        super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
        bounds.x = 20;
        bounds.y = 30;
        bounds.width = 26;
        bounds.height = 26;
        speed = 2f;
        DEFAULT_HEALTH = 20;
        health = DEFAULT_HEALTH;

        animStill = new Animation(200, Assets.blue_slime_still);
        animChase = new Animation(300, Assets.blue_slime_chase);
        animDie = new Animation(100, Assets.slime_die);
        lastAttackTimer = System.currentTimeMillis();
    }
    public void targetMove(Entity e, boolean targeting){
        xMove = 0;
        yMove = 0;
        float ran = (float)Math.random();
        if(targeting && distToPlayer > 10){
            float tmpSpeed = this.speed * ran;
            xMove = e.getX() < this.getX() ? -tmpSpeed*dir : tmpSpeed*dir;
            yMove = e.getY() < this.getY() ? -tmpSpeed*dir : tmpSpeed*dir;
        }
    }

    @Override
    public void update() {
        if(hurting){
            dir = -1;
            speed = 5f;
            hurting  = handler.getPlayer().getAnimOffset() <= 2;
        }else{
            dir = 1;
            speed = 2f;
        }
        facingOrigin = (handler.getPlayer().getX() >= this.x);
        animStill.tick();
        animChase.tick();
        animDie.tick();
        distToPlayer = Utils.calcDis((int)x, (int)y, (int)handler.getPlayer().getX(), (int)handler.getPlayer().getY());
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        move();
        attemptAttack();
    }

    @Override
    public void render(Graphics g) {
        //g.drawRect((int)(bounds.x+x-handler.getCamera().getxOffset()),(int)(bounds.y+y-handler.getCamera().getyOffset()), bounds.width, bounds.height);
        dirRender(getCurrentAnimationFrame(), g);
        renderHealth(g);
    }
    @Override
    public void hurt(double amt){
        handler.getSoundManager().playSound("res/sounds/splat.wav", -15.0f);
        health -= amt;
        dmgTaken = amt;
        if(health <= 0) {
            active = false;
        }
    }
    @Override
    public void die() {
    }

    private void attemptAttack(){
        if(handler.getPlayer().getCollisionBounds(0f, 0f).intersects(getCollisionBounds(0f,0f))) {
            if (attackTimer >= attackCooldown && !handler.getGame().gameOver) {
                if(!handler.getPlayer().hurting) {
                    handler.getPlayer().hurt(1+State.getDiff()/5.0);
                    attackTimer = 0;
                    handler.getPlayer().hurting = true;
                }
            }
        }
    }

    public BufferedImage getCurrentAnimationFrame(){
        if(distToPlayer <= aggroRadius){
            targetMove(handler.getPlayer(), true);
            if(hurting)
                return Utils.rotate(animChase.getCurrentFrame(), 30);
            return animChase.getCurrentFrame();
        }
        return animStill.getCurrentFrame();
    }
}
