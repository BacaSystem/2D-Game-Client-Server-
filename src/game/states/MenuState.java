package game.states;

import game.controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuState extends State {

    BufferedImage menuText;

    public MenuState(StatesManager manager){
        super(manager);
        init();
    }

    private void init(){
        try {
            menuText = ImageIO.read(new File("img/menu_text.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void input(KeyHandler key) {
        if(key.enter.down)
            manager.add(StatesManager.PLAY);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(menuText, 400,100, null);
    }
}
