package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {

    private Grille grille;
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private Dir direction;
    private boolean gagne;

    public Personnage(Grille grille, int x, int y) {
        this.grille = grille;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        direction = null;
        gagne = false;
    }

    public void seDeplacer() throws ObstacleException {
        int dX, dY;

        switch (direction) {
            case haut : dX = 0; dY = -1; break;
            case bas : dX = 0; dY = 1; break;
            case gauche : dX = -1; dY = 0; break;
            case droite : dX = 1; dY = 0; break;
            default: dX = 0; dY = 0; break;
        }

        if (grille.estUnObstacle(getX() + dX, getY() + dY))
            throw new ObstacleException();
        else {
            xProperty.setValue(getX() + dX);
            yProperty.setValue(getY() + dY);
        }
        if (getX() == 12 && getY() == 5)
            gagne = true;
    }

    public final int getX() {
        return xProperty.getValue();
    }

    public final IntegerProperty getxProperty() {
        return xProperty;
    }

    public final int getY() {
        return yProperty.getValue();
    }

    public final IntegerProperty getyProperty() {
        return yProperty;
    }

    public Dir getDirection() {
        return direction;
    }

    public void setDirection(Dir direction) {
        this.direction = direction;
    }

    public boolean isGagne() {
        return gagne;
    }
}
