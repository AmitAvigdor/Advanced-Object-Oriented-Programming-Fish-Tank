import java.awt.*;

public class Laminaria extends Immobile{
    public Laminaria(String name, Color color, int x, int y, int size) {
        super(name, color, x, y, size);
    }

    @Override
    public SeaCreature clone() {
        return new Laminaria(name, colorr,x,y,size);
    }

    @Override
    public void drawCreature(Graphics g) {
        g.setColor(colorr);
        g.fillArc(x - size / 20, y - size, size / 10, size * (4 / 5), 0, 360);
        g.fillArc(x - size * (3 / 20), y - size * (13 / 15), size / 10, size * (2 / 3), 0, 360);
        g.fillArc(x + size / 20, y - size * (13 / 15), size / 10, size * (2 / 3), 0, 360);
        g.drawLine(x, y, x, y - size / 5);
        g.drawLine(x, y, x - size / 10, y - size / 5);
        g.drawLine(x, y, x + size / 10, y - size / 5);
    }
}
