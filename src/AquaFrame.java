//Assignment 2 Amit Avigdor 316178144 & Daniel Efrat Hajaj 209471028

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.ImageIO;

public class AquaFrame extends JFrame implements ActionListener {
    private JButton buttons[];
    private BufferedImage image;
    AquaPanel displayPanel;
    JMenu x, y, w, t;
    JMenuItem x1, y1, w1, w2, w3, t1, t2;
    JMenuBar z;
    static JButton a;
    ArrayList<Swimmable> savedAnimals;
    ArrayList<Immobile> savedPlants;

    public static void main(String[] args) {
        AquaFrame frame = new AquaFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }

    /**
     * adding frame with buttons
     */
    public AquaFrame() {
        super("my Aquarium");
        setSize(800, 500);
        setVisible(true);
        x = new JMenu("File");
        y = new JMenu("Help");
        w = new JMenu("Background");
        t = new JMenu("Memento");
        t1 = new JMenuItem("Save Object State");
        t2 = new JMenuItem("Restore Object State");
        t1.addActionListener(this);
        t2.addActionListener(this);
        x1 = new JMenuItem("Exit");
        x1.addActionListener(this);
        y1 = new JMenuItem("Help");
        y1.addActionListener(this);
        w1 = new JMenuItem("Image");
        w1.addActionListener(this);
        w2 = new JMenuItem("Blue");
        w2.addActionListener(this);
        w3 = new JMenuItem("None");
        w3.addActionListener(this);
        x.add(x1);
        y.add(y1);
        w.add(w1);
        w.add(w2);
        w.add(w3);
        t.add(t1);
        t.add(t2);
        z = new JMenuBar();
        z.add(x);
        z.add(w);
        z.add(y);
        z.add(t);
        setJMenuBar(z);

        // Create the main panel
        displayPanel = new AquaPanel();
        add(displayPanel);
        loadImages();
        savedAnimals = new ArrayList<>();
        savedPlants = new ArrayList<>();
    }

