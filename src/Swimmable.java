import java.awt.*;
import java.util.concurrent.CyclicBarrier;

/**
 * abstract class Swimmable with fields that define it
 */
public abstract class Swimmable extends Thread implements Runnable, Comparable, SeaCreature {
    protected int eatTimer, eatTime;
    protected int horSpeed;
    protected int verSpeed;
    protected HungerState state;

    /**
     * default constructor
     */
    public Swimmable() {
        this.horSpeed = 0;
        this.verSpeed = 0;
    }

    /**
     * constructor
     */
    public Swimmable(int horS, int verS, int eatTime) {
        this.horSpeed = horS;
        this.verSpeed = verS;
        this.eatTime = eatTime;
        eatTimer = 0;
    }

    /**
     * copy constructor
     *
     * @param other - object of Swimmable
     */
    public Swimmable(Swimmable other) {
        this.horSpeed = other.horSpeed;
        this.verSpeed = other.verSpeed;
    }

    public int getHorSpeed() {
        return this.horSpeed;
    }

    public int getverSpeed() {
        return this.verSpeed;
    }

    public abstract void setSize(int size);

    public void setHorSpeed(int horS) {
        this.horSpeed = horS;

    }

    public void setverSpeed(int verS) {
        this.verSpeed = verS;
    }

    /**
     * toString function
     */
    public String toString() {
        return String.format("%-16s%-12s%-6s%-6s%-6s%-10s", getAnimalName(), getColor(), this.getSize(), this.getHorSpeed(), this.getverSpeed(), this.getEatCount());
    }

    /**
     * equals function
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Swimmable other = (Swimmable) obj;
        return horSpeed == other.horSpeed && verSpeed == other.verSpeed;
    }

    abstract public String getAnimalName();

    //	abstract public void drawAnimal(Graphics g);
    abstract public void setSuspend();

    abstract public void setResume();

    abstract public void setBarrier(CyclicBarrier b);

    abstract public int getSize();

    abstract public void eatInc();

    abstract public int getEatCount();
    public int getEatTime(){return eatTime;}
    abstract public String getColor();

    abstract public void setColor(Color c);

    public void setEatTime(int eatTime) {
        this.eatTime = eatTime;
    }

    @Override
    public abstract SeaCreature clone();
}