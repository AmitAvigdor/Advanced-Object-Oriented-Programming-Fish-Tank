import java.awt.*;

public class MarineAnimalDecorator implements MarineAnimal{
    protected MarineAnimal animal;

    public MarineAnimalDecorator(MarineAnimal animal) {
        this.animal = animal;
    }

    @Override
    public void paintFish(Color color) {
        animal.paintFish(color);
    }
}
