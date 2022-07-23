package Interfaces;

import Aquarium.Swimmable;

/**
 * determine if animal is hungry or satiated
 * @author 
 *
 */
public interface HungerState {
	
	/**
	 * determine the fish action while worm is appear
	 * @param animal
	 */
	public void eat(Swimmable animal);
}
