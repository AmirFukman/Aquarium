package Interfaces;

/**
 * abstract interface to create sea creatures and plants
 * @author 
 *
 */
public interface AbstractSeaFactory {
	
	/**
	 * factory method
	 * @param other
	 */
	public Object produceSeaCreature(String other);
}
