import java.awt.*;

public class PlantFactory implements AbstractSeaFactory {
    @Override
    public SeaCreature produceSeaCreature(String type) {
        if (type.equals("Zostera")) // if type is Zostera then create it
            return new Zostera("Amit", Color.GREEN,0,0,50);
        return new Laminaria("Daniel", Color.GREEN,0,0,50);
    }
}
