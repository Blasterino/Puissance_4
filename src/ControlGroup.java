public class ControlGroup {

    FenetreMenu fenetreMenu;
    ControlButtonMenu controlb;
    ControlMouse controlm;


    public ControlGroup(boolean video){
        if(video){
            fenetreMenu = new FenetreMenu(true);
        } else {
            fenetreMenu = new FenetreMenu(false);
        }
        controlm = new ControlMouse(fenetreMenu);
        controlb = new ControlButtonMenu(fenetreMenu);

    }

}
