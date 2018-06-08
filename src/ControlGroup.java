public class ControlGroup {

    FenetreMenu fenetreMenu;
    ControlButtonMenu controlb;


    public ControlGroup(boolean video){
        if(video){
            fenetreMenu = new FenetreMenu(true);
        } else {
            fenetreMenu = new FenetreMenu(false);
        }
        controlb = new ControlButtonMenu(fenetreMenu);

    }

}
