public class ControlGroupJeu {

    Plateau plateau;
    FenetreJeu fenetreJeu;
    ControlButtonJeu controlb;


    public ControlGroupJeu(Plateau p){

        this.plateau = p;
        this.fenetreJeu = new FenetreJeu(this.plateau);
        this.controlb = new ControlButtonJeu(this.fenetreJeu,plateau);

    }

}
