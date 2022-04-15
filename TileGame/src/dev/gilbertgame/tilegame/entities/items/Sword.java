package dev.gilbertgame.tilegame.entities.items;

import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.entities.Entity;
import dev.gilbertgame.tilegame.entities.creatures.Player;
import dev.gilbertgame.tilegame.gfx.Animation;
import dev.gilbertgame.tilegame.gfx.Assets;
import dev.gilbertgame.tilegame.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sword extends Interactables {

    protected BufferedImage texture;
    private int default_xOffset = -20;
    private int default_yOffset = -20;
    private float pMidX;
    private float pMidY;
    private int swingSpeed = 100;
    private double dmg = 1;
    private int offsetX,offsetY;
    private boolean attacking = false;
    private Animation swing, slash;
    Rectangle ar = new Rectangle();

    private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;

    public Sword(Handler handler, Player parent, int width, int height){
        super(handler, parent, width, height);
        pMidX = parent.getWidth()/2.0f;
        pMidY = parent.getHeight()/2.0f;
        texture = Assets.starterSword[0];
        bounds.x = 20;
        bounds.y = 30;
        bounds.width = 26;
        bounds.height = 26;
        lastAttackTimer = System.currentTimeMillis();

        swing = new Animation(swingSpeed, Assets.starterSword);
        slash = new Animation(swingSpeed, Assets.swing);

        //
        int arSize = 100;
        ar.width = arSize;
        ar.height = arSize;
    }
    @Override
    public void update() {
        offsetX = -(int)handler.getCamera().getxOffset();
        offsetY = -(int)handler.getCamera().getyOffset();
        pMidX = parent.getX()+parent.getWidth()/2.0f;
        pMidY = parent.getY()+parent.getHeight()/2.0f;
        attachParent(parent, default_xOffset, default_yOffset);
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(swing.getLoopsDone() >= 1)
            attacking = false;
    }
    //(int)playerMidX - 64 + surround.getX()
    @Override
    public void render(Graphics g) {
        if(parent.isActive())
            super.dirRender(swingRender(), g);
        if(attacking){
            //g.drawRect(ar.x + offsetX, ar.y + offsetY, ar.width, ar.height);
            g.drawImage(Utils.rotate(slash.getCurrentFrame(), -(int)(handler.getAngle()-45)), ar.x + offsetX - ar.width/2, ar.y + offsetY - ar.height/2, width*2, height*2, null);
        }
    }

    @Override
    public void die() {

    }
    public void use(){
        ar.x = (int)pMidX + handler.getSurround().getX() - ar.width/2;
        ar.y = (int)pMidY + handler.getSurround().getY() - ar.height/2;
        if(!parent.hurting && (attackTimer < attackCooldown))
            return;
        handler.getSoundManager().playSound("res/sounds/swosh-10.wav", -20.0f);
        for(Entity e: handler.getObjects().getEntityManager().getEntities()){
            if(e.equals(this) || e.equals(parent) || !e.isCreature())
                continue;
            if(e.getCollisionBounds(0, 0).intersects(ar) && !e.hurting) {
                e.hurt(dmg);
                e.setHurting(true);
                attackTimer = 0;
            } else{
                e.setHurting(false);
            }
        }
    }

    public BufferedImage swingRender(){
        if(!parent.hurting && (handler.getMouseManager().isLeftPressed() || attacking)) {
            use();
            attacking = true;
            return getCurrentAnimationFrame();
        }
        return Assets.starterSword[0];
    }

    public double getDmg() {
        return dmg;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }

    private BufferedImage getCurrentAnimationFrame(){
        swing.tick();
        slash.tick();
        return  swing.getCurrentFrame();
    }
    public boolean isAttacking() {
        return attacking;
    }
}
