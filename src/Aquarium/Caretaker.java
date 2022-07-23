package Aquarium;

import java.util.ArrayList;
import java.util.List;

/**
 * manage the mementos 
 * @author 
 *
 */
public class Caretaker {
	private List animalStateList = new ArrayList();
	private List plantStateList = new ArrayList();
	
	/**
	 * add memento to memento lists
	 * @param m
	 */
	public void addMemento(Memento m) {
		//if plant memento - add to plant state list
		if(m.getVerSpeedState() == 0) {
			for(int i = 0; i < plantStateList.size(); i++) {
				if(((Memento) plantStateList.get(i)).getNumber() == m.getNumber()) {
					plantStateList.remove(i);
				}
			}
			plantStateList.add(m);
		}else {
			//if animal memento - add to animal state list
			for(int i = 0; i < animalStateList.size(); i++) {
				if(((Memento) animalStateList.get(i)).getNumber() == m.getNumber()) {
					animalStateList.remove(i);
				}
			}
			animalStateList.add(m);
		}
	}
	
	/**
	 * return memnto by object index
	 * @param index
	 * @return
	 */
	public Memento getMemento(int index , String Objectclass) {
		//if Swimmable memento - return from animals state list
		if(Objectclass.equals("Fish") || Objectclass.equals("Jellyfish")) {
			
			for(int i = 0; i < animalStateList.size(); i++) {
				if(((Memento) animalStateList.get(i)).getNumber() == index) {
					return (Memento) animalStateList.get(i);
				}
			}
			return null;
			
		}else {//if plant memento - return from plant state list
			for(int i = 0; i < plantStateList.size(); i++) {
				if(((Memento) plantStateList.get(i)).getNumber() == index) {
					return (Memento) plantStateList.get(i);
				}
			}
			return null;
		}
	}

	/**
	 * get animal state list
	 * @return
	 */
	public List getAnimalStateList() {
		return animalStateList;
	}

	/**
	 * get plant state list
	 * @return
	 */
	public List getPlantStateList() {
		return plantStateList;
	}

}

