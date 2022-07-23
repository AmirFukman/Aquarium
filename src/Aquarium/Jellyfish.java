package Aquarium;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import GUI.AquaFrame;
import Interfaces.HungerState;

/**
 * JellyFish class - create and generate a jellyfish object
 * @author  
 */
public class Jellyfish extends Swimmable implements Cloneable{
	private int EAT_DISTANCE = 4;//const number that define the amount of food Jellyfish can eat. 
	private int size;//basic size of a Jellyfish
	private Color col;//color
	private int eatCount;//amount of food that the Jellyfish has eaten.
	private int x_front;
	private int y_front;
	private int x_dir;
	private int y_dir;
	private int foodFreq;
	private Vector<Observer> list = new Vector();
	private Object arrState[];
	private long startTime;
	private long elapsedTime;
	Rectangle r;
	float incline;
	/**
	 * default class constructor
	 */
	public Jellyfish() {
		super();
		this.size = 0;
		this.col = null;
		this.x_front = 0;
		this.y_front = 0;
		this.eatCount = 0;
		this.x_dir = 1;
		this.y_dir = 1;
		this.foodFreq = 0;
		this.hungerState = (HungerState) new Hungry();
	}
	/**
	 * class constructor
	 * @param size
	 * @param x_front
	 * @param y_front
	 * @param horSpeed
	 * @param verSpeed
	 * @param col
	 */
	public Jellyfish(int size, int x_front, int y_front,int horSpeed,int verSpeed, Color col, int foodFreq, int seaCreatureNumber) {
		super(horSpeed,verSpeed,seaCreatureNumber);
		this.size = size;
		this.col = col;
		this.x_front = x_front;
		this.y_front = y_front;
		this.eatCount = 0;
		this.x_dir = 1;
		this.y_dir = 1;
		this.foodFreq = foodFreq;
		this.hungerState = (HungerState) new Hungry();
	}
	/**
	 * copy constructor
	 * @param other
	 */
	public Jellyfish(Jellyfish other) {
		super(other.horSpeed,other.verSpeed, other.seaCreatureNumber); 
		this.EAT_DISTANCE = other.EAT_DISTANCE;
		this.size = other.size;
		this.col = other.col;
		this.x_front = other.x_front;
		this.y_front = other.y_front;
		this.eatCount = other.eatCount;
		this.x_dir = other.x_dir;
		this.y_dir = other.y_dir;
		this.foodFreq = other.foodFreq;
		this.hungerState = (HungerState) new Hungry();
	}
	
	/**
	 * @return c
	 */
	public Color getColor(){
		return this.col;
	}
	
	/**
	 * @return object (class) name
	 */
	public String getAnimalName() {
		return String.format(this.getClass().getSimpleName());
	}
	
	/**
	 * @return the amount of food the animal ate
	 */
	public int getEatCount(){
		return this.eatCount;
	}
	
	/**
	 * @return size of the animal
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * increase the amount of food the animal ate with one
	 */
	public void eatInc() {
		if (this.eatCount < this.EAT_DISTANCE)
			this.eatCount++;
		else{
			this.eatCount = 0; 
			this.size++;
		}
	}
	
	/**
	 * @return string format of objects
	 */
	public String toString() {
		return "Jellyfish [EAT_DISTANCE=" + EAT_DISTANCE + ", size=" + size + ", col=" + col + ", eatCount=" + eatCount
				+ ", x_front=" + x_front + ", y_front=" + y_front + ", x_dir=" + x_dir + ", y_dir=" + y_dir
				+ ", horSpeed=" + horSpeed + ", verSpeed=" + verSpeed + "]";
	}
	
	/*
	 * @return if objects are equals;
	 */
	public boolean equals(Object other) {
		if (!(other instanceof Fish))
			return false;
		Jellyfish o = (Jellyfish) other;
		boolean r = true;
		r = r && this.EAT_DISTANCE == o.EAT_DISTANCE && this.size == o.size && this.col == o.col && this.eatCount == o.eatCount && this.x_front == o.x_front && this.y_front == o.y_front && this.x_dir == o.y_dir && this.horSpeed == o.horSpeed && this.verSpeed == o.verSpeed;
		return r;
	}
	
