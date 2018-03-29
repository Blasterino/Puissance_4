import java.util.Scanner;

public class Plateau {
    static final Scanner input = new Scanner(System.in);

    private final String COULEUR_UNE = "1";
    private final String COULEUR_DEUX = "2";

    private char plateau[][];
    private int tour;
    private String couleurDebut;
    private String couleurDeuxieme;

    public Plateau() {
        this.plateau = new char[6][7];
        this.tour = 1;
        double alea = random();
        System.out.println(alea);
        if (alea > 0.5) {
            this.couleurDebut = "" + COULEUR_UNE;
            this.couleurDeuxieme = "" + COULEUR_DEUX;
        }
        else {
            this.couleurDebut = "" + COULEUR_DEUX;
            this.couleurDeuxieme = "" + COULEUR_UNE;
        }
        this.initPlateau();
    }

    public Plateau(int ligne, int col) {
        this.plateau = new char[ligne][col];
        this.tour = 1;
        double alea = random();
        System.out.println(alea);
        if (alea > 0.5) {
            this.couleurDebut = "" + COULEUR_UNE;
            this.couleurDeuxieme = "" + COULEUR_DEUX;
        }
        else {
            this.couleurDebut = "" + COULEUR_DEUX;
            this.couleurDeuxieme = "" + COULEUR_UNE;
        }
        this.initPlateau();
    }

    public void initPlateau() {
        for (int i=0; i < this.plateau.length; i++) {
            for (int j=0; j < this.plateau[i].length ; j++) {
                this.plateau[i][j] = 'o';
            }
        }
        this.contenuPlateau();
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
            for (int j=0; j < this.plateau[i].length ; j++) {
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
            if (col < 0 || col > this.plateau[i].length-1) return false;
            if (this.plateau[i][col] == 'o') {
                return true;
            }
        }
        return false;
    }

    private void mettrePion() {
        if (!this.isGameOver()) {
            int col = demanderCol(0);
            col = col-1;

            while (!isPossible(col)) {
                col = demanderCol(1);
                col = col-1;
            }
            if (isPossible(col)) {
                for (int i = this.plateau.length-1; i >= 0; i--) {
                    if (this.plateau[i][col] == 'o') {
                        if (tour % 2 == 0) {
                            this.plateau[i][col] = this.couleurDeuxieme.charAt(0);
                        }
                        if (tour % 2 != 0) {
                            this.plateau[i][col] = this.couleurDebut.charAt(0);
                        }
                        this.contenuPlateau();
                        if (!this.isGameOver()) {
                            if (tour % 2 == 0) {
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
                        /*if (this.isCombination(col)) {

                            if (tour % 2 == 0) {
                                System.out.println("Partie terminée, le joueur " + this.couleurDeuxieme + " a gagné.");
                            }
                            if (tour % 2 != 0) {
                                System.out.println("Partie terminée, le joueur " + this.couleurDebut + " a gagné.");
                            }

                        }*/
                        this.tour++;
                        break;
                    }
                }
            }
        }
    }

    //Teste si des cases sont encore disponibles
    private boolean isGameOver() {
        for (int i=0; i < this.plateau.length; i++) {
            for (int j=0; j < this.plateau[i].length ; j++) {
                if (plateau[i][j] == 'o') return false;
            }
        }
        return true;
    }

    private boolean isCombination(int col) {
        return true;
    }

    public void jouer() {
        while (!this.isGameOver()) {
            this.mettrePion();
        }
    }

    private double random() {
        return Math.random();
    }
}