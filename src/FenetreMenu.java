import javax.swing.*;

public class FenetreMenu extends JFrame {

    Plateau p;
    JButton lancerPartie;
    JButton quitter;
    JTextField tfLigne,tfColonne,tfPuissance;
    ImageIcon fondEcranTropBeau;


    public FenetreMenu(Plateau p){
        this.p = p;
        initAttribut();
        creerVue();
        pack();
        setVisible(true);
    }

    public void initAttribut(){

        //tfLigne = new JTextField(""+p.getLigne(),10);
        //tfColonne = new JTextField(""+p.getColonne(),10);
        tfPuissance = new JTextField(""+p.getPuissancePlateau(),10);
        lancerPartie = new JButton("JOUER");
        quitter = new JButton("QUITTER");
    }

    public void creerVue(){
        JPanel pCadre = new JPanel();

        JPanel pFenetre = new JPanel();

        JPanel pLigne = new JPanel();
        pLigne.add(new JLabel("Nombre de lignes"));
       // pLigne.add(tfLigne);
        pFenetre.add(pLigne);

        JPanel pColonne = new JPanel();
        pColonne.add(new JLabel("Nombre de colonnes"));
        //pColonne.add(tfLigne);
        pFenetre.add(pColonne);

        JPanel pPuissance = new JPanel();
        pPuissance.add(new JLabel("Niveau de la puissance"));
        //pPuissance.add(tfLigne);
        pFenetre.add(pPuissance);

        JPanel pLancer = new JPanel();
        pLancer.add(lancerPartie);
        pFenetre.add(pLancer);

        JPanel pQuitter = new JPanel();
        pLancer.add(quitter);
        pFenetre.add(pQuitter);

        pCadre.add(pFenetre);

        setContentPane(pFenetre);
    }

    public void setControlButton(ControlButtonMenu cb){
        quitter.addActionListener(cb);
        lancerPartie.addActionListener(cb);
    }
}
