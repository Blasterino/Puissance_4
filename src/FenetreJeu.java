import javax.swing.*;
import java.awt.*;

public class FenetreJeu extends JFrame {


    Plateau plateau;
    JPanel[][] grille;
    JButton[] listeColonnes;
    JButton bSauterLigne, bQuitter;



    public FenetreJeu(Plateau p){
        this.plateau = p;
        initAttribut();
        creerVue();
        pack();
        setVisible(true);
    }

    public void initAttribut(){
        for (int i =0; i<plateau.getPlateau().length;i++){
            listeColonnes[i] = new JButton(String.valueOf(i));
        }

        for(int i = 0; i<plateau.getPlateau().length;i++){
            for (int j =0; j<plateau.getPlateau()[0].length;j++){
                grille[i][j] = new JPanel();
            }
        }
        bSauterLigne = new JButton("Activer la puissance 4");
        bQuitter = new JButton("Quitter");
    }

    public void creerVue(){

        JPanel panelHaut = new JPanel();
        panelHaut.add(bQuitter);
        JPanel panelColonnes = new JPanel(new GridLayout(1,plateau.getPlateau().length));
        for (JButton butt : listeColonnes){
            panelColonnes.add(butt);
        }
        JPanel panelGrille = new JPanel(new GridLayout(plateau.getPlateau().length,plateau.getPlateau()[0].length));
        for (int i = 0; i < grille.length; i++){
            for(JPanel pan : grille[i]){
                panelGrille.add(pan);
            }
        }
        JPanel panelBas = new JPanel();
        panelBas.setLayout(new BoxLayout(panelBas,BoxLayout.X_AXIS));
        JPanel container = new JPanel(new GridLayout(1,4));
        container.add(panelHaut);
        container.add(panelColonnes);
        container.add(panelGrille);
        container.add(panelBas);
        setContentPane(container);
    }

    public void setControlButton(ControlButtonJeu cb){
        for (JButton butt : listeColonnes){
            butt.addActionListener(cb);
        }
        bSauterLigne.addActionListener(cb);
        bQuitter.addActionListener(cb);
    }
}
