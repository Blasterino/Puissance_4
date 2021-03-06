import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        /*
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                Plateau p = new Plateau();
                ControlGroup control = new ControlGroup(p);
            }
        });
        */
        /*
        final Scanner input = new Scanner(System.in);

        Plateau plateau;
        System.out.println("Entrez \"Default\" ou \"Custom\" pour lancer un type de partie");
        String rep = input.next();

        while (!rep.equals("Default") && !rep.equals("Custom")) {
            System.out.println("Entrez \"Default\" ou \"Custom\" pour lancer un type de partie");
            rep = input.next();
        }
        if (rep.equals("Default")) {
            //Tester que la puissance est supérieure à deux
            // Tester que le plateau permet de faire une combinaison (si pas trop petit)
            System.out.println("Donner la puissance du plateau (le nombre de pions à aligner pour gagner :)");
            int puiss = input.nextInt();
            plateau = new Plateau(puiss);
        }
        else {
            System.out.println("Entrez le nombre de lignes du plateau :");
            int lignes = input.nextInt();
            System.out.println("Entrez le nombre de colonnes du plateau :");
            int colonnes = input.nextInt();
            System.out.println("Donner la puissance du plateau (le nombre de pions à aligner pour gagner :)");
            int puiss = input.nextInt();
            plateau = new Plateau(lignes, colonnes, puiss);
        }
        */
        //Lancement de la musique
        try {
            File fichierSon = new File("media/musique.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(fichierSon);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        ControlGroup cg = new ControlGroup(true);
        //plateau.jouer();
    }
}