    /**
     * loading image for background
     */
    private void loadImages() {
        try {
            image = ImageIO.read(getClass().getResource("aquariumJpg.jpeg"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(x1)) {
            System.exit(0);
        }
        if (e.getSource().equals(y1)) {
            JOptionPane.showMessageDialog(this, "Home Work 3 " + "\n" + "GUI @ Threads");
        }
        if (e.getSource().equals(w1)) {
            displayPanel.setImage(image);

        }
        if (e.getSource().equals(w2)) {
            displayPanel.resetImage(image);
            displayPanel.setBackground(Color.BLUE);
        }
        if (e.getSource().equals(w3)) {
            displayPanel.resetImage(image);
            displayPanel.setBackground(Color.WHITE);
        }
        if (e.getSource().equals(t1)) {
            JFrame frame = new JFrame("Animal&Plants Viewer");
            JPanel panel = new JPanel();

            frame.setSize(6 * 2 * 75, 5 * 40);
            frame.setLayout(new GridLayout(5, 6 * 2, 5, 5));

            ArrayList<Swimmable> animals = new ArrayList<>(displayPanel.getAnimals());
            ArrayList<Immobile> plants = (ArrayList<Immobile>) displayPanel.getPlants().clone();

            ArrayList<JButton> buttons = new ArrayList<>();
            for (int i = 0; i < animals.size(); i++) {
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
                buttons.add(new JButton("Save Animal"));
                int finalI = i;

                ActionListener listener = e14 -> {
                    savedAnimals.add((Swimmable) animals.get(finalI).clone());
                    frame.setVisible(false);
                };
                buttons.get(i).addActionListener(listener);

                panel.add(buttons.get(i));
                frame.add(panel);
                frame.setVisible(true);
            }

            for (int i = 0; i < plants.size(); i++) {
                {
                    JLabel animalName = new JLabel("Plant Type:");
                    JLabel animalChoice = new JLabel(plants.get(i).getClass().getName() + "|");
                    panel.add(animalName);
                    panel.add(animalChoice);

                    //choose size of animal
                    JLabel animalSize = new JLabel("Size:");
                    JLabel animalSizeChoice = new JLabel("" + plants.get(i).getSize() + "|");
                    panel.add(animalSize);
                    panel.add(animalSizeChoice);

                    //horizontal speed of animal
                    JLabel horizontalSpeedLabel = new JLabel("X:");
                    JLabel horizontalSpeed = new JLabel("" + plants.get(i).getX() + "|");
                    panel.add(horizontalSpeedLabel);
                    panel.add(horizontalSpeed);

                    //vertical speed of animal
                    JLabel verticalSpeedLabel = new JLabel("Y:");
                    JLabel verticalSpeed = new JLabel("" + plants.get(i).getY() + "|");
                    panel.add(verticalSpeedLabel);
                    panel.add(verticalSpeed);

                    //choosing animal color
                    JLabel animalColorLabel = new JLabel("Plant Color:");
                    JLabel animalColor = new JLabel("Green|");
                    panel.add(animalColorLabel);
                    panel.add(animalColor);
                }
                buttons.add(new JButton("Save Plant"));
                int finalI = i;

                ActionListener listener = e14 -> {
                    savedPlants.add((Immobile) plants.get(finalI).clone());
                    frame.setVisible(false);
                };
                buttons.get(i).addActionListener(listener);

                panel.add(buttons.get(i));
                frame.add(panel);
                frame.setVisible(true);
            }
        }
        if (e.getSource().equals(t2)) {
            JFrame frame = new JFrame("Saved Viewer");
            JPanel panel = new JPanel();

            frame.setSize(6 * 2 * 75, 5 * 40);
            frame.setLayout(new GridLayout(5, 6 * 2, 5, 5));

            ArrayList<JButton> buttons = new ArrayList<>();
            for (int i = 0; i < savedAnimals.size(); i++) {
                {
                    JLabel animalName = new JLabel("Animal Type:");
                    JLabel animalChoice = new JLabel(savedAnimals.get(i).getClass().getName() + "|");
                    panel.add(animalName);
                    panel.add(animalChoice);

                    //choose size of animal
                    JLabel animalSize = new JLabel("Size:");
                    JLabel animalSizeChoice = new JLabel("" + savedAnimals.get(i).getSize() + "|");
                    panel.add(animalSize);
                    panel.add(animalSizeChoice);

                    //horizontal speed of animal
                    JLabel horizontalSpeedLabel = new JLabel("Horizontal Speed:");
                    JLabel horizontalSpeed = new JLabel("" + savedAnimals.get(i).getHorSpeed() + "|");
                    panel.add(horizontalSpeedLabel);
                    panel.add(horizontalSpeed);

                    //vertical speed of animal
                    JLabel verticalSpeedLabel = new JLabel("Vertical Speed:");
                    JLabel verticalSpeed = new JLabel("" + savedAnimals.get(i).getverSpeed() + "|");
                    panel.add(verticalSpeedLabel);
                    panel.add(verticalSpeed);

                    //choosing animal color
                    JLabel animalColorLabel = new JLabel("Animal Color:");
                    JLabel animalColor = new JLabel(savedAnimals.get(i).getColor() + "|");
                    panel.add(animalColorLabel);
                    panel.add(animalColor);

                    //choosing animal color
                    JLabel timerLabel = new JLabel("Animal Eat Timer:");
                    JLabel timerChoice = new JLabel("" + savedAnimals.get(i).getEatTime() + "|");
                    panel.add(timerLabel);
                    panel.add(timerChoice);
                }
                buttons.add(new JButton("Save Animal"));
                int finalI = i;

                ActionListener listener = e14 -> {
                    displayPanel.getRealAnimals().remove(displayPanel.getAnimals().get(finalI));
                    displayPanel.getRealAnimals().add(savedAnimals.get(finalI));
                    savedAnimals.get(finalI).start();
                    savedAnimals.remove(finalI);
                    frame.setVisible(false);
                };
                buttons.get(i).addActionListener(listener);

                panel.add(buttons.get(i));
                frame.add(panel);
                frame.setVisible(true);
            }

            for (int i = 0; i < savedPlants.size(); i++) {
                {
                    JLabel animalName = new JLabel("Plant Type:");
                    JLabel animalChoice = new JLabel(savedPlants.get(i).getClass().getName() + "|");
                    panel.add(animalName);
                    panel.add(animalChoice);

                    //choose size of animal
                    JLabel animalSize = new JLabel("Size:");
                    JLabel animalSizeChoice = new JLabel("" + savedPlants.get(i).getSize() + "|");
                    panel.add(animalSize);
                    panel.add(animalSizeChoice);

                    //horizontal speed of animal
                    JLabel horizontalSpeedLabel = new JLabel("X:");
                    JLabel horizontalSpeed = new JLabel("" + savedPlants.get(i).getX() + "|");
                    panel.add(horizontalSpeedLabel);
                    panel.add(horizontalSpeed);

                    //vertical speed of animal
                    JLabel verticalSpeedLabel = new JLabel("Y:");
                    JLabel verticalSpeed = new JLabel("" + savedPlants.get(i).getY() + "|");
                    panel.add(verticalSpeedLabel);
                    panel.add(verticalSpeed);

                    //choosing animal color
                    JLabel animalColorLabel = new JLabel("Plant Color:");
                    JLabel animalColor = new JLabel("Green|");
                    panel.add(animalColorLabel);
                    panel.add(animalColor);
                }
                buttons.add(new JButton("Save Plant"));
                int finalI = i;

                ActionListener listener = e14 -> {
                    displayPanel.getPlants().set(finalI, savedPlants.get(finalI));
                    savedPlants.remove(finalI);
                    frame.setVisible(false);
                };
                buttons.get(i).addActionListener(listener);

                panel.add(buttons.get(i));
                frame.add(panel);
                frame.setVisible(true);
            }
        }
    }
}

