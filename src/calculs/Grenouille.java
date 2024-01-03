package calculs;

import java.util.LinkedList;
import config.Config;

/**
 * la classe des grenouilles
 */
public class Grenouille {
    
    /**
     * coordonnées des grenouilles
     */
    private LinkedList<Coords> coords;

    /**
     * nombre de grenouilles
     */
    private int nbGrenouilles = 2;

    /**
     * largeur de la fenêtre
     */
    private final int width = Config.width;

    /**
     * hauteur de la fenêtre
     */
    private final int height = Config.height;

    /**
     * dimension d'une grenouille (d'une case)
     */
    private final int dim = Config.dim;

    /**
     * positions des grenouilles
     */
    private LinkedList<Coords> posGrenouille = new LinkedList<Coords>();

    /**
     * constructeur
     * @param coords les coordonnées du serpent pour l'initialisation des coordonnées des grenouilles
     */
    public Grenouille(LinkedList<Coords> coords) {
        this.coords = coords;
        initPos(nbGrenouilles);
    }

    /**
     * initialisation des coordonnées des grenouilles en fonction de la postion du serpent
     * @param nb nombre de grenouilles
     */
    private void initPos(int nb) {
        int nbCasesX = (int) width / dim;
        int nbCasesY = (int) height / dim;
        
        for (int i = 0; i < nb; i++) {
            boolean posGrenouilleOk = false;
            int xInit = 0;
            int yInit = 0;
            while (!posGrenouilleOk) {
                xInit = (int) (Math.random() * nbCasesX) * dim;
                yInit = (int) (Math.random() * nbCasesY) * dim;
                boolean isValid = true;
                for (Coords c: coords) {
                    if (c.isValid(xInit, yInit) == false) {
                        isValid = false;
                    }
                }
                if (isValid == true) {
                    posGrenouilleOk = true;
                }
            }
            posGrenouille.add(new Coords(xInit, yInit));
        }
    }

    /**
     * réinitialiser les positions des grenouilles
     * @param g les coordonnées du serpent
     * @return true si la grenouille n'est pas mangée
     */
    public boolean addGrenouille(Coords g) {
        for (int i = 0; i < posGrenouille.size(); i++) {
            if (g.isValid(posGrenouille.get(0).getX(), posGrenouille.get(i).getY())) {
                posGrenouille = new LinkedList<Coords>();
                initPos(nbGrenouilles++);
                return false;
            }
        }
        return true;
    }

    /**
     * getter positions grenouilles
     * @return les positions des grenouilles
     */
    public LinkedList<Coords> getPosGrenouille() {
        return posGrenouille;
    }

}
