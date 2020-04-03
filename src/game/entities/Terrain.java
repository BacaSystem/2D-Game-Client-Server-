package game.entities;

import java.awt.*;

/**
 * Klasa terenu, reprezentująca powierzchnie planety
 */
public class Terrain {

    /** zmienna określająca kształt planety */
    private Shape terrain;

    /**
     * Konstruktor klasy, tworzy teren ze zbioru podanych współrzędnych
     * @param xVertecies współrzędne x kolejnych wierchołków
     * @param yVertecies współrzędne y kolejnych wierzchołków
     */
    public Terrain(int[] xVertecies, int[]yVertecies){
        terrain = new Polygon(xVertecies, yVertecies, xVertecies.length);
    }

    /**
     * Metoda zwracająca kształt terenu
     * @return wielokąt reprezentujący teren
     */
    public Shape getTerrainCollider(){
        return terrain;
    }
}
