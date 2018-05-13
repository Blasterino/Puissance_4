
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButtonMenu implements ActionListener {

    FenetreMenu fenetreMenu;
    Plateau plateau;

    public ControlButtonMenu(FenetreMenu f, Plateau p){
        this.fenetreMenu = f;
        this.plateau = p;
        this.fenetreMenu.setControlButton(this);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == fenetreMenu.lancerPartie){
            fenetreMenu.dispose();
            FenetreJeu f = new FenetreJeu(plateau);
        }
        return;
    }
}
