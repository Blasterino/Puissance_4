import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JPanelFond extends JPanel {

    @Override
    protected void paintComponent(Graphics g){
        File f;
        Image image;
        try{
            f = new File("media/fondjeu.jpg");
            image = ImageIO.read(f);
            g.drawImage(image,0,0,null);
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
