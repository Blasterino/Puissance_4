import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Plateau {
    static final Scanner input = new Scanner(System.in);

    private final String COULEUR_UNE = "Uranus";
    private final String COULEUR_DEUX = "Saturne";

    private int ligne,colonne;
    private char plateau[][];
    private int tour;
    private int puissancePlateau;
    private String couleurDebut;
    private String couleurDeuxieme;
    private BufferedImage[] btab;
    private ImageIcon[] itab;

    public Plateau() {}

    public Plateau(int puissancePlateau) {
        this.plateau = new char[6][7];
        this.tour = 1;
        this.puissancePlateau = puissancePlateau;

        this.btab = new BufferedImage[3];
        this.itab = new ImageIcon[3];
        try{
            btab[0] = ImageIO.read(new File("C:/Users/basti/OneDrive/Documents/GitHub/Puissance_4/src/img1.png"));
            btab[1] = ImageIO.read(new File("C:/Users/basti/OneDrive/Documents/GitHub/Puissance_4/src/img2.png"));
            btab[2] = ImageIO.read(new File("C:/Users/basti/OneDrive/Documents/GitHub/Puissance_4/src/img3.png"));

            itab[0] = new ImageIcon(btab[0]);
            itab[1] = new ImageIcon(btab[1]);
            itab[2] = new ImageIcon(btab[2]);

            itab[0] = new ImageIcon(itab[0].getImage().getScaledInstance(100,100,BufferedImage.SCALE_SMOOTH));
            itab[1] = new ImageIcon(itab[1].getImage().getScaledInstance(100,100,BufferedImage.SCALE_SMOOTH));
            itab[2] = new ImageIcon(itab[2].getImage().getScaledInstance(100,100,BufferedImage.SCALE_SMOOTH));
        } catch(IOException ioe) {
            System.out.println("Chargement image impossible");
        }

        this.definirOrdreDeJeu();
        this.initPlateau();
    }

    public Plateau(int ligne, int col, int puissancePlateau) {
        this.plateau = new char[ligne][col];
        this.tour = 1;
        this.puissancePlateau = puissancePlateau;
        this.definirOrdreDeJeu();
        this.initPlateau();
    }

    private void definirOrdreDeJeu() {
        if (random() > 0.5) {
            this.couleurDebut = "" + COULEUR_UNE;
            this.couleurDeuxieme = "" + COULEUR_DEUX;
        }
        else {
            this.couleurDebut = "" + COULEUR_DEUX;
            this.couleurDeuxieme = "" + COULEUR_UNE;
        }
    }

    public void initPlateau() {
        for (int i=0; i < this.plateau.length; i++) {
            for (int j=0; j < this.plateau[0].length ; j++) {
                this.plateau[i][j] = 'o';
            }
        }
        //this.contenuPlateau();
        if (this.couleurDebut.equals("couleur1")) {
            System.out.println("Tour " + this.tour);
            System.out.println("Le joueur avec les pions de couleur " + this.couleurDeuxieme + " commence");
        }
        else {
            System.out.println("Tour : " + this.tour);
            System.out.println("Le joueur avec les pions de couleur " + this.couleurDebut + " commence");
        }
    }

    private void contenuPlateau() {
        for (int i=0; i < this.plateau.length; i++) {
            for (int j=0; j < this.plateau[0].length ; j++) {
                System.out.print(" " + this.plateau[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private int demanderCol(int err) {
        //erreur 0 pour fonctionnement normal
        //erreur 1 pour colonne pleine
        //-1 si erreur d'exec
        if (err == 0) {
            System.out.println("Veuillez donner le numéro de colonne où jouer le pion :");
            return input.nextInt();
        }
        else if (err == 1) {
            System.out.println("Colonne pleine, veuillez donner une autre colonne :");
            return input.nextInt();
        }
        return -1;
    }

    private boolean isPossible(int col) {
        for (int i = (this.plateau.length)-1; i >= -1; i--) {
            if (i == -1) return false;
            if (col < 0 || col > this.plateau[0].length-1) return false;
            if (this.plateau[i][col] == 'o') {
                return true;
            }
        }
        return false;
    }

    public void mettrePion(int col) {
        /*if (!this.isGameOver()) {
            int col = demanderCol(0);
            col = col-1;

            while (!isPossible(col)) {
                col = demanderCol(1);
                col = col-1;
            }
        */
            if (isPossible(col)) {
                for (int i = this.plateau.length-1; i >= 0; i--) {
                    if (this.plateau[i][col] == 'o') {
                        int indice = i; //indice pour check la combinaison
                        if (this.tour % 2 == 0) {
                            this.plateau[i][col] = this.couleurDeuxieme.charAt(0);
                        }
                        if (this.tour % 2 != 0) {
                            this.plateau[i][col] = this.couleurDebut.charAt(0);
                        }
                        //this.contenuPlateau();

                        //insérer ici le test de combinaison, en plus du test de cases libres
                        if (this.isCombination(col, indice)) {
                            if (this.tour % 2 == 0) {
                                System.out.println("Partie terminée, le joueur " + this.couleurDeuxieme + " a gagné.");
                                System.exit(0);
                            }
                            if (this.tour % 2 != 0) {
                                System.out.println("Partie terminée, le joueur " + this.couleurDebut + " a gagné.");
                                System.exit(0);
                            }
                        }


                        if (!this.isGameOver()) {
                            if (this.tour % 2 == 0) {
                                System.out.println("Tour " + this.tour);
                                System.out.println("Au tour du joueur avec les pions " + this.couleurDebut + ".");
                            } else {
                                System.out.println("Tour " + this.tour);
                                System.out.println("Au tour du joueur avec les pions " + this.couleurDeuxieme + ".");
                            }
                        }
                        else {
                            System.out.println("Plus aucune case n'est libre, fin de la partie.");
                        }
                        this.tour++;
                        break;
                    }
                }
            }
        }


    //Teste si des cases sont encore disponibles
    private boolean isGameOver() {
        for (int i=0; i < this.plateau.length; i++) {
            for (int j=0; j < this.plateau[0].length ; j++) {
                if (plateau[i][j] == 'o') return false;
            }
        }
        return true;
    }

    //En cours
    private boolean isCombination(int col, int ligne) {
        int compteurCombo = 0;

        //Ligne droite
        int i = 0;
        boolean estLineaire = true;
        while (i < this.puissancePlateau && estLineaire) {
            if (col+i  >= this.plateau[0].length)
                estLineaire = false;
            else {
                if (this.plateau[ligne][col] == this.plateau[ligne][col + i]) {
                    compteurCombo++;
                } else
                    estLineaire = false;
            }
            i++;
        }

        //Ligne gauche
        i = 1;
        estLineaire = true;
        while (i < this.puissancePlateau && estLineaire && compteurCombo < this.puissancePlateau) {
            if (col-i  < 0)
                estLineaire = false;
            else {
                if (this.plateau[ligne][col] == this.plateau[ligne][col-i]) {
                    compteurCombo ++;
                }
                else
                    estLineaire = false;
            }
            i++;
        }

        if (compteurCombo >= this.puissancePlateau) return true;

        return false;
    }

    /*
    public void jouer() {
        while (!this.isGameOver()) {
            this.mettrePion();
        }
    }
    */

    public String getCOULEUR_UNE() {
        return COULEUR_UNE;
    }

    public String getCOULEUR_DEUX() {
        return COULEUR_DEUX;
    }

    public char[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(char[][] plateau) {
        this.plateau = plateau;
    }

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

    public int getPuissancePlateau() {
        return puissancePlateau;
    }

    public void setPuissancePlateau(int puissancePlateau) {
        this.puissancePlateau = puissancePlateau;
    }

    public String getCouleurDebut() {
        return couleurDebut;
    }

    public void setCouleurDebut(String couleurDebut) {
        this.couleurDebut = couleurDebut;
    }

    public String getCouleurDeuxieme() {
        return couleurDeuxieme;
    }

    public void setCouleurDeuxieme(String couleurDeuxieme) {
        this.couleurDeuxieme = couleurDeuxieme;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public BufferedImage[] getBtab() {
        return btab;
    }

    public void setBtab(BufferedImage[] btab) {
        this.btab = btab;
    }

    public ImageIcon[] getItab() {
        return itab;
    }

    public void setItab(ImageIcon[] itab) {
        this.itab = itab;
    }

    private double random() {
        return Math.random();
    }
}