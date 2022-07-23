package Aquarium;

import java.awt.Color;

import Interfaces.MarineAnimal;

/**
 * paint fish decorator class
 * @author 
 *
 */
public class MarineAnimalDecorator implements MarineAnimal{
	protected MarineAnimal specialSeaCreature;
	
	/**
	 * constructor
	 * @param specialSeaCreature
	 */
	public MarineAnimalDecorator (MarineAnimal specialSeaCreature) {
		this.specialSeaCreature = specialSeaCreature;
	}
	
	/**
	 * change the fish color	
	 */
	public Swimmable PaintFish(Color color) {
		return specialSeaCreature.PaintFish(color);
	}

	
}
