package application.controleur;

import application.modele.Grille;
import application.modele.ObstacleException;
import application.modele.Sommet;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    public final static int TUILE_TAILLE = 30;

    private Grille grille;
    private TranslateTransition tt;

    @FXML private StackPane root;
    @FXML private Pane paneDecor;
    @FXML private Pane panePerso;
    @FXML private Label labelVictoire;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grille = new Grille(13,13);
        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(grille.getPerso(), this));
        construireGrille();
        construirePerso();
        tt = new TranslateTransition();
        tt.setNode(panePerso.getChildren().get(0));
        tt.setDuration(Duration.millis(300));
    }

    private void construirePerso() {
        Rectangle rectangle = new Rectangle(grille.getPerso().getX() * TUILE_TAILLE, grille.getPerso().getY() * TUILE_TAILLE, TUILE_TAILLE, TUILE_TAILLE);
        rectangle.setFill(Color.BLACK);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStroke(Color.WHITE);
        rectangle.setStrokeWidth(7);
        panePerso.getChildren().add(rectangle);
    }

    private void construireGrille() {
        Rectangle rectangle;
        for (Sommet s : grille.getListeSommets()) {
            rectangle = new Rectangle(s.getX() * TUILE_TAILLE, s.getY() * TUILE_TAILLE, TUILE_TAILLE, TUILE_TAILLE);
            if (s.isUnObstacle())
                rectangle.setFill(Color.BLACK);
            else {
                rectangle.setFill(Color.WHITE);
                rectangle.setStrokeType(StrokeType.INSIDE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(1);
            }
            paneDecor.getChildren().add(rectangle);
        }
    }

    public void seDeplacer() {
        try {
            if (tt.getCurrentRate() == 0 && grille.getPerso().getDirection() != null) {
                grille.getPerso().seDeplacer();
                switch (grille.getPerso().getDirection()) {
                    case haut: tt.setByY(-TUILE_TAILLE); tt.setByX(0); break;
                    case bas: tt.setByY(TUILE_TAILLE); tt.setByX(0); break;
                    case gauche: tt.setByX(-TUILE_TAILLE); tt.setByY(0); break;
                    case droite: tt.setByX(TUILE_TAILLE); tt.setByY(0); break;
                    default: break;
                }
                tt.play();
            }
            if (grille.getPerso().isGagne())
                labelVictoire.toFront();
        } catch (ObstacleException e) {}
    }
}
