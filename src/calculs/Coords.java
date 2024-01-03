package calculs;

/**
 * classe de coordonnées x y 
 */
public class Coords {
    private int x;
    private int y;

    /**
     * constructeur
     */
    public Coords() {}

    /**
     * constructeur
     * @param x absisse
     * @param y ordonnées
     */
    public Coords (int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter x
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * setter x
     * @param x absisse
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getter y 
     * @return l'ordonnée
     */
    public int getY() {
        return this.y;
    }

    /**
     * setter y 
     * @param y ordonnée
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * méthode d'affichage pour debug
     */
    @Override
    public String toString() {
        return x + " " + y;
    }

    /**
     * vérification que deux coordonnées ne se chevauchent pas
     * @param xTest absisse à tester
     * @param yTest ordonnée à tester
     * @return true si les coordonnées ne se chevauchent pas
     */
    public boolean isValid(int xTest, int yTest) {
        if (x == xTest & y == yTest) return false;
        return true;
    }
}
