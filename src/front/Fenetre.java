package front;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.LinkedList;

import calculs.Coords;
import calculs.Grenouille;
import calculs.Serpent;
import config.Config;
import enums.Direction;

/**
 * fenêtre principale
 */
public class Fenetre extends JFrame implements KeyListener{
    
    /**
     * largeur de la fenêtre
     */
    private final int width = Config.width;

    /**
     * hauteur de la fenêtre
     */
    private final int height = Config.height;
    
    /**
     * classe du serpent
     */
    private Serpent serpent;

    /**
     * classe de la grenouille
     */
    private Grenouille grenouille;

    /**
     * positions des grenouilles
     */
    private LinkedList<Coords> posGrenouille;

    /**
     * constructeur
     */
    public Fenetre() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(width, height);
        setLayout(null);
        setBounds(0,0, width, height);
        addKeyListener(this);

        // icone et titre
        setTitle("snake");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Config.path));

        serpent = new Serpent();
        grenouille = new Grenouille(serpent.getCoords());
        posGrenouille = grenouille.getPosGrenouille();

        // espace de jeu
        Container c = getContentPane();
        Pan pan = new Pan();
        c.add(pan);
    }

    /**
     * event clavier
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (serpent.getInitDir() != Direction.S) {
                serpent.setDir(Direction.N);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (serpent.getInitDir() != Direction.N) {
                serpent.setDir(Direction.S);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (serpent.getInitDir() != Direction.E) {
                serpent.setDir(Direction.W);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (serpent.getInitDir() != Direction.W) {
                serpent.setDir(Direction.E);
            }
        }
    }

    /**
     * event clavier
     */
    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * event clavier
     */
    @Override
    public void keyTyped(KeyEvent e) {}


    /**
     * classe du l'espace de jeu
     */
    class Pan extends JPanel {
        
        /**
         * dimension d'un anneau
         */
        private final int dim = Config.dim;

        /**
         * timer pour rafraîchir l'affichage
         */
        private Timer timer;

        /**
         * delai du timer
         */
        private int delay = 300;

        /**
         * nombre d'anneaux pour augmenter la vitesse de jeu
         */
        private int nbSerpents = 3;

        /**
         * condition pour afficher game over
         */
        private boolean isWinning = true;
    

        /**
         * constructeur
         */
        Pan () {
            setLayout(null);
            setBounds(0, 0, width, height);
            gestionTimer();
        }
    
        /**
         * boucle temporisée de jeu
         */
        protected void gestionTimer() {
            if (timer != null) {
                if (timer.isRunning()) timer.stop();
            }
            timer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int check = serpent.avance(grenouille.getPosGrenouille());
                    if (check == 1) {
                        isWinning = false;
                        repaint();
                        timer.stop();
                    } else if (check == 2) {
                        Coords g = serpent.getGrenouilleMangee();
                        grenouille.addGrenouille(g);
                        posGrenouille = grenouille.getPosGrenouille();
                    }
                    
                    // diminuer le délai
                    int siz = serpent.getCoords().size() - nbSerpents;
                    int delayMod = siz * 2;
                    timer.setDelay(delay - delayMod);
                    
                    repaint();
                }
            });
            timer.start();
        }
    
    
        /**
         * affichage des éléments
         * @param g graphics
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (isWinning) {
                drawSerpent(g);
                drawGrenouille(g);
            } else {
                drawGameOver(g);
            }
        }
    
     
        /**
         * affichage du serpent
         * @param g graphics
         */
        private void drawSerpent(Graphics g) {
            LinkedList<Coords> coords = serpent.getCoords();
            Graphics2D g2 = (Graphics2D) g;  // parsage en 2d pour utilisation de double pour affichage texte 
            int i = 0;
            for (Coords c: coords) {
                if (i == 0) {
                    g2.setPaint(Color.RED);
                    i++;
                } else  {
                    g2.setPaint(Color.blue);
                }
                g.drawOval(c.getX(), c.getY(), dim, dim);
                g.fillOval(c.getX(), c.getY(), dim, dim);
            }
        }

        /**
         * affichage des grenouilles
         * @param g graphics
         */
        private void drawGrenouille(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;  // parsage en 2d pour utilisation de double pour affichage texte 
            
            g2.setPaint(Color.GREEN);
            for (Coords c: posGrenouille) {
                g.drawOval(c.getX() + dim*2/6, c.getY()+dim*2/6, dim*2/3, dim*2/3);
                g.fillOval(c.getX() + dim*2/6, c.getY() + dim*2/6, dim*2/3, dim*2/3);
            }
        }

        /**
         * affichage de fin de partie
         * @param g graphics
         */
        private void drawGameOver(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            String txt = "GAME OVER";
            g2.setPaint(Color.RED);
            g2.setFont(new Font("arial", dim, dim));
            int textWidth = g.getFontMetrics().stringWidth(txt);
            g2.drawString(txt, width/2 - textWidth/2 - dim/2, height/2 - dim);
        }
    }
}

