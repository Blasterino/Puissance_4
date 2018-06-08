import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControlMouse implements MouseListener {

    FenetreMenu fen;

    public ControlMouse(FenetreMenu fen){
        fen.addControlMouse(this);
    }

    public void mouseClicked(MouseEvent e){
        fen.stopMedia();
    }

    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
}
