import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JPanelFondMenu extends JPanel {

    @Override
    protected void paintComponent(Graphics g){
        File f;
        Image image;
        try{
            f = new File("media/fond.png");
            image = ImageIO.read(f);
            g.drawImage(image,0,0,null);
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
