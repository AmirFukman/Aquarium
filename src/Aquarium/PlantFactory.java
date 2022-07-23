package Aquarium;

import GUI.AddAnimalDialog;
import GUI.AquaFrame;
import Interfaces.AbstractSeaFactory;

/**
 * Plants factory class		
 */
public class PlantFactory implements AbstractSeaFactory{
	
	/**
	 * factory method
	 */
	public Object produceSeaCreature(String other) {
		//if Zostera
		if (other == "Zostera") {
			
			int a_Zostera = (int) ((Math.random() * (700 - 100)) + 100);
			
			return new Zostera(AquaFrame.myPanel.getPlantSizeValue(),a_Zostera, 406,"Green",AquaFrame.myPanel.plantCounter);
			
		//if Laminaria	
		}else {
			int a_Laminaria = (int) ((Math.random() * (700 - 100)) + 100);
			
			return new Laminaria(AquaFrame.myPanel.getPlantSizeValue(),a_Laminaria, 406,"Green",AquaFrame.myPanel.plantCounter);
		}	
	}
}
