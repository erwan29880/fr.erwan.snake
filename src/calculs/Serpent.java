package calculs;

import java.util.LinkedList;
import java.util.Random;

import config.Config;
import enums.Direction;

/**
 * classe du serpent
 */
public class Serpent {
    
    /**
     * dimension d'un anneau
     */
    private final int dim = Config.dim;

    /**
     * largeur de la fenêtre
     */
    private final int width = Config.width;

    /**
     * hauteur de la fenêtre
     */
    private final int height = Config.height;

    /**
     * nombre d'anneaux du serpent
     */
    private int nbAnneaux;

    /**
     * liste des coordonnées du serpent
     */
    private LinkedList<Coords> coords = new LinkedList<Coords>(); 

    /**
     * absisse initial de la tête
     */
    private int xInit;

    /**
     * ordonnée initiale de la tête
     */
    private int yInit;

    /**
     * direction du serpent
     */
    private Direction dirInit;

    /**
     * coordonnées d'une grenouille mangée
     */
    private Coords grenouilleMangee = null;

    /**
     * constructeur
     */
    public Serpent() {
        this.nbAnneaux = 3;
        init();
    }

    /**
     * initialisation pseudo aléatoire de la position du serpent. Le serpent ne doit pas sortir de la fenêtre
     * lors de l'initialisation
     */
    private void init() {
        Direction[] dirs = new Direction[]{Direction.N, Direction.S, Direction.S, Direction.E};
        int nbCasesX = (int) width / dim;
        int nbCasesY = (int) height / dim;

        // initialisation x, y, direction
        xInit = (int) (nbAnneaux + (Math.random() * ((nbCasesX - nbAnneaux) - nbAnneaux)) ) * dim;
        yInit = (int) (nbAnneaux + (Math.random() * ((nbCasesY - nbAnneaux) - nbAnneaux)) ) * dim;
        Random r = new Random();
        dirInit = dirs[r.nextInt(4)];

        // inversion de la direction pour l'initialisation
        Direction dirInitInv = invDir(dirInit);

        // ajout de la tête
        Coords tete = new Coords(xInit, yInit);
        coords.add(tete);

        // ajout des anneaux
       
        for (int i = 0; i < nbAnneaux - 1; i++) {
            Coords prev = coords.get(i);
            Case corps = new Case(prev.getX(), prev.getY(), dirInitInv);
            coords.add(corps.newPos());
        }
    }


    /**
     * faire avancer le serpent. 
     * La tête est avancée, le dernier anneau enlevé.
     * Vérification que le serpent ne se mord pas la queue.
     * Vérification que le serpent mange une grenouille.
     * @params posGrenouille les coordonnées des grenouilles
     * @return 1 pour game over, 2 si le serpent a mangé une grenouille, sinon 0
     */
    public int avance(LinkedList<Coords> posGrenouille) {
        Case c = new Case(coords.get(0).getX(), coords.get(0).getY(), dirInit);
        coords.removeLast();
        coords.addFirst(c.newPos());
        boolean check = checkSerpentPos();
        if (check == false) {
            return 1;
        }
        if (checkGrenouillePos(posGrenouille) == false) {
            return 2;
        }
        if (checkBorders() == true ){
            return 1;
        }
        return 0;
    }

    /**
     * vérification que le serpent ne se mord pas la queue
     * @return false si le serpent se mord la queue
     */
    public boolean checkSerpentPos() {
        Coords tete = new Coords(coords.get(0).getX(), coords.get(0).getY());
        int siz = coords.size();
        for (int i = 1 ; i < siz; i++) {
            if (tete.isValid(coords.get(i).getX(), coords.get(i).getY()) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * vérifie si le serpent mange une grenouille.
     * Ajoute un anneau au serpent si le serpent mange une grnouille.
     * @param posGrenouille les positions des grenouilles
     * @return false si le serpent a mangé une grenouille
     */
    public boolean checkGrenouillePos(LinkedList<Coords> posGrenouille) {
        Coords tete = new Coords(coords.get(0).getX(), coords.get(0).getY());
        int siz = posGrenouille.size();
        for (int i = 0 ; i < siz; i++) {
            if (tete.isValid(posGrenouille.get(i).getX(), posGrenouille.get(i).getY()) == false) {
                grenouilleMangee = new Coords(posGrenouille.get(i).getX(), posGrenouille.get(i).getY());
                coords.add(new Case(coords.getLast().getX(), coords.getLast().getY(), invDir(dirInit)).newPos());
                return false;
            }
        }
        return true;
    }

    /**
     * vérifié que le serpent ne se cogne pas la tête contre les murs
     * @return true si le serpent se mange un mur
     */
    public boolean checkBorders() {
       int xTest = coords.get(0).getX();
       int yTest = coords.get(0).getY();
       if (xTest == 0 - dim | xTest == width - dim | yTest == 0 - dim | yTest == height - dim) {
        return true;
       }
       return false;
    }

    /**
     * ajouter un anneau au serpent
     */
    public void addAnneau() {
        nbAnneaux++; 
    }

    /**
     * inversion de la direction pour l'initialisation du serpent
     * @param d la direction
     * @return la nouvelle direction
     */
    public Direction invDir(Direction d) {
        switch(dirInit) {
            case N:
                return Direction.S;
            case S:
                return Direction.N;
            case E:
                return Direction.W;
            case W:
                return Direction.E;
            default:
                return Direction.N;
        }
    }


    /**
     * getter coords
     * @return les coordonnées du serpent
     */
    public LinkedList<Coords> getCoords() {
        return coords;
    }

    /**
     * getter nombre d'anneaux
     * @return le nombre d'anneaux
     */
    public int getNbAnneaux() {
        return nbAnneaux;
    }

    /**
     * setter nombre d'anneaux
     * @param nb le nouveau nombre d'anneaux
     */
    public void setNbAnneaux(int nb) {
        this.nbAnneaux = nb;
    }

    /**
     * getter direction
     * @return la direction du serpent
     */
    public Direction getInitDir() {
        return dirInit;
    }

    /**
     * setter direction
     * @param dir la nouvelle direction du serpent
     */
    public void setDir(Direction dir) {
        dirInit = dir;
    }
    
    /**
     * méthode pour les tests
     * @return
     */
    public boolean testInit() {
        return coords.size() == 3;
    }

    /**
     * position de la grenouille mangée
     * @return la position de la grenouille mangée
     */
    public Coords getGrenouilleMangee() {
        return grenouilleMangee;
    }
}
