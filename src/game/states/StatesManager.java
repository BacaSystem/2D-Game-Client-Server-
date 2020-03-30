package game.states;

import game.Constant.DefaultGameSettings;
import game.controller.KeyHandler;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class StatesManager {

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;

    private ArrayList<State> states;

    public StatesManager(){
        states = new ArrayList<State>();

        states.add(new MenuState(this));
    }

    public void add(int state){
        if(states.size() > 0) {
            states.remove(0);
        }

        if(state == PLAY) {
            states.add(new PlayState(this));
        }

        if(state == MENU) {
            states.add(new MenuState(this));
        }
        /**
        if(state == PAUSE) {
            states.add(new PauseState(this));
        }
        if(state == GAMEOVER) {
            states.add(new GameOverState(this));
        }
         **/
    }

    public void update(){
        for (State state : states) {
            state.update();
        }
    }

    public void input( KeyHandler key){
        for (State state : states) {
            state.input(key);
        }
    }

    public void render(Graphics2D g){
        for (State state : states) {
            state.render(g);
        }
    }

}
