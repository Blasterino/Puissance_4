
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButtonMenu implements ActionListener {

    FenetreMenu fenetreMenu;

    public ControlButtonMenu(FenetreMenu f){
        this.fenetreMenu = f;
        this.fenetreMenu.setControlButton(this);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource()== fenetreMenu.lancerPartie){
            int nbLigne,nbColonne,nbPuissance;
            boolean test = true;
            //ils sont en coms pour que je puisse tester l'affichage du jeu
            try{
                nbLigne = Integer.parseInt(fenetreMenu.cLigne.getSelectedItem().toString());
                nbColonne = Integer.parseInt(fenetreMenu.cColonne.getSelectedItem().toString());
                nbPuissance = Integer.parseInt(fenetreMenu.cPuissance.getSelectedItem().toString());
            } catch(NumberFormatException nfe){
                test = false;
            }

            if(test){
                nbLigne = Integer.parseInt(fenetreMenu.cLigne.getSelectedItem().toString());
                nbColonne = Integer.parseInt(fenetreMenu.cColonne.getSelectedItem().toString());
                nbPuissance = Integer.parseInt(fenetreMenu.cPuissance.getSelectedItem().toString());
            } else {
                nbLigne = 6;
                nbColonne = 7;
                nbPuissance = 4;
            }

            Plateau p = new Plateau(nbLigne,nbColonne,nbPuissance);
            fenetreMenu.dispose();
            ControlGroupJeu cbj = new ControlGroupJeu(p);
        }

        if(e.getSource()==fenetreMenu.quitter){
            System.exit(0);
        }
    }
}