import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButtonJeu implements ActionListener {

    Plateau p;
    FenetreJeu f;

    public ControlButtonJeu(FenetreJeu f, Plateau p){
        this.p=p;
        this.f=f;
        this.f.setControlButton(this);
    }

    public void actionPerformed(ActionEvent e){

        //control des boutons pour ajouter un pion à une colonne

        for (int i=0; i<f.listeColonnes.length; i++){
            if(e.getSource()==f.listeColonnes[i]){
                p.mettrePion(i);
                f.updateGrille();
            }
        }

        //control pour faire sauter une ligne

        /*
        if(e.getSource()==f.bSautLigne){

            //p.sauterLigne();
            //ff.updateGrille();
        }
        */

        if(e.getSource()==f.bQuitter){
            f.dispose();
            ControlGroup cg = new ControlGroup();
        }
    }
}
