import java.awt.*;

public abstract class Immobile implements SeaCreature {
    public static int countPlants = 0;
    String name;
    protected Color colorr;
    protected int x, y, size;

    public Immobile(String name, Color colorr, int x, int y, int size) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.size = size;
        this.colorr = colorr;
    }

    @Override
    public abstract SeaCreature clone();

    public int getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}