public class ControlGroup {

    Plateau plateau;
    FenetreMenu fenetreMenu;
    ControlButtonMenu controlb;


    public ControlGroup(Plateau p){

        this.plateau = p;
        this.fenetreMenu = new FenetreMenu(this.plateau);
        this.controlb = new ControlButtonMenu(this.fenetreMenu,plateau);

    }


}
