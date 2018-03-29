public class Main {

    public static void main(String[] args) {
        Plateau p1 = new Plateau();
        p1.initPlateau();
        p1.contenuPlateau();
        Plateau p2 = new Plateau(2, 2);
        p2.initPlateau();
        p2.contenuPlateau();
        while (!p2.isGameOver()) {
            p2.mettrePion();
        }


    }
}
