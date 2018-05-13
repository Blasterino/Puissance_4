
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

            int nbLigne = Integer.parseInt(fenetreMenu.tfLigne.getText());
            int nbColonne = Integer.parseInt(fenetreMenu.tfColonne.getText());
            int nbPuissance = Integer.parseInt(fenetreMenu.tfPuissance.getText());

            p.setLigne(nbLigne);
            p.setColonne(nbColonne);
            p.setPuissancePlateau(nbPuissance);

            fenetreMenu.dispose();
            ControlGroupJeu cbj = new ControlGroupJeu(p);
        }
    }
}