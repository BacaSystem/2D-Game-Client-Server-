package game;

import game.entities.Ship;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Klasa odpowiedzialna za wykrywanie kolizji
 */
public class Collision {

    /** referencja do obiektu statku gracza */
    private Ship ship;
    /** referencja do obiektu terenu */
    private Shape terrain;
    /** referencja do obiektu lądowiska */
    private Shape landing;

    /**
     * Konstruktor klasy, przypisuje referencje za pomocą podanych argumentów
     * @param ship obiekt statka gracza
     * @param terrain obiekt terenu poziomu
     * @param landing obiekt lądowiska poziomu
     */
    public Collision(Ship ship, Shape terrain, Shape landing){
        this.ship = ship;
        this.terrain = terrain;
        this.landing = landing;
    }

    /**
     * Metoda sprawdzająca czy nastąpiła fatalna kolizja - czyli gracz zderzył się z terenem, wyleciał poza obszar gry lub wylądował ze zbyt dużą prędkością
     * @return prawda jeśli taka kolizja nastąpiła, fałsz jeśli nie
     */
    public boolean detectCollisionTerrain(){
        if((terrain.intersects(ship.collider) || ((landing.intersects(ship.collider)) && (Math.abs(ship.speedY) >= ship.maxLandingSpeed || Math.abs(ship.speedX) >= ship.maxLandingSpeed)))
        || (ship.y > 700))
            return true;
        else
            return false;
    }

    /**
     * Meotda sprawdzająca czy gracz wylądował poprawnie
     * @return prawda jeśli lądowanie było poprawne, fałsz gdy nie
     */
    public boolean detectWin(){
        if((landing.intersects(ship.collider)) && ((Math.abs(ship.speedY) < ship.maxLandingSpeed) && (Math.abs(ship.speedX) < ship.maxLandingSpeed))) {
            return true;
        }
        else
            return false;
    }
}
