import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ControlButtonJeu implements ActionListener {

    Plateau p;
    FenetreJeu f;

    public ControlButtonJeu(FenetreJeu f, Plateau p){
        this.p=p;
        this.f=f;
        this.f.setControlButton(this);
    }

    public void actionPerformed(ActionEvent e){
        boolean fini = false;//variable booleenne determinant si la partie doit être terminée

        //control des boutons pour ajouter un pion à une colonne

        for (int i=0; i<f.listeColonnes.length; i++){
            if(e.getSource()==f.listeColonnes[i]){
                f.playBoom();
                Random r = new Random();
                if (p.getTour() % 2 != 0) {
                    p.setPts1(p.getPts1()+(r.nextInt(100)));
                }
                else if(p.getTour() % 2 == 0) {
                    p.setPts2(p.getPts2()+(r.nextInt(100)));
                }
                p.mettrePion(i);
                f.updateGrille();
                for (int j = 0; j < p.getPlateau().length; j++) {
                    if ((p.getPlateau()[j][i] == 'J') || (p.getPlateau()[j][i] == 'T')){
                        int indice = j;

                        if (p.isCombination(i, indice)) {
                            if (p.getTour() % 2 == 0) {
                                f.playGG();
                                f.gameOver(2);// Le joueur 2 a gagné
                                fini = true;
                            }
                            if (p.getTour() % 2 != 0) {
                                f.playGG();
                                f.gameOver(1);//le joueur 1 a gagné
                                fini = true;

                            }
                        }
                        break;
                    }
                }

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
            ControlGroup cg = new ControlGroup(false);
        }

        if(e.getSource()==f.bSauterLigne){
            f.playWow();
            p.supprimerDerniereLigne();
            p.setCooldownPuissanceDefaut();
            p.setTour(p.getTour()+1);
            f.updateGrille();
        }
        if(fini){
            f.dispose();
            ControlGroup cg = new ControlGroup(false);
        }
    }
}
