public class AnimalFactory implements AbstractSeaFactory {

    @Override
    public SeaCreature produceSeaCreature(String type) {
        if (type.equals("Fish")) // if the msg type is fish then create new fish and return it
            return new Fish();
        return new Jellyfish(); // if not - return jellyfish
    }
}
