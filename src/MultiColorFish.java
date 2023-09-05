import java.awt.*;

public class MultiColorFish extends Fish{
	
	/**
	 * default constructor
	 */
	public MultiColorFish() {
		super();
	}
	/**
	 * copy constructor
	 * @param other - object of fish
	 */
	public MultiColorFish(Fish other) {
		super(other);
			}
	/**
	 * constructor
	 * @param size - basic size of the fish
	 * @param x_front - for future use
	 * @param y_front - for future use
	 * @param horSpeed  - for future use
	 * @param verSpeed - for future use
	 * @param col - color of the fish 
	 */

	public MultiColorFish(int eatTime, int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col, AquaPanel aquaPanel) {
		super(eatTime, size, x_front, y_front, horSpeed, verSpeed, col,aquaPanel);
			}
	/**
	 * increase fish size
	 */
	public void changeFish() {
		super.changeFish();
//		changeColor();
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
		return true;
	}
	/**
	 * return animal name
	 */
	public String getAnimalName() {
		return "MultiColorFish";
	}
}
