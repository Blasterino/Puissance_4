import javax.imageio.ImageIO;
import javax.swing.*;
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
        pack();
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
        BufferedImage buff;
        ImageIcon icon;
        JLabel label;
        JPanel pCadre = new JPanel();
        pCadre.setLayout(new BoxLayout(pCadre, BoxLayout.Y_AXIS));
        JPanel pImage = new JPanel();
        try {
            buff = ImageIO.read(new File("media/fond.png"));
            icon = new ImageIcon(buff);
            label = new JLabel();
            label.setIcon(new ImageIcon(icon.getImage().getScaledInstance(900,500,BufferedImage.SCALE_SMOOTH)));
            pImage.add(label);
        } catch (IOException e) {
            e.printStackTrace();
        }



        JPanel pFenetre = new JPanel();

        JPanel pLigne = new JPanel();
        pLigne.add(new JLabel("Nombre de lignes"));
        pLigne.add(tfLigne);
        pFenetre.add(pLigne);

        JPanel pColonne = new JPanel();
        pColonne.add(new JLabel("Nombre de colonnes"));
        pColonne.add(tfColonne);
        pFenetre.add(pColonne);

        JPanel pPuissance = new JPanel();
        pPuissance.add(new JLabel("Niveau de la puissance"));
        pPuissance.add(tfPuissance);
        pFenetre.add(pPuissance);

        JPanel pLancer = new JPanel();
        pLancer.add(lancerPartie);
        pFenetre.add(pLancer);

        JPanel pQuitter = new JPanel();
        pLancer.add(quitter);
        pFenetre.add(pQuitter);

        pCadre.add(pImage);
        pCadre.add(pFenetre);

        setContentPane(pCadre);
    }

    public void setControlButton(ControlButtonMenu cb){
        quitter.addActionListener(cb);
        lancerPartie.addActionListener(cb);
    }
}
