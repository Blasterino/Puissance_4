import javax.swing.*;

public class FenetreJeu extends JFrame {

    Plateau plateau;
    JButton[] listeColonnes;
    JPanel[][] grille;
    JButton bSauterLigne, bQuitter;

    public FenetreJeu(Plateau p){
        this.plateau=p;
        initAttribut();
        ajouterWidget();
        pack();
        setTitle("Le Jeu");
        this.plateau = p;
        initAttribut();
        creerVue();
        pack();
        setVisible(true);
    }

    public void initAttribut(){
<<<<<<< HEAD
        JPanel pFenetre = new JPanel();
        setContentPane(pFenetre);
    }

    public void ajouterWidget(){
=======
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
>>>>>>> 7575f5265083f714dff5d124bf67319ca26a4385

    public void setControlButton(ControlButtonJeu cb){
        for (JButton butt : listeColonnes){
            butt.addActionListener(cb);
        }
        bSauterLigne.addActionListener(cb);
        bQuitter.addActionListener(cb);
    }
}
