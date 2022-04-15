package dev.gilbertgame.tilegame.states;

import dev.gilbertgame.tilegame.Game;
import dev.gilbertgame.tilegame.Handler;
import dev.gilbertgame.tilegame.display.Display;
import dev.gilbertgame.tilegame.gfx.Animation;
import dev.gilbertgame.tilegame.gfx.Assets;
import dev.gilbertgame.tilegame.ui.ClickListener;
import dev.gilbertgame.tilegame.ui.UIImageButton;
import dev.gilbertgame.tilegame.ui.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;
    private Animation animLoading;
    private Animation cursorPulse;

    public MenuState(Handler handler){
        super(handler);
        animLoading = new Animation(200, Assets.player_still);
        cursorPulse = new Animation(300, Assets.cursor);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.addObject(new UIImageButton(handler.getWidth()/2 - 128, 300, 256, 64, Assets.startButton, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().gameState);
                lastTime = System.currentTimeMillis();
                handler.getSoundManager().stopAll();
                handler.getSoundManager().playMusic("res/sounds/cute-piano.wav", -20.0f);
            }
        }));
        uiManager.addObject(new UIImageButton(handler.getWidth()/2 - 94, 400, 188, 64, Assets.exitButton, new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().getFrame().setVisible(false);
                handler.getGame().getFrame().dispose();
                System.exit(0);
            }
        }));
    }
    public void update(){
        uiManager.update();
        animLoading.tick();
        cursorPulse.tick();
    }
    public void render(Graphics g){
        g.setColor(Color.orange);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        g.drawImage(animLoading.getCurrentFrame(), handler.getWidth()/2-128, 50, 256, 256, null );
        uiManager.render(g);
        g.drawImage(cursorPulse.getCurrentFrame(),
                handler.getMouseManager().getMouseX() - 12,
                handler.getMouseManager().getMouseY() - 12,
                null);
    }

    @Override
    public void endScreen() {}
}
