package application.modele;

public class Sommet {

    private int x;
    private int y;
    private boolean unObstacle;

    public Sommet(int x, int y, boolean estUnObstacle) {
        this.x = x;
        this.y = y;
        this.unObstacle = estUnObstacle;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isUnObstacle() {
        return unObstacle;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sommet other = (Sommet) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
