package Aquarium;

import java.awt.Color;

import GUI.AddAnimalDialog;
import GUI.AquaFrame;
import GUI.AquaPanel;
import Interfaces.AbstractSeaFactory;

/**
 * creates new sea creature 
 * @author 
 *
 */
public class AnimalFactory implements AbstractSeaFactory{
	
	/**
	 * factory method - choose which animal to create
	 * @param other
	 */
	public Object produceSeaCreature(String nameType) {
		
		//if fish
		if (nameType == "Fish") {
			
			int a_fish = (int) ((Math.random() * (695 - 410)) + 410);
			int b_fish = (int) ((Math.random() * (325 - 80)) + 80);
			
			return new Fish(AquaFrame.myPanel.getADW().sizeValue, a_fish, b_fish,AquaFrame.myPanel.getADW().horSpeedValue,AquaFrame.myPanel.getADW().verSpeedValue,returnColorNumber(AquaFrame.myPanel.getADW().colorValue), AquaFrame.myPanel.getADW().foodFreqValue,AquaFrame.myPanel.animalCounter);
			
		//if jellyfish	
		}else {
			
			int a_jellyfish = (int) ((Math.random() * (537 - 160)) + 160);
			int b_jellyfish = (int) ((Math.random() * (300 - 80)) + 80);
			
			return new Jellyfish(AquaFrame.myPanel.getADW().sizeValue, a_jellyfish,b_jellyfish,AquaFrame.myPanel.getADW().horSpeedValue,AquaFrame.myPanel.getADW().verSpeedValue,returnColorNumber(AquaFrame.myPanel.getADW().colorValue),AquaFrame.myPanel.getADW().foodFreqValue,AquaFrame.myPanel.animalCounter);
		}
	}
	
	/**
     * 
     * @param color
     * @return color objects
     */
    public Color returnColorNumber(String color) {
		switch(color) {
			case "Black":
				return Color.black;
			case "Red":
				return Color.red;
			case "Blue":
				return Color.blue;
			case "Green":
				return Color.green;
			case "Cyan":
				return Color.cyan;
			case "Orange":
				return Color.orange;
			case "Yellow":
				return Color.yellow;
			case "Magenta":
				return Color.magenta;
			case "Pink":
				return Color.pink;
			default:
				return null;
		}
	}
}
