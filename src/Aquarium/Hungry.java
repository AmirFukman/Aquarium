package Aquarium;

import java.awt.Rectangle;
import java.util.concurrent.BrokenBarrierException;

import GUI.AquaFrame;
import Interfaces.HungerState;

/**
 * Determine if animal is hungry
 * @author 
 *
 */
public class Hungry implements HungerState{

	/**
	 * determine the fish action while worm is appear
	 */
	public void eat(Swimmable animal) {
		if(animal.getX_front() > (animal.getR().getWidth() / 2)) {
			animal.setX_dir(0);
			if(animal.horSpeed > 0) {
				animal.horSpeed = animal.horSpeed * -1;
			}
			animal.setX_front(animal.getX_front() + animal.horSpeed);
			animal.setY_front((int)((animal.getIncline() * (float)(animal.getX_front() - (animal.getR().getWidth()/2))) + (animal.getR().getHeight()/2)) ); 
		}else {
			if(animal.horSpeed < 0) {
				animal.horSpeed = animal.horSpeed * -1;
			}
			animal.setX_dir(1);
			animal.setX_front(animal.getX_front() + animal.horSpeed);
			animal.setY_front((int)((animal.getIncline() * (float)(animal.getX_front() - (animal.getR().getWidth()/2))) + (animal.getR().getHeight()/2)) );
		}
		
		//if the fish has reach to the worm
		if(Math.abs((animal.getR().getHeight() / 2) - animal.getY_front()) <= 15 && Math.abs((animal.getR().getWidth()/ 2) - animal.getX_front()) <= 15 ) {///////////
			AquaFrame.getPanel().setFoodFlag(false);
			AquaFrame.getPanel().setWormFlag(false);
			AquaFrame.getPanel().IncreaseFoodCount(animal);
			Singleton.setInstanceToNull();//"delete" the current worm instance
			animal.setStartTime(System.nanoTime());
			animal.hunger = 0;
		}
	}
		
}
