import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);

        Plateau plateau;
        String rep;
        System.out.println("Entrez \"Default\" ou \"Custom\" pour lancer un type de partie");
        rep = input.next();
        while (!rep.equals("Default") && !rep.equals("Custom")) {
            System.out.println("Entrez \"Defalut\" ou \"Custom\" pour lancer un type de partie");
            rep = input.next();
        }

        if (rep.equals("Default")) {
            plateau = new Plateau();
        }
        else {
            int lignes;
            int colonnes;
            System.out.println("Entrez le nombre de lignes du plateau :");
            lignes = input.nextInt();
            System.out.println("Entrez le nombre de colonnes du plateau :");
            colonnes = input.nextInt();
            plateau = new Plateau(lignes, colonnes);
        }

        plateau.jouer();
    }
}
