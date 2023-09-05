import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CyclicBarrier;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AquaPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Singleton isWormInAqua = null;
    private int openInfo = 0;
    private String total;
    private JButton buttons[];
    private JTable table;
    private DefaultTableModel model;
    private Image image;
    private HashSet<Swimmable> allAnimals = new HashSet<>();
    private ArrayList<Immobile> allPlants = new ArrayList<>();
    Iterator<Swimmable> itrAnimals;
    Iterator<Immobile> itrPlants;
    CyclicBarrier cyclicBarrier;
    private String[][] infoData = new String[6][6];
    private String[] tryInfo = new String[6];
    private BorderLayout myBorderLayout = new BorderLayout();
    private int totalEatCount;
    private JPanel tableJPanel;
    private JScrollPane jScrollPane;

    private final static String[] plantsName = {"Zostera", "Laminaria"};

    public HashSet<Swimmable> getRealAnimals(){return allAnimals;}
    public ArrayList<Swimmable> getAnimals() {
        return new ArrayList<>(allAnimals);
    }

    public ArrayList<Immobile> getPlants() {
        return allPlants;
    }
    /**
     * adding panel with buttons
     */
    public AquaPanel() {
        setBackground(Color.white);
        setLayout(myBorderLayout);

        buttons = new JButton[10];

        JPanel buttonJPanel = new JPanel();
        tableJPanel = new JPanel();

        buttonJPanel.setLayout(new GridLayout(0, 10, 0, 0));
        buttonJPanel.setBackground(Color.white);

        tableJPanel.setBackground(Color.white);

        buttons[0] = new JButton("Add Animal");
        buttons[1] = new JButton("Sleep");
        buttons[2] = new JButton("Wake up");
        buttons[3] = new JButton("Reset");
        buttons[4] = new JButton("Food");
        buttons[5] = new JButton("Info");
        buttons[6] = new JButton("Duplicate Animal");
        buttons[7] = new JButton("Add Plant");
        buttons[8] = new JButton("Decorator");
        buttons[9] = new JButton("Exit");

        buttonJPanel.add(buttons[0]);
        buttonJPanel.add(buttons[1]);
        buttonJPanel.add(buttons[2]);
        buttonJPanel.add(buttons[3]);
        buttonJPanel.add(buttons[4]);
        buttonJPanel.add(buttons[5]);
        buttonJPanel.add(buttons[6]);
        buttonJPanel.add(buttons[7]);
        buttonJPanel.add(buttons[8]);
        buttonJPanel.add(buttons[9]);

        buttonJPanel.setPreferredSize(new Dimension(100, 22));
        add(buttonJPanel, myBorderLayout.SOUTH);
        add(tableJPanel, myBorderLayout.CENTER);

        buttons[0].addActionListener(this);
        buttons[1].addActionListener(this);
        buttons[2].addActionListener(this);
        buttons[3].addActionListener(this);
        buttons[4].addActionListener(this);
        buttons[5].addActionListener(this);
        buttons[6].addActionListener(this);
        buttons[7].addActionListener(this);
        buttons[8].addActionListener(this);
        buttons[9].addActionListener(this);

        buttonJPanel.setVisible(true);
        tableJPanel.setVisible(false);

    }

    /**
     * @return flag if the worm is in the aquarium
     */
    // check if worm non null because now it's a singletion
    public boolean isWormInAqua() {
        return isWormInAqua != null; // Singleton !
    }

    public void actionPerformed(ActionEvent e) {
        /**
         * add animal
         */
        if (e.getSource().equals(buttons[0])) {
            if (allAnimals.size() > 4) {
                JOptionPane.showMessageDialog(this, "Error! Can't Add More Animals!");
            } else {
                AddAnimalDialog animalDialog = new AddAnimalDialog(this);
                animalDialog.pack();
                animalDialog.setVisible(true);
            }
        }
        /**
         * sleep
         */
        if (e.getSource().equals(buttons[1])) {
            itrAnimals = allAnimals.iterator();
            while (itrAnimals.hasNext()) {
                itrAnimals.next().setSuspend();
            }
        }
        /**
         * wake up
         */
        if (e.getSource().equals(buttons[2])) {
            itrAnimals = allAnimals.iterator();
            while (itrAnimals.hasNext()) {
                itrAnimals.next().setResume();
            }
        }
        /**
         * reset
         */
        if (e.getSource().equals(buttons[3])) {
            allAnimals.clear();
            infoData = new String[6][6];
            totalEatCount = 0;
            isWormInAqua = null; // update to null instance
            AddAnimalDialog.place = 0;
            repaint();
        }
        /**
         * food
         */
        if (e.getSource().equals(buttons[4])) {
            if (allAnimals.size() > 0) {
                isWormInAqua = Singleton.getInstance(); // create the instance of the worm
                cyclicBarrier = new CyclicBarrier(allAnimals.size());
                itrAnimals = allAnimals.iterator();
                while (itrAnimals.hasNext()) {
                    itrAnimals.next().setBarrier(cyclicBarrier);
                }
            }

        }
        /**
         * info
         */
        if (e.getSource().equals(buttons[5])) {
            if (openInfo % 2 == 0) {
                makeAnInfoTable();
                tableJPanel.setVisible(true);
            } else {
                tableJPanel.setVisible(false);
            }
            openInfo++;


        }
        /**
         * Duplicate Animal
         */
        if (e.getSource().equals(buttons[6])) {
            if (allAnimals.size() > 4) {
                JOptionPane.showMessageDialog(this, "Error! Can't Add More Animals!");
            } else {
                JFrame jFrame = new JFrame("Duplicate Selection");
                JPanel jPanel = new JPanel();
                jFrame.setSize(150, 120);

                JButton fishBtn = new JButton("Fish"), jellyfishBtn = new JButton("JellyFish");
                jPanel.add(fishBtn);
                jPanel.add(jellyfishBtn);

                jFrame.add(jPanel);
                jFrame.setVisible(true);

                fishBtn.addActionListener(e1 -> {
                    int fishSize = 0;
                    for (int i = 0; i < allAnimals.size(); i++)
                        if (allAnimals.toArray()[i] instanceof Fish)
                            fishSize++;

                    JFrame fish = new JFrame("Fish");
                    JPanel panel = new JPanel();
                    fish.setSize(100, 50 * fishSize + 50);

                    ArrayList<JRadioButton> buttons = new ArrayList<>();
                    for (int i = 0; i < allAnimals.size(); i++)
                        if (allAnimals.toArray()[i] instanceof Fish)
                            buttons.add(new JRadioButton("Fish: " + ((Fish) allAnimals.toArray()[i]).getAnimalName()));

                    for (JRadioButton jRadioButton : buttons)
                        panel.add(jRadioButton);

                    JButton ok = new JButton("DONE");
                    panel.add(ok);

                    ok.addActionListener(e2 -> {
                        for (int i = 0; i < buttons.size(); i++) {
                            if (buttons.get(i).isSelected()) {
                                Swimmable fish1 = (Swimmable) ((Swimmable) allAnimals.toArray()[i]).clone();
                                allAnimals.add(fish1);
                                changeFishValues(fish1);
                                fish1.start();
                                for (int j = 0; j < allAnimals.size(); j++) {
                                    Swimmable swimmable = (Swimmable) allAnimals.toArray()[j];
                                    infoTable(swimmable.getAnimalName(), swimmable.getColor(), swimmable.getSize(),
                                            swimmable.getHorSpeed(), swimmable.getverSpeed(), swimmable.getEatCount(),
                                            j);
                                }
                            }
                        }
                        fish.setVisible(false);
                        jFrame.setVisible(false);
                    });
                    fish.add(panel);
                    fish.setVisible(true);
                });

                jellyfishBtn.addActionListener(e1 -> {
                    int fishSize = 0;
                    for (int i = 0; i < allAnimals.size(); i++)
                        if (allAnimals.toArray()[i] instanceof Jellyfish)
                            fishSize++;

                    JFrame fish = new JFrame("Jellyfish");
                    JPanel panel = new JPanel();
                    fish.setSize(100, 50 * fishSize + 50);

                    ArrayList<JRadioButton> buttons = new ArrayList<>();
                    for (int i = 0; i < allAnimals.size(); i++)
                        if (allAnimals.toArray()[i] instanceof Jellyfish)
                            buttons.add(new JRadioButton("Jellyfish: " + ((Jellyfish) allAnimals.toArray()[i]).getAnimalName()));

                    for (JRadioButton jRadioButton : buttons)
                        panel.add(jRadioButton);

                    JButton ok = new JButton("DONE");
                    panel.add(ok);

                    ok.addActionListener(e2 -> {
                        for (int i = 0; i < buttons.size(); i++) {
                            if (buttons.get(i).isSelected()) {
                                Swimmable fish1 = (Swimmable) ((Swimmable) allAnimals.toArray()[i]).clone();
                                allAnimals.add(fish1);
                                changeFishValues(fish1);
                                fish1.start();
                                for (int j = 0; j < allAnimals.size(); j++) {
                                    Swimmable swimmable = (Swimmable) allAnimals.toArray()[j];
                                    infoTable(swimmable.getAnimalName(), swimmable.getColor(), swimmable.getSize(),
                                            swimmable.getHorSpeed(), swimmable.getverSpeed(), swimmable.getEatCount(),
                                            j);
                                }
                            }
                        }
                        fish.setVisible(false);
                        jFrame.setVisible(false);
                    });
                    fish.add(panel);
                    fish.setVisible(true);
                });
            }
        }
        /**
         * add plant
         */
        if (e.getSource().equals(buttons[7])) {
            if (allAnimals.size() > 4) {
                JOptionPane.showMessageDialog(this, "Error! Can't Add More Plants!");
            } else {
                JFrame jFrame = new JFrame("Add Plant");
                JPanel jPanel = new JPanel();
                jFrame.setSize(150, 150);

                JButton zostera = new JButton("Zostera"), laminaria = new JButton("Laminaria");
                JLabel animalSize = new JLabel("Pick size:");
                for (int i = 20; i < 321; i++) {
                    AddAnimalDialog.ANIMALSIZE[i - 20] = String.valueOf(i);
                }
                JComboBox plantSizeChoice = new JComboBox<>(AddAnimalDialog.ANIMALSIZE);
                jPanel.add(animalSize);
                jPanel.add(plantSizeChoice);

                jPanel.add(zostera);
                jPanel.add(laminaria);

                jFrame.add(jPanel);
                jFrame.setVisible(true);

                zostera.addActionListener(e1 -> {
                    allPlants.add(new Zostera("Zosetra", Color.GREEN,
                            new Random().nextInt(this.getWidth()), new Random().nextInt(this.getHeight()),
                            Integer.parseInt(plantSizeChoice.getSelectedItem().toString())));
                    repaint();
                    jFrame.setVisible(false);
                });

                laminaria.addActionListener(e1 -> {
                    allPlants.add(new Laminaria("Laminaria", Color.GREEN,
                            new Random().nextInt(this.getWidth()), new Random().nextInt(this.getHeight()),
                            Integer.parseInt(plantSizeChoice.getSelectedItem().toString())));
                    repaint();
                    jFrame.setVisible(false);
                });
            }
        }
        /**
         * Decorator
         */
        if (e.getSource().equals(buttons[8])) {
            JFrame frame = new JFrame("Animal Decorator");
            JPanel panel = new JPanel();

            frame.setSize(6 * 2 * 75, 5 * 40);
            frame.setLayout(new GridLayout(5, 6 * 2, 5, 5));

            ArrayList<Swimmable> animals = new ArrayList<>(allAnimals);
            ArrayList<JButton> buttons = new ArrayList<>();
            for (int i = 0; i < allAnimals.size(); i++) {
                //choose animal type
                {
                    JLabel animalName = new JLabel("Animal Type:");
                    JLabel animalChoice = new JLabel(animals.get(i).getClass().getName() + "|");
                    panel.add(animalName);
                    panel.add(animalChoice);

                    //choose size of animal
                    JLabel animalSize = new JLabel("Size:");
                    JLabel animalSizeChoice = new JLabel("" + animals.get(i).getSize() + "|");
                    panel.add(animalSize);
                    panel.add(animalSizeChoice);

                    //horizontal speed of animal
                    JLabel horizontalSpeedLabel = new JLabel("Horizontal Speed:");
                    JLabel horizontalSpeed = new JLabel("" + animals.get(i).getHorSpeed() + "|");
                    panel.add(horizontalSpeedLabel);
                    panel.add(horizontalSpeed);

                    //vertical speed of animal
                    JLabel verticalSpeedLabel = new JLabel("Vertical Speed:");
                    JLabel verticalSpeed = new JLabel("" + animals.get(i).getverSpeed() + "|");
                    panel.add(verticalSpeedLabel);
                    panel.add(verticalSpeed);

                    //choosing animal color
                    JLabel animalColorLabel = new JLabel("Animal Color:");
                    JLabel animalColor = new JLabel(animals.get(i).getColor() + "|");
                    panel.add(animalColorLabel);
                    panel.add(animalColor);

                    //choosing animal color
                    JLabel timerLabel = new JLabel("Animal Eat Timer:");
                    JLabel timerChoice = new JLabel("" + animals.get(i).getEatTime() + "|");
                    panel.add(timerLabel);
                    panel.add(timerChoice);
                }
                buttons.add(new JButton("Change Color"));
                int finalI = i;

                ActionListener listener = e14 -> {
                    JFrame jframe = new JFrame("Animal Decorator");
                    JPanel jpanel = new JPanel();
                    jframe.setSize(150, 100);
                    jpanel.setLayout(new GridLayout(2, 2, 5, 5));

                    JLabel colorChoice = new JLabel("Choose Color:");
                    JComboBox colors = new JComboBox<>(new String[]{"Black", "Red", "Blue", "Green", "Cyan", "Orange", "Yellow", "Magenta", "Pink"});
                    JButton button = new JButton("OK");

                    jpanel.add(colorChoice);
                    jpanel.add(colors);
                    jpanel.add(button);

                    button.addActionListener(e13 -> {
                        MarineAnimal animal = new MarineAnimalDecorator((MarineAnimal) animals.get(finalI));

                        Color colAni = null;
                        if (colors.getSelectedItem().toString() == "Black") {
                            colAni = Color.BLACK;
                        }
                        if (colors.getSelectedItem().toString() == "Red") {
                            colAni = Color.RED;
                        }
                        if (colors.getSelectedItem().toString() == "Blue") {
                            colAni = Color.BLUE;
                        }
                        if (colors.getSelectedItem().toString() == "Green") {
                            colAni = Color.GREEN;
                        }
                        if (colors.getSelectedItem().toString() == "Cyan") {
                            colAni = Color.CYAN;
                        }
                        if (colors.getSelectedItem().toString() == "Orange") {
                            colAni = Color.orange;
                        }
                        if (colors.getSelectedItem().toString() == "Yellow") {
                            colAni = Color.yellow;
                        }
                        if (colors.getSelectedItem().toString() == "Magenta") {
                            colAni = Color.MAGENTA;
                        }
                        if (colors.getSelectedItem().toString() == "Pink") {
                            colAni = Color.pink;
                        }

                        animal.paintFish(colAni);
                        jframe.setVisible(false);
                    });

                    jframe.add(jpanel);
                    jframe.setVisible(true);
                };
                buttons.get(i).addActionListener(listener);

                panel.add(buttons.get(i));
                frame.add(panel);
                frame.setVisible(true);
            }
        }
        /**
         * exit
         */
        if (e.getSource().equals(buttons[9])) {
            System.exit(0);
        }
    }

    private void changeFishValues(Swimmable fish) {
        JFrame jFrame = new JFrame();
        JPanel panel = new JPanel();
        jFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jFrame.setSize(200, 350);
        jFrame.setTitle("Add Animal");

        panel.setLayout(new GridLayout(7, 2, 5, 5));

        //choose animal type
        JLabel animalName = new JLabel("Pick animal:");
        JComboBox animalChoice = new JComboBox<String>(AddAnimalDialog.ANIMALS);
        panel.add(animalName);
        panel.add(animalChoice);

        //choose size of animal
        JLabel animalSize = new JLabel("Pick size:");
        for (int i = 20; i < 321; i++) {
            AddAnimalDialog.ANIMALSIZE[i - 20] = String.valueOf(i);
        }
        JComboBox animalSizeChoice = new JComboBox<String>(AddAnimalDialog.ANIMALSIZE);
        panel.add(animalSize);
        panel.add(animalSizeChoice);

        //horizontal speed of animal
        JLabel horizontalSpeedLabel = new JLabel("Horizontal Speed:");
        JComboBox horizontalSpeed = new JComboBox<String>(AddAnimalDialog.HORSPEED);
        panel.add(horizontalSpeedLabel);
        panel.add(horizontalSpeed);

        //vertical speed of animal
        JLabel verticalSpeedLabel = new JLabel("Vertical Speed:");
        JComboBox verticalSpeed = new JComboBox<String>(AddAnimalDialog.VERSPEED);
        panel.add(verticalSpeedLabel);
        panel.add(verticalSpeed);

        //choosing animal color
        JLabel animalColorLabel = new JLabel("Animal Color:");
        JComboBox animalColor = new JComboBox<String>(AddAnimalDialog.ANIMALCOLORS);
        panel.add(animalColorLabel);
        panel.add(animalColor);

        //choosing animal color
        JLabel timerLabel = new JLabel("Animal Eat Timer:");
        JComboBox timerChoice = new JComboBox<String>(AddAnimalDialog.EATTIMES);
        panel.add(timerLabel);
        panel.add(timerChoice);

        //adding new animal
        JButton done = new JButton("Done");
        done.setPreferredSize(new Dimension(100, 22));
        panel.add(done);
        done.addActionListener(e -> {
            fish.setName(animalChoice.getSelectedItem().toString());
            fish.setSize(Integer.parseInt(animalSizeChoice.getSelectedItem().toString()));
            fish.setHorSpeed(Integer.parseInt(horizontalSpeed.getSelectedItem().toString()));
            fish.setverSpeed(Integer.parseInt(verticalSpeed.getSelectedItem().toString()));

            Color colAni = null;
            if (animalColor.getSelectedItem().toString() == "Black") {
                colAni = Color.BLACK;
            }
            if (animalColor.getSelectedItem().toString() == "Red") {
                colAni = Color.RED;
            }
            if (animalColor.getSelectedItem().toString() == "Blue") {
                colAni = Color.BLUE;
            }
            if (animalColor.getSelectedItem().toString() == "Green") {
                colAni = Color.GREEN;
            }
            if (animalColor.getSelectedItem().toString() == "Cyan") {
                colAni = Color.CYAN;
            }
            if (animalColor.getSelectedItem().toString() == "Orange") {
                colAni = Color.orange;
            }
            if (animalColor.getSelectedItem().toString() == "Yellow") {
                colAni = Color.yellow;
            }
            if (animalColor.getSelectedItem().toString() == "Magenta") {
                colAni = Color.MAGENTA;
            }
            if (animalColor.getSelectedItem().toString() == "Pink") {
                colAni = Color.pink;
            }
            fish.setColor(colAni);
            fish.setEatTime(Integer.parseInt(timerChoice.getSelectedItem().toString()));
            jFrame.setVisible(false);
        });

        //canceling the animal
        JButton cancel = new JButton("Cancel");
        cancel.setPreferredSize(new Dimension(100, 22));
        panel.add(cancel);
        cancel.addActionListener(e -> {
            jFrame.setVisible(false);
        });

        jFrame.add(panel);
        jFrame.setVisible(true);
    }

    /**
     * creating JTable of info
     */
    private void makeAnInfoTable() {
        String[] columnNames = {"Animal",
                "Color",
                "Size",
                "Hor. speed",
                "Ver. speed",
                "Eat counter"};
        model = new DefaultTableModel(infoData, columnNames);
        table = new JTable(model);

        total = String.valueOf(totalEatCount);
        model.addRow(new Object[]{"Total", "", "", "", "", total});
        jScrollPane = new JScrollPane(table);
        tableJPanel.removeAll();
        tableJPanel.add(jScrollPane, myBorderLayout.CENTER);

    }

    /**
     * inserts data of swimmable to the data table
     *
     * @param animal
     * @param color
     * @param size
     * @param horspeed
     * @param verspeed
     * @param eatcounter
     * @param place
     */
    public void infoTable(String animal, String color, int size, int horspeed, int verspeed, int eatcounter,
                          int place) {
        infoData[place][0] = animal;
        infoData[place][1] = color;
        infoData[place][2] = String.valueOf(size);
        infoData[place][3] = String.valueOf(horspeed);
        infoData[place][4] = String.valueOf(verspeed);
        infoData[place][5] = String.valueOf(eatcounter);
        totalEatCount = totalEatCount + eatcounter;

    }

    /**
     * set imag for background
     *
     * @param img
     */
    public void setImage(Image img) {
        image = img;
        this.repaint();
    }

    /**
     * for switching between backgrounds
     *
     * @param img
     */
    public void resetImage(Image img) {
        image = null;
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, 1080, 720, this);
        }
        itrAnimals = allAnimals.iterator();
        while (itrAnimals.hasNext()) {
            itrAnimals.next().drawCreature(g);
        }
        itrPlants = allPlants.iterator();
        while (itrPlants.hasNext()) {
            itrPlants.next().drawCreature(g);
        }
        if (isWormInAqua != null) { // if worm doesnt exist
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.red);
            g2.drawArc(getWidth() / 2, getHeight() / 2 - 5, 10, 10, 30, 210);
            g2.drawArc(getWidth() / 2, getHeight() / 2 + 5, 10, 10, 180, 270);
            g2.setStroke(new BasicStroke(1));
        }
    }

    /**
     * add the swimmable to hashset
     *
     * @param swimmable
     */
    public void addAnimal(Swimmable swimmable) {
        allAnimals.add(swimmable);
        repaint();
    }

    /**
     * updates eat counter and total
     *
     * @param swimmable
     */
    public void callback(Swimmable swimmable) {
        isWormInAqua = null; // remove worm
        swimmable.eatInc();
        checkWhoTheSwim(swimmable);
        itrAnimals = allAnimals.iterator();
        while (itrAnimals.hasNext()) {
            itrAnimals.next().setBarrier(null);
        }
    }

    private void checkWhoTheSwim(Swimmable swimmable) {
        for (int i = 0; i < infoData.length; i++) {
            if (swimmable.getAnimalName() == infoData[i][0] && swimmable.getColor() == infoData[i][1] && String.valueOf(swimmable.getHorSpeed()).equals(infoData[i][3])
                    && String.valueOf(swimmable.getverSpeed()).equals(infoData[i][4])) {
                infoData[i][2] = String.valueOf(swimmable.getSize());
                infoData[i][5] = String.valueOf(swimmable.getEatCount());
                totalEatCount++;

            }

        }
    }

}
