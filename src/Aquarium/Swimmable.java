package Aquarium;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
//import java.util.concurrent.CyclicBarrier;

import Interfaces.HungerState;
import Interfaces.MarineAnimal;
import Interfaces.SeaCreature;

/**
 * swimmable class - this is an abstract class to different kind of sea animals
 * @author
 */
public abstract class Swimmable extends Thread implements SeaCreature,Cloneable,Observer ,MarineAnimal,HungerState{
	protected int horSpeed;
	protected int verSpeed;
	protected int seaCreatureNumber;
	protected HungerState hungerState;
	public int hunger = 4; 
	
	/**
	 * default class Constructor
	 */
	public Swimmable() {
		horSpeed = 0;
		verSpeed = 0;
		seaCreatureNumber = 0;
		
	}
	/**
	 * class Constructor
	 * @param a - horSpeed
	 * @param b - verSpeed
	 */
	public Swimmable(int a, int b, int c) {
		horSpeed = a;
		verSpeed = b;
		seaCreatureNumber = c;
	}
	/**
	 * copy Constructor
	 * @param other - other object
	 */
	public Swimmable(Swimmable other) {
		horSpeed = other.horSpeed;
		verSpeed = other.verSpeed;
		seaCreatureNumber = other.seaCreatureNumber; 
	}
	
	/**
	 * @return horSpeed
	 */
	public int getHorSpeed() {
		return horSpeed;
	}
	/**
	 * set horSpeed
	 * @param OhorSpeed
	 * @return true/false if the operation succeed
	 */
	public boolean setHorSpeed(int OhorSpeed) {
		if (OhorSpeed < 0)
			return false;
		this.horSpeed = OhorSpeed;
		return true;
	}
	/**
	 * @return verSpeed
	 */
	public int getVerSpeed() {
		return verSpeed;
	}
	/**
	 * set verSpeed
	 * @return true/false if the operation succeed 
	 * @param OverSpeed
	 */
	public boolean setVerSpeed(int OverSpeed) {
		if (OverSpeed < 0)
			return false;
		this.verSpeed = OverSpeed;
		return true;
	}
	
	/**
	 * @return the name of the animal
	 */
	public abstract String getAnimalName();
	
	/**
	 * @return the amount of food the animal ate
	 */
	public abstract int getEatCount();
	
	/**
	 * @return size of the animal
	 */
	public abstract int getSize();
	
	/**
	 * 
	 * @return color of animal
	 */
	public abstract Color getColor();
	
	/**
	 * increase the amount of food the animal ate with one
	 */
	public abstract void eatInc();
	
	/**
	 * @return string format of objects
	 */
	public abstract String toString();
	
	/*
	 * @return if objects are equals;
	 */
	public abstract boolean equals(Object other);
	
	/**
	 * @param other an object to compare with this comparable object.
	 * @return a negative value, 0, or a positive value, if this object has an identical size.
	 */
	public int compareTo(Object other) {
		if (!(other instanceof Swimmable)) {
			throw new RuntimeException("compareTo got a non-Swimmable object.");
		}
		int otherSize = ((Swimmable)other).getSize();
		return this.getSize() - otherSize;
	}
	
	/**
	 * stop the animal movement
	 */
	abstract public void setSuspend();
	
	/**
	 * resume the animal movement
	 */
	abstract public void setResume();
	
	/**
	 * threads run method
	 */
	public abstract void run();
	
	/**
	 * draw sea creatures
	 * @param g
	 */
	public abstract void drawCreature(Graphics g);
	
	/**
	 * clone creature method
	 * @return Swimmable
	 */
	public abstract Swimmable clone();
	
	/**
	 * Implement update method of observer interface
	 * @param msg
	 */
	public abstract void update(Observable o, Object arg);
	
	/**
	 * add observer to Observable Observers list
	 * @param ob
	 */
	public abstract void unregisterObserver(Observer ob);
	
	/**
	 * add observer to Observable Observers list
	 * @param ob
	 */
	public abstract void registerObserver(Observer ob);
	
	/**
	 * get x_front
	 * @return x_front
	 */
	public abstract int getX_front();
	
	/**
	 * get y_front
	 * @return y_front
	 */
	public abstract int getY_front();
	
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
	
	/**
	 * return index
	 * @return
	 */
	public abstract int getSeaCreatureNumber();
	
	/**
	 * set size
	 * @param size_o
	 * @return
	 */
	public abstract boolean setSize(int size_o);
	
	/**
	 * set x_front
	 * @param x_front_o
	 * @return true/false if the operation succeed
	 */
	public abstract boolean setX_front(int x_front_o);
	
	/**
	 * set y_front
	 * @param y_front_o
	 * @return true/false if the operation succeed
	 */
	public abstract boolean setY_front(int y_front_o);
	
	/**
	 * set color
	 * @param color
	 * @return true/false if the operation succeed
	 */
	public abstract boolean setCol(Color color);
	
	/**
	 * get x_dir
	 * @return x_dir
	 */
	public abstract int getX_dir();
	
	/**
	 * get y_dir
	 * @return y_dir
	 */
	public abstract int getY_dir();
	
	/**
	 * set y_dir
	 * @param y_dir_o
	 * @return true/false if the operation succeed
	 */
	public abstract boolean setY_dir(int y_dir_o);
	
	/**
	 * set x_dir
	 * @param x_dir_o
	 * @return true/false if the operation succeed
	 */
	public abstract boolean setX_dir(int x_dir_o);
	/**
	 * sets the animal hunger state
	 * @param state
	 */
	public abstract void setHungerState(HungerState state);
	
	/**
	 * determine the fish action while worm is appear
	 */
	public abstract void eat();
	
	/**
	 * determine the fish action while worm is appear
	 */
	public abstract void eat(Swimmable animal);
	/**
	 * get start time
	 * @return
	 */
	public abstract long getStartTime();
	
	/**
	 * set start time
	 * @param startTime
	 */
	public abstract void setStartTime(long startTime);
	
	/**
	 * get food frequency
	 * @return
	 */
	public abstract int getFoodFreq();
	/**
	 * set food frequency
	 * @param foodFreq
	 */
	public abstract void setFoodFreq(int foodFreq);
	
	/**
	 * return elapsed time
	 * @return
	 */
	public abstract long getElapsedTime();
	/**
	 * set elapes time
	 * @param elapsedTime
	 */
	public abstract void setElapsedTime(long elapsedTime);
	
	/**
	 * get Rectangle
	 * @return
	 */
	public abstract Rectangle getR();
	/**
	 * set Rectangle
	 * @param r
	 */
	public abstract void setR(Rectangle r);
	
	/**
	 * get inclime
	 * @return
	 */
	public abstract float getIncline();
	/**
	 * set inclime
	 * @param incline
	 */
	public abstract void setIncline(float incline);
}
