public class ControlGroup {

    FenetreMenu fenetreMenu;
    ControlButtonMenu controlb;


    public ControlGroup(){

        this.fenetreMenu = new FenetreMenu();
        this.controlb = new ControlButtonMenu(this.fenetreMenu);

    }

}
