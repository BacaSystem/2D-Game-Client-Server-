package game.states;

import game.controller.KeyHandler;

import java.awt.*;

public abstract class State {

    public StatesManager manager;

    public State(StatesManager manager){
        this.manager = manager;
    }

    public abstract void update();
    public abstract void input(KeyHandler key);
    public abstract void render(Graphics2D g);

}

