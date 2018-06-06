import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreJeu extends JFrame {


    Plateau plateau;
    JLabel[][] grille;
    JLabel lTour, lJoueur;
    JButton[] listeColonnes;
    JButton bSauterLigne, bQuitter;
    Timer timer;
    JOptionPane fenetreDialogue;


    public FenetreJeu(Plateau p){
        this.plateau = p;
        initAttribut();
        updateGrille();
        creerVue();
        pack();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initAttribut(){

        //création des boutons pour ajouter au niveau des colonnes
        listeColonnes = new JButton[plateau.getColonne()];
        for (int i=0; i<plateau.getColonne();i++){
            listeColonnes[i] = new JButton();
            listeColonnes[i].setPreferredSize(new Dimension(100,100));
            listeColonnes[i].setIcon(plateau.getItab()[i+3]);
        }

        //création des labels
        grille = new JLabel[plateau.getLigne()][plateau.getColonne()];
        //System.out.println(plateau.getLigne());
        for(int i = 0; i<plateau.getLigne();i++){
            for (int j =0; j<plateau.getColonne();j++){
                grille[i][j] = new JLabel();
                grille[i][j].setPreferredSize(new Dimension(100-plateau.getColonne()*2,100-plateau.getLigne()*2));
                grille[i][j].setOpaque(false);
            }
        }

        //création des autres boutons
        bSauterLigne = new JButton("Activer la puissance 4");
        bQuitter = new JButton("Quitter");

        lTour = new JLabel("Tour : 0");
        lTour.setOpaque(false);
        lTour.setForeground(Color.white);
        lJoueur = new JLabel("Au tour de : "+plateau.getCouleurDebut());
        lJoueur.setOpaque(false);
        lJoueur.setForeground(Color.white);
        initTimer();

        //initialisation du JOptionPane
        fenetreDialogue = new JOptionPane();
    }

    public void creerVue(){

        //panel tout en haut
        JPanel panelPlusHaut = new JPanel(new FlowLayout(FlowLayout.CENTER,100, 0));
        panelPlusHaut.setOpaque(false);
        //panelPlusHaut.setLayout(new BoxLayout(panelPlusHaut,BoxLayout.X_AXIS));
        panelPlusHaut.add(lTour);
        panelPlusHaut.add(lJoueur);

        //panel du haut
        JPanel panelHaut = new JPanel();
        panelHaut.setOpaque(false);
        panelHaut.add(bQuitter);
        //panel du milieu haut
        JPanel panelColonnes = new JPanel(new GridLayout(1,plateau.getPlateau().length));
        panelColonnes.setOpaque(false);
        for (JButton butt : listeColonnes){
            panelColonnes.add(butt);
        }
        //panel du milieu bas
        JPanel panelGrille = new JPanel(new GridLayout(plateau.getPlateau().length,plateau.getPlateau()[0].length));
        panelGrille.setOpaque(false);
        for (int i = 0; i < grille.length; i++){
            for(JLabel pan : grille[i]){
                panelGrille.add(pan);
            }
        }
        //panel bas
        JPanel panelBas = new JPanel();
        panelBas.setOpaque(false);
        panelBas.setLayout(new BoxLayout(panelBas,BoxLayout.X_AXIS));
        panelBas.add(bSauterLigne);
        //ajout
        JPanelFond container = new JPanelFond();

        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.add(panelPlusHaut);
        container.add(panelHaut);
        container.add(panelColonnes);
        container.add(panelGrille);
        container.add(panelBas);
        setContentPane(container);
    }

    public void updateGrille(){
        //System.out.println(plateau.getColonne());
        //System.out.println(plateau.getLigne());
        for(int i = 0; i<plateau.getLigne();i++){
            for (int j =0; j<plateau.getColonne();j++){
                if (plateau.getPlateau()[i][j]=='o'){
                    //grille[i][j].setText("0");
                    grille[i][j].setIcon(plateau.getItab()[0]);
                } else if (plateau.getPlateau()[i][j]=='T'){
                    //grille[i][j].setText("S");
                    grille[i][j].setIcon(plateau.getItab()[1]);
                } else if (plateau.getPlateau()[i][j]=='J'){
                    //grille[i][j].setText("U");
                    grille[i][j].setIcon(plateau.getItab()[2]);
                }

            }
        }

        if(plateau.getTour()%2==1){
            lJoueur.setText("Au tour de : "+plateau.getCouleurDebut());
        } else {
            lJoueur.setText("Au tour de : "+plateau.getCouleurDeuxieme());
        }

        lTour.setText("Tour : "+plateau.getTour());

    }

    public void initTimer(){
        timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("media/musique.wav");
            }
        });
        timer.start();
    }

    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream("media/" + url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void setControlButton(ControlButtonJeu cb){
        for (JButton butt : listeColonnes){
            butt.addActionListener(cb);
        }
        bSauterLigne.addActionListener(cb);
        bQuitter.addActionListener(cb);
    }

    public void gameOver(int i){
        String message = "";
        JDialog fenErr;
        switch(i){
            case 1:
                message += "Partie terminée !\n Le joueur " + plateau.getCouleurDeuxieme() + " a gagné.";
                break;
            case 2 :
                message += "Partie terminée !\n Le joueur " + plateau.getCouleurDebut() + " a gagné.";
                break;
        }

        fenetreDialogue.showMessageDialog( this, message, "Game Over",
                JOptionPane.ERROR_MESSAGE );
        fenErr = fenetreDialogue.createDialog(this, "Game Over");
    }
}
