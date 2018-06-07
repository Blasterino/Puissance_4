import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FenetreMenu extends JFrame {

    JButton lancerPartie;
    JButton quitter;
    JComboBox cLigne,cColonne,cPuissance;
    ImageIcon fondEcranTropBeau;


    public FenetreMenu(){
        //video();
        initAttribut();
        creerVue();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width-100, screenSize.height-100);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initAttribut(){

        String[] tabLigne= new String[]{"6","7","8"};
        String[] tabColonne= new String[]{"7","8","9","10","11","12","13","14","15"};
        String[] tabPuissance= new String[]{"4","5","6"};

        cLigne = new JComboBox(tabLigne);
        cColonne = new JComboBox(tabColonne);
        cPuissance = new JComboBox(tabPuissance);

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
        pLigne.add(cLigne);
        pFenetre.add(pLigne);

        JLabel lColonne = new JLabel("Nombre de colonnes");
        lColonne.setOpaque(false);
        lColonne.setForeground(Color.white);

        JPanel pColonne = new JPanel();
        pColonne.setOpaque(false);
        pColonne.add(lColonne);
        pColonne.add(cColonne);
        pFenetre.add(pColonne);

        JLabel lPuissance = new JLabel("Niveau de la puissance");
        lPuissance.setForeground(Color.white);
        lPuissance.setOpaque(false);

        JPanel pPuissance = new JPanel();
        pPuissance.setOpaque(false);
        pPuissance.add(lPuissance);
        pPuissance.add(cPuissance);
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
    /* Fonction lançant la vidéo (marche po)
    public void video(){
        try {
            URL mediaURL = new File("media/intro.mov").toURI().toURL();
            Player mediaPlayer = null;
            mediaPlayer = Manager.createRealizedPlayer(mediaURL);
            Component video = mediaPlayer.getVisualComponent();
            add(video,BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoPlayerException e) {
            e.printStackTrace();
        } catch (CannotRealizeException e) {
            e.printStackTrace();
        }
    }*/


}
