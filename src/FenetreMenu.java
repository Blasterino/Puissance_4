import javax.swing.*;

public class FenetreMenu extends JFrame {

    Plateau p;
    JButton lancerPartie;
    JButton quitter;
    ImageIcon fondEcranTropBeau;


    public FenetreMenu(Plateau p){
        this.p = p;
        initAttribut();
        creerVue();
        pack();
        setVisible(true);
    }

    public void initAttribut(){
        lancerPartie = new JButton("JOUER");
        quitter = new JButton("QUITTER");
    }

    public void creerVue(){
        JPanel pFenetre = new JPanel();
        pFenetre.add(lancerPartie);
        pFenetre.add(quitter);
        setContentPane(pFenetre);
    }

    public void setControlButton(ControlButtonMenu cb){
        quitter.addActionListener(cb);
        lancerPartie.addActionListener(cb);
    }
}
