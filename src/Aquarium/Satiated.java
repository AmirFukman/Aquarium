package Aquarium;

import java.awt.Rectangle;
import java.util.concurrent.BrokenBarrierException;

import GUI.AquaFrame;
import Interfaces.HungerState;

/**
 * Determine if animal is satiated
 * @author 
 *
 */
public class Satiated implements HungerState{
	
	/**
	 * determine the fish action while worm is appear
	 */
	public void eat(Swimmable animal) {
		if(animal.getX_front()>= animal.getR().getWidth()) {
			animal.horSpeed = animal.horSpeed * -1;
			animal.setX_front((int)animal.getR().getWidth() - animal.getSize() - (animal.getSize()/4));
			animal.setX_dir(0);
		}	
		if(animal.getY_front() >= (animal.getR().getHeight() - 30 - (animal.getSize()/4))) {
			animal.verSpeed = animal.verSpeed * -1;
		}
		if(animal.getX_front()<= 0) {
			animal.horSpeed = animal.horSpeed * -1;
			animal.setX_front(animal.getSize() + (animal.getSize()/4)) ;
			animal.setX_dir(1);
		}
		if(animal.getY_front() <= (animal.getSize()/4)) {
			animal.verSpeed = animal.verSpeed * -1;
		}
		animal.setY_front(animal.getY_front() + animal.verSpeed);
		animal.setX_front(animal.horSpeed + animal.getX_front());
	}
}
