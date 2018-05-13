import javax.swing.*;

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
        bQuitter = new JButton("Quitte");
    }

    public void creerVue(){
        return;
    }

    public void setControlButton(ControlButtonJeu cb){
        for (JButton butt : listeColonnes){
            butt.addActionListener(cb);
        }
        bSauterLigne.addActionListener(cb);
        bQuitter.addActionListener(cb);
    }
}
