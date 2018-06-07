public class ControlGroup {

    FenetreMenu fenetreMenu;
    ControlButtonMenu controlb;


    public ControlGroup(boolean video){
        if(video){
            this.fenetreMenu = new FenetreMenu(true);
        } else {
            this.fenetreMenu = new FenetreMenu(false);
        }


        this.controlb = new ControlButtonMenu(this.fenetreMenu);

    }

}
