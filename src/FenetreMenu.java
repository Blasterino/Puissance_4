import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FenetreMenu extends JFrame {

    JButton lancerPartie;
    JButton quitter;
    JTextField tfLigne,tfColonne,tfPuissance;
    ImageIcon fondEcranTropBeau;


    public FenetreMenu(){
        initAttribut();
        creerVue();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initAttribut(){

        tfLigne = new JTextField("",10);
        tfColonne = new JTextField("",10);
        tfPuissance = new JTextField("",10);
        lancerPartie = new JButton("JOUER");
        quitter = new JButton("QUITTER");
    }



    public void creerVue(){
        JPanel pCadre = new JPanel();
        pCadre.setLayout(new BoxLayout(pCadre, BoxLayout.Y_AXIS));

        JPanelFondMenu pFenetre = new JPanelFondMenu();

        JLabel lLigne = new JLabel("Nombre de lignes");
        lLigne.setOpaque(false);
        lLigne.setForeground(Color.white);

        JPanel pLigne = new JPanel();
        pLigne.setOpaque(false);
        pLigne.add(lLigne);
        pLigne.add(tfLigne);
        pFenetre.add(pLigne);

        JLabel lColonne = new JLabel("Nombre de colonnes");
        lColonne.setOpaque(false);
        lColonne.setForeground(Color.white);

        JPanel pColonne = new JPanel();
        pColonne.setOpaque(false);
        pColonne.add(lColonne);
        pColonne.add(tfColonne);
        pFenetre.add(pColonne);

        JLabel lPuissance = new JLabel("Niveau de la puissance");
        lPuissance.setForeground(Color.white);
        lPuissance.setOpaque(false);

        JPanel pPuissance = new JPanel();
        pPuissance.setOpaque(false);
        pPuissance.add(lPuissance);
        pPuissance.add(tfPuissance);
        pFenetre.add(pPuissance);

        JPanel pLancer = new JPanel();
        pLancer.setOpaque(false);
        pLancer.add(lancerPartie);
        pFenetre.add(pLancer);

        JPanel pQuitter = new JPanel();
        pQuitter.setOpaque(false);
        pLancer.add(quitter);
        pFenetre.add(pQuitter);

        pCadre.add(pFenetre);

        setContentPane(pCadre);
    }

    public void setControlButton(ControlButtonMenu cb){
        quitter.addActionListener(cb);
        lancerPartie.addActionListener(cb);
    }
}
