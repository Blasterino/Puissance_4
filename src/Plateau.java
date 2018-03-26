import java.util.Scanner;

public class Plateau {
    static final Scanner input = new Scanner(System.in);

    private char plateau[][];
    private int tour;

    public Plateau() {
        this.plateau = new char[6][7];
        this.tour = 1;
    }

    public Plateau(int ligne, int col) {
        this.plateau = new char[ligne][col];
        this.tour = 1;
    }

    public void initPlateau() {
        for (int i=0; i < this.plateau.length; i++) {
            for (int j=0; j < this.plateau[i].length ; j++) {
                this.plateau[i][j] = 'o';
            }
        }
    }

    public void contenuPlateau() {
        for (int i=0; i < this.plateau.length; i++) {
            for (int j=0; j < this.plateau[i].length ; j++) {
                System.out.print(" " + this.plateau[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int demanderCol(int err) {
        //erreur 0 pour fonctionnement normal
        //erreur 1 pour colonne pleine
        //-1 si erreur d'exec
        if (err == 0) {
            System.out.println("Veuillez donner une colonne :");
            return input.nextInt();
        }
        else if (err == 1) {
            System.out.println("Colonne pleine, veuillez donner une autre colonne :");
            return input.nextInt();
        }
        return -1;
    }

    public boolean isPossible(int col) {
        for (int i = (this.plateau.length)-1; i >= -1; i--) {
            if (i == -1) return false;
            if (col < 0 || col > this.plateau[i].length) return false;
            if (this.plateau[i][col] == 'o') {
                return true;
            }
        }
        return false;
    }

    public void mettrePion() {
        if (!this.isGameOver()) {
            int col = demanderCol(0);

            while (!isPossible(col)) {
                col = demanderCol(1);
            }
            if (isPossible(col)) {
                for (int i = this.plateau.length - 1; i >= 0; i--) {
                    if (this.plateau[i][col] == 'o') {
                        if (tour % 2 == 0) {
                            this.plateau[i][col] = 'R';
                        }
                        if (tour % 2 != 0) {
                            this.plateau[i][col] = 'J';
                        }
                        this.contenuPlateau();
                        this.tour++;
                        break;
                    }
                }
            }
        }
    }

    //Teste si des cases sont encore disponibles
    public boolean isGameOver() {
        for (int i=0; i < this.plateau.length; i++) {
            for (int j=0; j < this.plateau[i].length ; j++) {
                if (plateau[i][j] == 'o') return false;
            }
        }
        System.out.println("Plus aucune case n'est libre, fin de la partie.");
        return true;
    }

}
