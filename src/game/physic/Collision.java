package game.physic;

import game.entities.LandingSpace;
import game.entities.Meteor;
import game.entities.Ship;
import game.entities.Terrain;

import java.util.List;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Klasa odpowiedzialna za wykrywanie kolizji
 */
public class Collision {

    /** referencja do obiektu statku gracza
     * @see Ship */
    private Ship ship;
    /** referencja do obiektu terenu
     * @see Terrain*/
    private Terrain terrain;
    /** referencja do obiektu lądowiska
     * @see LandingSpace */
    private LandingSpace landing;

    private List<Meteor> meteors;

    /**
     * Konstruktor klasy, przypisuje referencje za pomocą podanych argumentów
     * @param ship obiekt statka gracza
     * @param terrain obiekt terenu poziomu
     * @param landing obiekt lądowiska poziomu
     * @see Ship
     * @see Terrain
     * @see LandingSpace
     */
    public Collision(Ship ship, Terrain terrain, LandingSpace landing, List<Meteor> meteors){
        this.ship = ship;
        this.terrain = terrain;
        this.landing = landing;
        this.meteors = meteors;
    }

    /**
     * Metoda sprawdzająca czy nastąpiła fatalna kolizja - czyli gracz zderzył się z terenem, wyleciał poza obszar gry lub wylądował ze zbyt dużą prędkością
     * @return prawda jeśli taka kolizja nastąpiła, fałsz jeśli nie
     */
    public boolean detectFatalColission(){
        for (Meteor meteor : meteors) {
            if(meteor.getCollider().intersects(ship.collider))
                return true;
        }
            if ((terrain.getTerrainCollider().intersects(ship.collider) || ((landing.getLandingSpaceCollider().intersects(ship.collider)) && (Math.abs(ship.speedY) >= ship.maxLandingSpeed || Math.abs(ship.speedX) >= ship.maxLandingSpeed)))
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
        if((landing.getLandingSpaceCollider().intersects(ship.collider)) && ((Math.abs(ship.speedY) < ship.maxLandingSpeed) && (Math.abs(ship.speedX) < ship.maxLandingSpeed))) {
            return true;
        }
        else
            return false;
    }
}
