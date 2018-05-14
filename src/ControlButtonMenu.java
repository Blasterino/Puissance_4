
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButtonMenu implements ActionListener {

    FenetreMenu fenetreMenu;
    Plateau p;

    public ControlButtonMenu(FenetreMenu f, Plateau p){
        this.fenetreMenu = f;
        this.p = p;
        this.fenetreMenu.setControlButton(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()== fenetreMenu.lancerPartie){

            //ils sont en coms pour que je puisse tester l'affichage du jeu
            //int nbLigne = Integer.parseInt(fenetreMenu.tfLigne.getText());
            //int nbColonne = Integer.parseInt(fenetreMenu.tfColonne.getText());
            //int nbPuissance = Integer.parseInt(fenetreMenu.tfPuissance.getText());

            int nbLigne = 6;
            int nbColonne = 6;
            int nbPuissance = 4;

            p.setLigne(nbLigne);
            p.setColonne(nbColonne);
            p.setPuissancePlateau(nbPuissance);

            fenetreMenu.dispose();
            ControlGroupJeu cbj = new ControlGroupJeu(p);
        }
    }
}