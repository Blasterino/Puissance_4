import javax.swing.*;
import java.awt.*;

public class FenetreJeu extends JFrame {


    Plateau plateau;
    JLabel[][] grille;
    JButton[] listeColonnes;
    JButton bSauterLigne, bQuitter;


    public FenetreJeu(Plateau p){
        this.plateau = p;
        initAttribut();
        updateGrille();
        creerVue();
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initAttribut(){

        //création des boutons pour ajouter au niveau des colonnes
        listeColonnes = new JButton[plateau.getColonne()];
        for (int i=0; i<plateau.getColonne();i++){
            listeColonnes[i] = new JButton(String.valueOf(i+1));
        }

        //création des labels
        grille = new JLabel[plateau.getLigne()][plateau.getColonne()];
        for(int i = 0; i<plateau.getColonne();i++){
            for (int j =0; j<plateau.getLigne();j++){
                grille[i][j] = new JLabel();
                grille[i][j].setPreferredSize(new Dimension(100,100));
            }
        }

        //création des autres boutons
        bSauterLigne = new JButton("Activer la puissance 4");
        bQuitter = new JButton("Quitter");
    }

    public void creerVue(){

        //panel du haut
        JPanel panelHaut = new JPanel();
        panelHaut.add(bQuitter);
        //panel du milieu haut
        JPanel panelColonnes = new JPanel(new GridLayout(1,plateau.getPlateau().length));
        for (JButton butt : listeColonnes){
            panelColonnes.add(butt);
        }
        //panel du milieu bas
        JPanel panelGrille = new JPanel(new GridLayout(plateau.getPlateau().length,plateau.getPlateau()[0].length));
        for (int i = 0; i < grille.length; i++){
            for(JLabel pan : grille[i]){
                panelGrille.add(pan);
            }
        }
        //panel bas
        JPanel panelBas = new JPanel();
        panelBas.setLayout(new BoxLayout(panelBas,BoxLayout.X_AXIS));
        panelBas.add(bSauterLigne);
        //ajout
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.add(panelHaut);
        container.add(panelColonnes);
        container.add(panelGrille);
        container.add(panelBas);
        setContentPane(container);
    }

    public void updateGrille(){
        for(int i = 0; i<plateau.getColonne();i++){
            for (int j =0; j<plateau.getLigne();j++){
                if (plateau.getPlateau()[i][j]=='o'){
                    //grille[i][j].setText("0");
                    grille[i][j].setIcon(plateau.getItab()[0]);
                } else if (plateau.getPlateau()[i][j]=='S'){
                    //grille[i][j].setText("S");
                    grille[i][j].setIcon(plateau.getItab()[1]);
                } else if (plateau.getPlateau()[i][j]=='U'){
                    //grille[i][j].setText("U");
                    grille[i][j].setIcon(plateau.getItab()[2]);
                }

            }
        }
    }

    public void setControlButton(ControlButtonJeu cb){
        for (JButton butt : listeColonnes){
            butt.addActionListener(cb);
        }
        bSauterLigne.addActionListener(cb);
        bQuitter.addActionListener(cb);
    }
}
