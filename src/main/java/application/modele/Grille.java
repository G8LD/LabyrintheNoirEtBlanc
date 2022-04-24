package application.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Grille {
    private int width;
    private int height;
    private ArrayList<Sommet> listeSommets;
    private Personnage perso;

    public Grille(int width, int height) {
        this.width = width;
        this.height = height;
        listeSommets = new ArrayList<>();
        perso = new Personnage(this, 3,0);
        construire();
    }

    private void construire() {
        InputStream is = getClass().getResourceAsStream("/application/map.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        char c;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                try {
                    c = (char) br.read();
                    if (c == '0')
                        listeSommets.add(new Sommet(i, j, false));
                    else
                        listeSommets.add(new Sommet(i, j, true));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                br.read(); br.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Sommet getSommet(int x, int y) {
        for (Sommet s : listeSommets)
            if (s.getX() == x && s.getY() == y)
                return s;
        return null;
    }

    public boolean dansGrille(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean estUnObstacle(int x, int y) {
        Sommet s = getSommet(x,y);
        if (s == null)
            return true;
        return s.isUnObstacle();
    }

    //region Getter & Setter
    public ArrayList<Sommet> getListeSommets() {
        return listeSommets;
    }

    public Personnage getPerso() {
        return perso;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    //endregion
}
