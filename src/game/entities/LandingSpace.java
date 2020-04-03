package game.entities;

import java.awt.*;

/**
 * Klasa lądowiska, reprezentuje lądowisko dla statku
 */
public class LandingSpace {

    /** zmienna określająca kształt lądowiska */
    private Shape landing;

    /**
     * Konstruktor klasy, tworzy lądowisko ze zbioru podanych współrzędnych
     * @param xVertecies współrzędne x kolejnych wierchołków
     * @param yVertecies współrzędne y kolejnych wierzchołków
     */
    public LandingSpace(int[] xVertecies, int[] yVertecies){
        landing = new Polygon(xVertecies, yVertecies, xVertecies.length);
    }

    /**
     * Metoda zwracająca kształt lądowiska
     * @return wielokąt reprezentujący lądowisko
     */
    public Shape getLandingSpaceCollider(){
        return landing;
    }
}
