package calculs;

import config.Config;
import enums.Direction;

/**
 * classe d'une case de jeu
 */
public class Case {

    /**
     * dimension de la case
     */
    private final int dim = Config.dim;

    /**
     * absisse de la case
     */
    private int x;

    /**
     * ordonnées de la case
     */
    private int y;

    /**
     * direction du serpent
     */
    private Direction direction;

    /**
     * constructeur
     * @param x absisse 
     * @param y ordonnée
     * @param direction direction
     */
    public Case(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    /**
     * calcul de la nouvelle position d'un anneau du serpent en fonction de 
     * ses coordonnées actuelles et de sa direction
     * @return la nouvelle position d'un anneau
     */
    public Coords newPos() {
        Coords c = new Coords();
        switch(direction) {
            case N:
                c.setX(x);
                c.setY(y - dim);
                break;
            case S:
                c.setX(x);
                c.setY(y + dim);
                break;
            case W:
                c.setX(x - dim);
                c.setY(y);
                break;
            case E:
                c.setX(x + dim);
                c.setY(y);
                break;
        }
        return c;
    }


    /**
     * getter dimension
     * @return la dimension de la case
     */
    public int getDim() {
        return dim;
    }

}
