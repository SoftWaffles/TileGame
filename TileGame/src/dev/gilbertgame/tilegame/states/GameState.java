package dev.gilbertgame.tilegame.states;
import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.entities.items.Interactables;
import dev.gilbertgame.tilegame.entities.items.Sword;
import dev.gilbertgame.tilegame.gfx.Animation;
import dev.gilbertgame.tilegame.gfx.Assets;
import dev.gilbertgame.tilegame.gfx.Text;
import dev.gilbertgame.tilegame.properties.Vector2D;
import dev.gilbertgame.tilegame.ui.ClickListener;
import dev.gilbertgame.tilegame.ui.UIImageButton;
import dev.gilbertgame.tilegame.ui.UIManager;
import dev.gilbertgame.tilegame.utils.Utils;
import dev.gilbertgame.tilegame.worlds.Objects;
import dev.gilbertgame.tilegame.worlds.World;

import java.awt.*;

import static java.awt.Color.*;

public class GameState extends State {

    private World world;
    private Objects obj;
    private Animation cursorPulse;
    private Interactables interactables;
    private UIManager uiManager;
    private long timer;
    private String score;
    private int bonus = 0;
    //temp
    private float playerMidX, playerMidY;
    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "TileGame/res/worlds/world1.txt");
        obj = new Objects(handler, "TileGame/res/worlds/object_layer.txt");
        handler.setWorld(world);
        handler.setObjects(obj);
        interactables = new Sword(handler, handler.getPlayer(), 96, 96);
        cursorPulse = new Animation(300, Assets.cursor);
        surround = new Vector2D();
    }
    @Override
    public void update() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        buff();
        if(handler.getGame().gameOver)
            uiManager.update();
        cursorPulse.tick();
        playerMidX = handler.getPlayer().getX()+handler.getPlayer().getWidth()/2f-handler.getCamera().getxOffset();
        playerMidY = handler.getPlayer().getY()+handler.getPlayer().getHeight()/2f-handler.getCamera().getyOffset();
        angle = Utils.calcAngle((int)playerMidX,(int)playerMidY,handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
        surround = Utils.polarConversion(80, angle);
        world.update();
        interactables.update();
        obj.update();
    }
    @Override
    public void render(Graphics g) {
        world.render(g);
        obj.render(g);
        interactables.render(g);
        buffRender();
        //g.drawImage(Utils.rotate(Assets.target, -(int)(angle-45)), (int)playerMidX - 64 + surround.getX(), (int)playerMidY - 64 + surround.getY(), 128, 128, null);
        if(handler.getGame().gameOver){
            g.setColor(new Color(255, 0, 0, 100));
            g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
            Text.drawString(g, "Thanks for Playing!", handler.getWidth()/2, handler.getHeight()/2, true, WHITE, Assets.font28);
            Text.drawString(g, "YOU SURVIVED", handler.getWidth()/2, handler.getHeight()/2, true, WHITE, Assets.font28);
            Text.drawString(g, score, handler.getWidth()/2, handler.getHeight()/2 - 40, true, WHITE, Assets.font80);
            Text.drawString(g, "rounds", handler.getWidth()/2, handler.getHeight()/2 + 70, true, WHITE, Assets.font28);
            uiManager.render(g);
        }else {
            score = Integer.toString((int)(timer/10000.0));
            Text.drawString(g, "Round: " + score, 10, 30, false, YELLOW, Assets.font28);
            Text.drawString(g, "Next Round In: " + String.format("%.1f", 10 - (timer/1000.0)%10) + " s", 10, 50, false, WHITE, Assets.font12);
        }
        g.drawImage(cursorPulse.getCurrentFrame(),
            handler.getMouseManager().getMouseX() - 12,
            handler.getMouseManager().getMouseY() - 12,
            null);
        //g.drawLine((int)(playerMidX), (int)(playerMidY), handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
    }
    public void endScreen(){
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.addObject(new UIImageButton(handler.getWidth()/2f - 94, 400, 188, 64, Assets.exitButton, new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().getFrame().setVisible(false);
                handler.getGame().getFrame().dispose();
                System.exit(0);
            }
        }));
    }
    public void buff(){
        if(timer/10000 > difficulty) {
            handler.getSoundManager().playSound("TileGame/res/sounds/upper.wav", -10.0f);
            obj.getEntityManager().spawnEnemies(0,difficulty,0);
            difficulty++;
            bonus = (int)(3*Math.random()+1);
            double dmg = ((Sword)interactables).getDmg();
            if(bonus == 1)
                handler.getPlayer().setSpeed(handler.getPlayer().getSpeed()+difficulty/20.0f);
            if(bonus == 2 && handler.getPlayer().getHealth() < handler.getPlayer().DEFAULT_HEALTH) {
                handler.getPlayer().setHealth(handler.getPlayer().getHealth() + 2*difficulty / 10.0);
                handler.getPlayer().DEFAULT_HEALTH += difficulty/10.0f;
            }
            if(bonus == 3)
                ((Sword)interactables).setDmg(dmg + difficulty/10.0);
        }
    }
    public void buffRender(){
        if(bonus == 1)
            Text.drawString(handler.getGraphics(), "+ " + String.format("%.2f",difficulty/20.0f) + " SPD!",
                    (int)(handler.getPlayer().getX() + 20 - handler.getCamera().getxOffset()),
                    (int)(handler.getPlayer().getY() + 20 -handler.getCamera().getyOffset()),
                    true, YELLOW, Assets.font12);
        if(bonus == 2)
            Text.drawString(handler.getGraphics(), "+ " + String.format("%.2f", difficulty /10.0f) + " HP!",
                    (int) (handler.getPlayer().getX() + 20 - handler.getCamera().getxOffset()),
                    (int) (handler.getPlayer().getY() + 20 - handler.getCamera().getyOffset()),
                    true, YELLOW, Assets.font12);
        if(bonus == 3)
            Text.drawString(handler.getGraphics(), "+ " + String.format("%.2f", difficulty/10.0f) + " DMG!",
                    (int)(handler.getPlayer().getX() + 20 - handler.getCamera().getxOffset()),
                    (int)(handler.getPlayer().getY() + 20 -handler.getCamera().getyOffset()),
                    true, YELLOW, Assets.font12);
    }
}
