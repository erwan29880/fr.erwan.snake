package config;

/**
 * variables globales
 */
public class Config {
    /**
     * largeur de la fenêtre
     */
    public static final int width = 400;

    /**
     * heuteur de la fenêtre
     */
    public static final int height = 400;

    /**
     * dimension d'un anneau
     */
    public static final int dim = 20;

    /**
     * chemin d'accès de l'icône
     */
    public static final String path = new StringBuilder()
        .append(System.getProperty("user.dir"))
        .append(System.getProperty("file.separator"))
        .append("snake.jpg")
        .toString();
}
