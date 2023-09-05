import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAnimalDialog extends JDialog implements ActionListener {

    final static String[] ANIMALS = { "Fish", "JellyFish"};
    final static String[] HORSPEED = {"1","2","3","4","5","6","7","8","9","10"};
    final static String[] VERSPEED = {"1","2","3","4","5","6","7","8","9","10"};
    final static String[] EATTIMES={"5","10","15","20"};
    final static String[] ANIMALCOLORS = { "Black", "Red", "Blue", "Green", "Cyan", "Orange", "Yellow", "Magenta", "Pink"};
    static String[] ANIMALSIZE = new String[301];
    private AquaPanel aquaPanel;
    JComboBox<String> animalChoice,animalSizeChoice,horizontalSpeed,verticalSpeed,animalColor, timerChoise;
    JLabel animalName, animalSize,horizontalSpeedLabel,verticalSpeedLabel,animalColorLabel, timerLabel;
    JButton done,cancel;
    String chosenAnimalValue,chosenAnimalColor;
    int chosenAnimalSize,chosenAnimalHorSpeed,chosenAnimalVerSpeed;
    static int place = 0;
    Color colAni;


    public AddAnimalDialog(AquaPanel aquaPanel) {
        this.aquaPanel=aquaPanel;
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7,2,5,5));

        this.setTitle("Add Animal");

        //choose animal type
        animalName = new JLabel("Pick animal:");
        animalChoice = new JComboBox<String>(ANIMALS);
        add(animalName);
        add(animalChoice);

        //choose size of animal
        animalSize = new JLabel("Pick size:");
        for(int i=20;i<321;i++){
            ANIMALSIZE[i-20]= String.valueOf(i);
        }
        animalSizeChoice = new JComboBox<String>(ANIMALSIZE);
        add(animalSize);
        add(animalSizeChoice);

        //horizontal speed of animal
        horizontalSpeedLabel = new JLabel("Horizontal Speed:");
        horizontalSpeed = new JComboBox<String>(HORSPEED);
        add(horizontalSpeedLabel);
        add(horizontalSpeed);

        //vertical speed of animal
        verticalSpeedLabel = new JLabel("Vertical Speed:");
        verticalSpeed = new JComboBox<String>(VERSPEED);
        add(verticalSpeedLabel);
        add(verticalSpeed);

        //choosing animal color
        animalColorLabel = new JLabel("Animal Color:");
        animalColor = new JComboBox<String>(ANIMALCOLORS);
        add(animalColorLabel);
        add(animalColor);

        //choosing animal timer
        timerLabel = new JLabel("Animal Eat Timer:");
        timerChoise = new JComboBox<String>(EATTIMES);
        add(timerLabel);
        add(timerChoise);

        //adding new animal
        done = new JButton( "Done");
        done.setPreferredSize(new Dimension(100,22));
        add(done);
        done.addActionListener(this);

        //canceling the animal
        cancel = new JButton( "Cancel");
        cancel.setPreferredSize(new Dimension(100,22));
        add(cancel);
        cancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(done)) {
            addNewAnimal();
            dispose();

        }
        if (e.getSource().equals(cancel)) {
            dispose();
        }
    }

    /**
     * adding a new animal
     */
    private void addNewAnimal() {
        chosenAnimalValue = animalChoice.getSelectedItem().toString();
        chosenAnimalSize = Integer.parseInt(animalSizeChoice.getSelectedItem().toString());
        chosenAnimalHorSpeed = Integer.parseInt(horizontalSpeed.getSelectedItem().toString());
        chosenAnimalVerSpeed = Integer.parseInt(verticalSpeed.getSelectedItem().toString());
        chosenAnimalColor = animalColor.getSelectedItem().toString();

        if(chosenAnimalColor == "Black") {
            colAni=Color.BLACK;
        }
        if(chosenAnimalColor == "Red") {
            colAni=Color.RED;
        }
        if(chosenAnimalColor == "Blue") {
            colAni=Color.BLUE;
        }
        if(chosenAnimalColor == "Green") {
           colAni= Color.GREEN;
        }
        if(chosenAnimalColor == "Cyan") {
            colAni=Color.CYAN;
        }
        if(chosenAnimalColor == "Orange" ) {
            colAni = Color.orange;
        }
        if(chosenAnimalColor == "Yellow" ) {
            colAni = Color.yellow;
        }
        if(chosenAnimalColor == "Magenta") {
            colAni = Color.MAGENTA;
        }
        if(chosenAnimalColor == "Pink" ) {
            colAni=Color.pink;
        }

        if (chosenAnimalValue == "Fish"){
            Fish fish = new Fish(Integer.parseInt(timerChoise.getSelectedItem().toString()),chosenAnimalSize,0,0,chosenAnimalHorSpeed,chosenAnimalVerSpeed,colAni, aquaPanel);
            fish.start();
            aquaPanel.addAnimal(fish);
            aquaPanel.infoTable(chosenAnimalValue, chosenAnimalColor, chosenAnimalSize, chosenAnimalHorSpeed, chosenAnimalVerSpeed, fish.getEatCount(), place);
            place++;

        }
        if (chosenAnimalValue == "JellyFish"){
            Jellyfish jellyFish = new Jellyfish(Integer.parseInt(timerChoise.getSelectedItem().toString()),chosenAnimalSize,0,0,chosenAnimalHorSpeed,chosenAnimalVerSpeed,colAni, aquaPanel);
            jellyFish.start();
            aquaPanel.addAnimal(jellyFish);
            aquaPanel.infoTable(chosenAnimalValue, chosenAnimalColor, chosenAnimalSize, chosenAnimalHorSpeed, chosenAnimalVerSpeed, jellyFish.getEatCount(), place);
            place++;
        }

    }


}