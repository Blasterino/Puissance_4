import javax.swing.*;

public class FenetreJeu extends JFrame {

    Plateau plateau;
    JButton[] listeColonnes;
    JPanel[][] grille;
    JButton bSauterLigne;

    public FenetreJeu(Plateau p){
        this.plateau=p;
        initAttribut();
        ajouterWidget();
        pack();
        setTitle("Le Jeu");
        setVisible(true);
    }

    public void initAttribut(){
        JPanel pFenetre = new JPanel();
        setContentPane(pFenetre);
    }

    public void ajouterWidget(){

    }
}
