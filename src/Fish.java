import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * class Fish with fields that define it
 */
public class Fish extends Swimmable implements MarineAnimal {
    public int EAT_DISTANCE;
    private int size, x0, y0;
    private double distX, distY, m, b;
    private Color col;
    private int eatCount;
    private int x_front, y_front, x_dir, y_dir;
    private AquaPanel aquaPanel;
    boolean suspendFlag = false;
    boolean resumeFlag = false;
    private CyclicBarrier cyclicBarrier;


    /**
     * default constructor
     */
    public Fish() {
        super();
        EAT_DISTANCE = 4;
        this.size = 0;
        this.col = null;
        this.eatCount = 0;
        this.x_front = 0;
        this.y_front = 0;
        this.x_dir = 1;
        this.y_dir = 1;
        state=new Satiated();
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * constructor
     *
     * @param size     - basic size of the fish
     * @param x_front  - for future use
     * @param y_front  - for future use
     * @param horSpeed - for future use
     * @param verSpeed - for future use
     * @param col      - color of the fish
     */
    public Fish(int eatTime, int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col, AquaPanel aquaPanel) {
        super(horSpeed, verSpeed, eatTime);
        EAT_DISTANCE = 4;
        this.size = size;
        this.col = col;
        this.x_front = x_front;
        this.y_front = y_front;
        this.eatCount = 0;
        this.x_dir = 1;
        this.y_dir = 1;
        this.aquaPanel = aquaPanel;
        state=new Satiated();
    }

    /**
     * copy constructor
     *
     * @param other - object of fish
     */
    public Fish(Fish other) {
        super(other);
        EAT_DISTANCE = 4;
        this.size = other.getSize();
        this.col = other.getCol();
        this.x_front = other.getX_front();
        this.y_front = other.getY_front();
        this.eatCount = other.getEatCount();
        this.x_dir = other.getX_dir();
        this.y_dir = other.getY_dir();
        state=new Satiated();
    }

    public int getEAT_DISTANCE() {
        return EAT_DISTANCE;
    }

    public void setEAT_DISTANCE(int eAT_DISTANCE) {
        EAT_DISTANCE = eAT_DISTANCE;
    }

    public int getSize() {
        return size;
    }

    public Color getCol() {
        return col;
    }

    public void setCol(Color col) {
        this.col = col;
    }

    public int getEatCount() {
        return eatCount;
    }

    public void setEatCount(int eatCount) {
        this.eatCount = eatCount;
    }

    public int getX_front() {
        return x_front;
    }

    public void setX_front(int x_front) {
        this.x_front = x_front;
    }

    public int getY_front() {
        return y_front;
    }

    public void setY_front(int y_front) {
        this.y_front = y_front;
    }

    public int getX_dir() {
        return x_dir;
    }

    public void setX_dir(int x_dir) {
        this.x_dir = x_dir;
    }

    public int getY_dir() {
        return y_dir;
    }

    public void setY_dir(int y_dir) {
        this.y_dir = y_dir;
    }

    /**
     * gets color number and return color name
     */
    public String getColor() {
        if (this.col == Color.black) {
            return "Black";
        }
        if (this.col == Color.RED) {
            return "Red";
        }
        if (this.col == Color.BLUE) {
            return "Blue";
        }
        if (this.col == Color.GREEN) {
            return "Green";
        }
        if (this.col == Color.CYAN) {
            return "Cyan";
        }
        if (this.col == Color.orange) {
            return "Orange";
        }
        if (this.col == Color.yellow) {
            return "Yellow";
        }
        if (this.col == Color.MAGENTA) {
            return "Magenta";
        }
        if (this.col == Color.pink) {
            return "Pink";
        }

        return "Error";
    }

    @Override
    public void setColor(Color c) {
        this.col = c;
    }

    /**
     * increase fish size
     */
    public void changeFish() {
        size++;
    }
    /**
     * changes the color of the fish in a circular way
     */
//	public void changeColor() {
//
//		col=(col+1)%10;
//		if(col==0) {
//			col=1;
//		}
//	}

    /**
     * return animal name
     */
    public String getAnimalName() {
        return "Fish";
    }


    /**
     * Increases the amount of food the animal has eaten by 1
     */
    public void eatInc() {
        eatCount++;
        if (eatCount == EAT_DISTANCE) {
            eatCount = 0;
            size++;
        }
    }

    /**
     * equals function
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Fish other = (Fish) obj;
        return EAT_DISTANCE == other.EAT_DISTANCE && col == other.col && eatCount == other.eatCount
                && size == other.size && x_dir == other.x_dir && x_front == other.x_front && y_dir == other.y_dir
                && y_front == other.y_front;
    }

    /**
     * suspend fish while eating
     */
    @Override
    public void setSuspend() {
        suspendFlag = true;
    }

    /**
     * waking up the fish
     */
    @Override
    public synchronized void setResume() {
        suspendFlag = false;
        resumeFlag = true;
        notify();


    }


    @Override
    public void setBarrier(CyclicBarrier b) {
        cyclicBarrier = b;

    }

    public void run() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() { // Eat Timer by seconds
                eatTimer++;//increase the counter
                if (eatTimer == eatTime) {
                    JFrame frame = new JFrame("FEED ME!!");
                    frame.setSize(180, 100);
                    JPanel panel = new JPanel();
                    JButton timerBtn = new JButton("<html>The Fish " + getAnimalName() + " is hungry!<br>Do you want to feed him?<br>Press Food if so!</html>");
                    panel.add(timerBtn);
                    frame.add(panel);
                    frame.setVisible(true);

                    if (state instanceof Satiated)
                        state = new Hungry();

                    timerBtn.addActionListener(e -> frame.setVisible(false));


                    eatTimer = 0;
                }
            }
        };

