import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FenetreJeu extends JFrame {


    Plateau plateau;
    JLabel[][] grille;
    JLabel lTour, lJoueur, lPts1, lPts2;
    JButton[] listeColonnes;
    JButton bSauterLigne, bQuitter;
    Timer timer;
    JOptionPane fenetreDialogue;
    Clip[] clipBoom,clipWow,clipGG;


    public FenetreJeu(Plateau p){
        this.plateau = p;
        initSons();
        initAttribut();
        updateGrille();
        creerVue();
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initAttribut(){

        //création des boutons pour ajouter au niveau des colonnes
        lPts1= new JLabel("Point de J1 : "+plateau.getPts1()+"000000");
        lPts1.setForeground(Color.white);
        lPts2= new JLabel("Point de J2 : "+plateau.getPts1()+"000000");
        lPts2.setForeground(Color.white);
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.setPts1(plateau.getPts1()+1);
                plateau.setPts2(plateau.getPts2()+1);
                lPts1.setText("Point de J1 : "+plateau.getPts1()+"000000");
                lPts2.setText("Point de J2 : "+plateau.getPts2()+"000000");
            }
        });
        timer.start();
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
        bSauterLigne.setEnabled(false);
        bQuitter = new JButton("Quitter");

        lTour = new JLabel("Tour : 0");
        lTour.setOpaque(false);
        lTour.setForeground(Color.white);
        lJoueur = new JLabel("Au tour de : "+plateau.getCouleurDebut());
        lJoueur.setOpaque(false);
        lJoueur.setForeground(Color.white);

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
        JPanel panelBas = new JPanel(new FlowLayout(FlowLayout.CENTER,10,50));
        panelBas.setOpaque(false);
        //panelBas.setLayout(new BoxLayout(panelBas,BoxLayout.X_AXIS));
        panelBas.add(lPts1);
        panelBas.add(bSauterLigne);
        panelBas.add(lPts2);
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

        if (plateau.isPuissanceAvailable()) bSauterLigne.setEnabled(true);
        else bSauterLigne.setEnabled(false);
    }

    public void setControlButton(ControlButtonJeu cb){
        for (JButton butt : listeColonnes) butt.addActionListener(cb);
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

    public void initSons(){

        clipBoom = new Clip[6];
        clipWow = new Clip[2];
        clipGG = new Clip[4];

        for (int i = 0; i<6;i++){
            try {
                File fichierSon = new File("media/sons/boom"+String.valueOf(i+1)+".wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(fichierSon);
                clipBoom[i] = AudioSystem.getClip();
                clipBoom[i].open(audioIn);
                FloatControl gainControl = (FloatControl) clipBoom[i].getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-20.0f);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i<2;i++){
            try {
                File fichierSon = new File("media/sons/wow"+String.valueOf(i+1)+".wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(fichierSon);
                clipWow[i] = AudioSystem.getClip();
                clipWow[i].open(audioIn);
                FloatControl gainControl = (FloatControl) clipWow[i].getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-5.0f);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i<4;i++){
            try {
                File fichierSon = new File("media/sons/gg"+String.valueOf(i+1)+".wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(fichierSon);
                clipGG[i] = AudioSystem.getClip();
                clipGG[i].open(audioIn);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    public void playBoom(){
        int rand = (int)(Math.random()*5.+1);
        if(clipBoom[rand].isRunning()){
            clipBoom[rand].stop();
        }
        clipBoom[rand].setMicrosecondPosition(0);
        clipBoom[rand].start();
    }

    public void playWow(){
        int rand = (int)(Math.random()*1.+1);
        if(clipWow[rand].isRunning()){
            clipWow[rand].stop();
        }
        clipWow[rand].setMicrosecondPosition(0);
        clipWow[rand].start();
    }

    public void playGG(){
        int rand = (int)(Math.random()*3.+1);
        if(clipGG[rand].isRunning()){
            clipGG[rand].stop();
        }
        clipGG[rand].setMicrosecondPosition(0);
        clipGG[rand].start();
    }
}
