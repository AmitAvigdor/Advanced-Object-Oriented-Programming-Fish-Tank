import java.awt.*;

/**
 * class UnusualFish with fields that define it 
 */
public class UnusualFish extends Fish {
	private int factor;
/**
 * default constructor
 */
	public UnusualFish() {
		super();
		this.factor=0;
		
	}
/**
 * constructor
 * @param size - basic size of the fish
 * @param x_front - for future use
 * @param y_front - for future use
 * @param horSpeed  - for future use
 * @param verSpeed - for future use
 * @param col - color of the fish 
 * @param factor - the way to calculate the size
 */
	public UnusualFish(int eatTime, int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col, int factor, AquaPanel aquaPanel) {
		super(eatTime,size, x_front, y_front, horSpeed, verSpeed, col, aquaPanel);
		this.factor=factor;
		
	}
	/**
	 * copy constructor
	 * @param other - object of UnusualFish
	 */
	public UnusualFish(UnusualFish other) {
		super(other);
		this.factor=other.factor;
	}


	public int getFactor() {
		return factor;
	}


	public void setFactor(int factor) {
		this.factor = factor;
	}

	
	public int getSize() {
		return super.getSize()*factor;
	}


//	public String toString() {
//		return String.format("%s%18s%8s%5s",getAnimalName(),getColor(),this.getSize(),this.getEatCount());
//	}
	
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
		UnusualFish other = (UnusualFish) obj;
		return factor == other.factor;
	}
/**
 * return animal name
 */
	public String getAnimalName() {
		return "UnusualFish";
	}
	
}
