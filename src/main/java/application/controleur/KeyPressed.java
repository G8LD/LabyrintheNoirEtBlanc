package application.controleur;

import application.modele.Dir;
import application.modele.Personnage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;


public class KeyPressed implements EventHandler<KeyEvent> {

    private Personnage perso;
    private Controleur controleur;

    public KeyPressed(Personnage perso, Controleur controleur) {
        this.perso = perso;
        this.controleur = controleur;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case Z: perso.setDirection(Dir.haut); break;
            case S: perso.setDirection(Dir.bas); break;
            case Q: perso.setDirection(Dir.gauche); break;
            case D: perso.setDirection(Dir.droite); break;
            default: perso.setDirection(null); break;
        }
        controleur.seDeplacer();
    }
}
