package Aquarium;

import Interfaces.SeaPlant;

/**
 * abstract immobile class
 * @author 
 *
 */
public abstract class Immobile implements SeaPlant{
	String name;
	int indexSeaPlant;
	
	/**
	 * 
	 * @return plant class name
	 */
	public abstract Object getPlantName();

	/**
	 * return index
	 * @return
	 */
	public abstract int getIndexSeaPlant();

	/**
	 * 
	 * @return plant size
	 */
	public abstract int getSize();
	
	/**
     * get x
     * @return x
     */
    public abstract int getX();

    /**
     * set x
     * @param x
     */
	public abstract void setX(int x);

	/**
	 * 
	 * @return y
	 */
	public abstract int getY();

	/**
	 * set y
	 * @param y
	 */
	public abstract void setY(int y);

	/**
	 * set size
	 * @param size
	 */
	public abstract void setSize(int size);
	
	
	/**
	 * save the current state values in state object 
	 * @param arrState
	 */
	public abstract void setState(Object arrState[]);
	
	/**
	 * return the last state saved
	 */
	public abstract Object getstate();
	
	/**
	 * return memento object with the last state saved
	 * @return memento object with the last state saved
	 */
	public abstract Memento createMemento();
	
	/**
	 * Recovers the state saved in the memnto
	 * @param memento
	 */
	public abstract void setMemento(Memento memento);
}
