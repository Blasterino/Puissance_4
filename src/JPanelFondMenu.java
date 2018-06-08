import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JPanelFondMenu extends JPanel {

    @Override
    protected void paintComponent(Graphics g){
        try{
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            File input = new File("media/fond.png");
            BufferedImage image = ImageIO.read(input);

            BufferedImage resized = resize(image, (int)screenSize.getHeight(), (int)screenSize.getWidth());
            g.drawImage(resized,0,0,null);
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
