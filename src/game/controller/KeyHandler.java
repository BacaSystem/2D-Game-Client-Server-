package game.controller;


import game.Game;
import game.window.GameWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener {
    //inner class representing single Key.
    public class Key {
        public boolean down;
        private int numTimesPressed = 0;

        public void toggle(boolean pressed){
            this.down = pressed;
            if(down)
                numTimesPressed++;
        }

        public boolean down(){
            return this.down;
        }

        public boolean released(){
            return !this.down;
        }

        public int getNumTimesPressed(){
            return numTimesPressed;
        }
    }

    //public static List<Key> keys = new ArrayList<Key>();

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key space = new Key();
    public Key enter = new Key();
    public Key escape = new Key();
    public Key cheat_0 = new Key();

    public KeyHandler(Game game){
        game.addKeyListener(this);
        game.setFocusable(true);
    }

    public void toggle(KeyEvent e, boolean pressed){
        if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_SPACE) space.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_0) cheat_0.toggle(pressed);

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() != KeyEvent.VK_SPACE)
            toggle(keyEvent, true);
        else{
            toggle(keyEvent, !space.down);
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() != KeyEvent.VK_SPACE)
            toggle(keyEvent, false);
    }
}
