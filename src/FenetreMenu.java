import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class FenetreMenu extends JFrame {

    JButton lancerPartie;
    JButton quitter;
    JComboBox cLigne,cColonne,cPuissance;
    Canvas can;
    EmbeddedMediaPlayer emp;
    JPanel pVideo;


    public FenetreMenu(boolean video){
        initAttribut();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width-100, screenSize.height-100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        if(video){
            video();
            setVisible(false);
        }
        creerVue();
        setVisible(true);
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

    public void video(){
        pVideo = new JPanel();
        can = new Canvas();
        can.setBackground(Color.black);
        pVideo.setLayout(new BorderLayout());
        pVideo.add(can);
        setContentPane(pVideo);
        setVisible(true);

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(),LibVlc.class);
        MediaPlayerFactory mpf = new MediaPlayerFactory();
        emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(this));
        emp.setVideoSurface(mpf.newVideoSurface(can));
        emp.setEnableMouseInputHandling(false);
        String fichier = "media/intro.mov";
        emp.prepareMedia(fichier);
        //emp.toggleFullScreen();
        emp.play();
        try {
            Thread.sleep(2000);
            while(emp.isPlaying()){
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