	/**
	 * 
	 * @return eat distance
	 */
	public int getEAT_DISTANCE() {
		return EAT_DISTANCE;
	}
	/**
	 *  set eat distance
	 * @param eAT_DISTANCE
	 * @return true/false if the operation succeed
	 */
	public boolean setEAT_DISTANCE(int eAT_DISTANCE) {
		if(eAT_DISTANCE >= 0) {
			EAT_DISTANCE = eAT_DISTANCE;
			return true;
		}
		return false;
	}
	/**
	 * get color
	 * @return color
	 */
	public Color getCol() {
		return col;
	}
	/**
	 * set color
	 * @param color
	 * @return true/false if the operation succeed
	 */
	public boolean setCol(Color color) {
		try{
			this.col = color;
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	/**
	 * get x_front
	 * @return x_front
	 */
	public int getX_front() {
		return x_front;
	}
	/**
	 * set x_front
	 * @param x_front_o
	 * @return true/false if the operation succeed
	 */
	public boolean setX_front(int x_front_o) {
		try{
			this.x_front = x_front_o;
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	/**
	 * get y_front
	 * @return y_front
	 */
	public int getY_front() {
		return y_front;
	}
	/**
	 * set y_front
	 * @param y_front_o
	 * @return true/false if the operation succeed
	 */
	public boolean setY_front(int y_front_o) {
		try{
			this.y_front = y_front_o;
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	/**
	 * get x_dir
	 * @return x_dir
	 */
	public int getX_dir() {
		return x_dir;
	}
	/**
	 * set x_dir
	 * @param x_dir_o
	 * @return true/false if the operation succeed
	 */
	public boolean setX_dir(int x_dir_o) {
		try{
			this.x_dir = x_dir_o;
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	/**
	 * get y_dir
	 * @return y_dir
	 */
	public int getY_dir() {
		return y_dir;
	}
	/**
	 * set y_dir
	 * @param y_dir_o
	 * @return true/false if the operation succeed
	 */
	public boolean setY_dir(int y_dir_o) {
		try{
			this.y_dir = y_dir_o;
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	/**
	 * set jellyfish size
	 * @param size_o
	 * @return true/false if the operation succeed
	 */
	public boolean setSize(int size_o) {
		if(size_o < 0)
			return false;
		this.size = size_o;
		return true;
	}
	/**
	 * set eat count
	 * @param eatCount_o
	 * @return true/false if the operation succeed
	 */
	public boolean setEatCount(int eatCount_o) {
		if(eatCount_o >= 0) {
			this.eatCount = eatCount_o;
			return true;
		}
		return false;
	}
	
	/**
	 * set eat count
	 * @param eatCount_o
	 * @return true/false if the operation succeed
	 */
	public void changeJellyfish(int size_o) {
		if(size_o >= 0)
			this.size = size_o;
		else
			System.out.println("size must be larger than 0.");
	}
	
	/**
	 * draw pixels with animal shapes
	 */
	public void drawCreature(Graphics g)
	{
	   int numLegs;
	   if(size<40)
	    	numLegs = 5;
	   else if(size<80)
	    	numLegs = 9;
	   else
	    	numLegs = 12;

	   g.setColor(col);
	   g.fillArc(x_front - size/2, y_front - size/4, size, size/2, 0, 180);
			
	   for(int i=0; i<numLegs; i++)
		g.drawLine(x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front,x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front+size/3);
	}
	
	/**
	 * thread run method
	 */
	public void run() {
		startTime = System.nanoTime();
		r = AquaFrame.getPanel().getBounds();
		synchronized (this){
			//keep the thread alive
			while(true) {
				
				this.setHungerState(new Satiated());
				
				//regular swimming
				while(AquaFrame.getPanel().getFoodFlag() == false || this.hunger < 4) {
					
					this.eat();
					
					try {
						Thread.sleep(40);
					}catch(InterruptedException e) {};
					
					AquaFrame.getPanel().repaint();
					
					if(AquaFrame.getPanel().getFlag()) {
						try {
							wait();
						} catch (InterruptedException e) {}
					}
					
					//count the seconds that the animal no eat
					long elapsedTime = (System.nanoTime() - startTime)/1000000000;
					
					if(elapsedTime >= this.foodFreq) {
						startTime = System.nanoTime();
						this.update(null,"Animal " +  this.seaCreatureNumber + " is hungry!");
					}
				}
				
				//change state to hungry
				this.setHungerState(new Hungry());
		
				incline = ((float)((float)((float)this.y_front - (r.height/2)) / ((float)((float)this.x_front - (r.width / 2)))));
				while(AquaFrame.getPanel().getFoodFlag() && this.hunger >=4) {
					
					this.eat();
					
					try {
						Thread.sleep(40);
					}catch(InterruptedException e) {};
					
					if(AquaFrame.getPanel().getFlag()) {
						try {
							wait();
						} catch (InterruptedException e) {}
					}
					AquaFrame.getPanel().repaint();
				}
			}	
		}
	}

	/**
	 * set the suspend flag to true 
	 */
	public void setSuspend() {
		AquaFrame.getPanel().setFlag(true);
	}
	
	/**
	 * resume the swimming of the fish after eating
	 */
	public void setResume() {
		if(this.getState().toString() == "WAITING" ) {
			synchronized (this) {
				AquaFrame.getPanel().setFlag(false);
				this.notify();
			}
		}
	}
	
	/**
	 * clone jellyfish method
	 */
	public Jellyfish clone() {
		return new Jellyfish(this);
	}
	
	/**
	 * update animal method
	 * @param size
	 * @param ver_speed
	 * @param hor_speed
	 * @param color
	 */
	public void upgrade(int size,int ver_speed, int hor_speed, Color color) {
		this.size = size;
		this.verSpeed = ver_speed;
		this.horSpeed = hor_speed;
		this.col = color;
	}
	
	/**
	 * add observer to Observable Observers list
	 * @param ob
	 */
	public void registerObserver(Observer ob) {
		list.add(ob);
	}
	
	/**
	 * add observer to Observable Observers list
	 * @param ob
	 */
	public void unregisterObserver(Observer ob) {
		list.remove(ob);
	}

	/**
	 * Implement update method of observer interface
	 */
	public void update(Observable o, Object arg) {
		for(Observer ob:list) {
			ob.update(null,arg);
		}
	}
	
	/**
	 * return fish with new paint
	 */
	public Swimmable PaintFish(Color color) {
		this.setCol(color);
		return this;
	}
	
	/**
	 * save the current state values in state object 
	 * @param arrState
	 */
	public void setState(Object arrState[]) {
		this.arrState = arrState;
	}
	
	/**
	 * return the last state saved
	 */
	public Object getstate() {
		return this.arrState;
	}
	
	/**
	 * return memento object with the last state saved
	 * @return memento object with the last state saved
	 */
	public Memento createMemento() {
		return new Memento(this.arrState);
	}
	
	/**
	 * Recovers the state saved in the memnto
	 * @param memento
	 */
	public void setMemento(Memento memento) {
		this.arrState = (Object[]) memento.getState();
	}
	
	/**
	 * return index
	 */
	public int getSeaCreatureNumber() {
		return this.seaCreatureNumber;
	}
	
	/**
	 * get start time
	 * @return
	 */
	public long getStartTime() {
		return startTime;
	}
	
	/**
	 * set start time
	 * @param startTime
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	/**
	 * get food frequency
	 * @return
	 */
	public int getFoodFreq() {
		return foodFreq;
	}
	/**
	 * set food frequency
	 * @param foodFreq
	 */
	public void setFoodFreq(int foodFreq) {
		this.foodFreq = foodFreq;
	}
	
	@Override
	public void eat(Swimmable animal) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * sets the animal hunger state
	 * @param state
	 */
	public void setHungerState(HungerState state) {
		this.hungerState = state;
		
	}
	/**
	 * determine the fish action while worm is appear
	 */
	public void eat() {
		hungerState.eat(this);
		
	}
	/**
	 * return elapsed time
	 * @return
	 */
	public long getElapsedTime() {
		return elapsedTime;
	}
	/**
	 * set elapse time
	 * @param elapsedTime
	 */
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	/**
	 * get Rectangle
	 * @return
	 */
	public Rectangle getR() {
		return r;
	}
	/**
	 * set Rectangle
	 * @param r
	 */
	public void setR(Rectangle r) {
		this.r = r;
	}
	
	/**
	 * get inclime
	 * @return
	 */
	public float getIncline() {
		return incline;
	}
	/**
	 * set inclime
	 * @param incline
	 */
	public void setIncline(float incline) {
		this.incline = incline;
	}
}

