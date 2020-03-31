package game;

import game.entities.Ship;

import java.awt.*;
import java.awt.geom.Rectangle2D;


public class Collision {

    private Ship ship;
    private Shape terrain;
    private Shape landing;

    public Collision(Ship ship, Shape terrain, Shape landing){
        this.ship = ship;
        this.terrain = terrain;
        this.landing = landing;
    }

    public boolean detectCollisionTerrain(){
        if(terrain.intersects(ship.collider) || ((landing.intersects(ship.collider)) && (Math.abs(ship.speedY) >= ship.maxLandingSpeed || Math.abs(ship.speedX) >= ship.maxLandingSpeed)))
            return true;
        else
            return false;
    }

    public boolean detectWin(){
        if((landing.intersects(ship.collider)) && ((Math.abs(ship.speedY) < ship.maxLandingSpeed) && (Math.abs(ship.speedX) < ship.maxLandingSpeed))) {
            System.out.println("WON");
            return true;
        }
        else
            return false;
    }
}
