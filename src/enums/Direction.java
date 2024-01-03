package enums;

/**
 * directions possibles de déplacement du serpent
 */
public enum Direction {
    N("N"),
    S("S"),
    E("E"),
    W("W");

    /**
     * direction
     */
    private final String name;       

    /**
     * constructeur
     * @param s direction
     *  
     */ 
    private Direction(String s) {
        name = s;
    }

    /**
     * méthode de vérification de chaîne de caractère
     * @param otherName quelle direction
     * @return la vérification
     */
    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    /**
     * direction
     * @return direction
     */
    public String toString() {
        return this.name;
    }
}