        java.util.Timer timer = new Timer("EatTimer");//create a new Timer
        timer.scheduleAtFixedRate(timerTask, 30, 1000);//this line starts the timer at the same time its executed

        while (true) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (suspendFlag) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if (aquaPanel.isWormInAqua() && state instanceof Hungry) {
                if (cyclicBarrier != null) {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                }
                cyclicBarrier = null;
                calculateLine();
                changeDirectionToWorm();
                synchronized (this) {
                    if (distX <= 5 && distY <= 5) {
                        state = new Satiated();
                        aquaPanel.callback(this);
                        aquaPanel.repaint();
                    }
                }
            } else {
                this.x_front = x_front + horSpeed * x_dir;
                this.y_front = y_front + verSpeed * y_dir;
                checkBoundaries();
            }
            aquaPanel.repaint();
        }
    }

    /**
     * fish stays in the frame
     */
    private void checkBoundaries() {
        if (this.x_front >= aquaPanel.getWidth()) {
            x_dir = -1;
        }
        if (this.x_front < 0) {
            x_dir = 1;
        }
        if (this.y_front >= aquaPanel.getHeight() - 22) {
            y_dir = -1;
            ;
        }
        if (this.y_front < 0) {
            y_dir = 1;
        }
    }

    /**
     * calculate fish path to worm
     */
    public void calculateLine() {
        distX = Math.abs(x_front - (aquaPanel.getWidth() / 2));
        distY = Math.abs(y_front - (aquaPanel.getHeight() / 2));
        x0 = x_front;
        y0 = y_front;
        m = (double) (y0 - aquaPanel.getHeight() / 2) / (double) (x0 - aquaPanel.getWidth() / 2);
        b = -m * x0 + y0;
    }

    /**
     * fish looking at worm
     */
    private void changeDirectionToWorm() {
        if (x_front >= (aquaPanel.getWidth()) / 2)
            x_dir = -1;
        else
            x_dir = 1;

        x_front += verSpeed * x_dir;
        if (y_front == (int) (m * x_front + b))
            y_front++;
        else
            y_front = (int) (m * x_front + b);
        checkBoundaries();
    }

    @Override
    public SeaCreature clone() {
        return new Fish(eatTime, size, x_front, y_front, horSpeed, verSpeed, col, aquaPanel);
    }

    @Override
    public void drawCreature(Graphics g) {
        g.setColor(col);
        if (x_dir == 1) // fish swims to right side
        {
            // Body of fish
            g.fillOval(x_front - size, y_front - size / 4, size, size / 2);

            // Tail of fish
            int[] x_t = {x_front - size - size / 4, x_front - size - size / 4, x_front - size};
            int[] y_t = {y_front - size / 4, y_front + size / 4, y_front};
            Polygon t = new Polygon(x_t, y_t, 3);
            g.fillPolygon(t);

            // Eye of fish
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue()));
            g2.fillOval(x_front - size / 5, y_front - size / 10, size / 10, size / 10);

            // Mouth of fish
            if (size > 70)
                g2.setStroke(new BasicStroke(3));
            else if (size > 30)
                g2.setStroke(new BasicStroke(2));
            else
                g2.setStroke(new BasicStroke(1));
            g2.drawLine(x_front, y_front, x_front - size / 10, y_front + size / 10);
            g2.setStroke(new BasicStroke(1));
        } else // fish swims to left side
        {
            // Body of fish
            g.fillOval(x_front, y_front - size / 4, size, size / 2);

            // Tail of fish
            int[] x_t = {x_front + size + size / 4, x_front + size + size / 4, x_front + size};
            int[] y_t = {y_front - size / 4, y_front + size / 4, y_front};
            Polygon t = new Polygon(x_t, y_t, 3);
            g.fillPolygon(t);

            // Eye of fish
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue()));
            g2.fillOval(x_front + size / 10, y_front - size / 10, size / 10, size / 10);

            // Mouth of fish
            if (size > 70)
                g2.setStroke(new BasicStroke(3));
            else if (size > 30)
                g2.setStroke(new BasicStroke(2));
            else
                g2.setStroke(new BasicStroke(1));
            g2.drawLine(x_front, y_front, x_front + size / 10, y_front + size / 10);
            g2.setStroke(new BasicStroke(1));
        }
    }

    @Override
    public void paintFish(Color color) {
        this.col = color;
    }
}
