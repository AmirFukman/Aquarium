package Aquarium;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * zostera plant class
 * @author 
 *
 */
public class Zostera extends Immobile{
	
	private int size;
	private int x;
	private int y;
	private String color;
	private Object arrState[];
	/**
	 * default ctor
	 */
	public Zostera () {
		size = 0;
		x = 0;
		y = 0;
		color = "Green";
	}
	
	/**
	 * constructor
	 * @param size
	 * @param x
	 * @param y
	 * @param color
	 */
	public Zostera (int size,int x,int y, String color, int index) {
		this.size = size;
		this.x = x;
		this.y = y;
		this.color = color;
		this.indexSeaPlant = index;
	}
	
	/**
	 * copy constructor
	 * @param other
	 */
	public Zostera (Zostera other) {
		this.size = other.size;
		this.x = other.x;
		this.y = other.y;
		this.color = other.color;
		this.indexSeaPlant = other.indexSeaPlant;
	}
	
	/**
	 * start the draw function
	 */
	public void drawCreature(Graphics g) {
		this.draw(g);
	}
	
	/**
	 * draw the plant
	 * @param g
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke (3));
		g.setColor(returnColorNumber(color));
		g.drawLine(x, y, x, y-size);
		g.drawLine(x-2, y, x-10, y-size*9/10);
		g.drawLine(x+2, y, x+10, y-size*9/10);
		g.drawLine(x-4, y, x-20, y-size*4/5);
		g.drawLine(x+4, y, x+20, y-size*4/5);
		g.drawLine(x-6, y, x-30, y-size*7/10);
		g.drawLine(x+6, y, x+30, y-size*7/10);
		g.drawLine(x-8, y, x-40, y-size*4/7);
		g.drawLine(x+8, y, x+40, y-size*4/7);
		g2.setStroke(new BasicStroke(1));
	}
	/**
     * return the color by name
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
    /**
     * get x
     * @return x
     */
    public int getX() {
		return x;
	}

    /**
     * set x
     * @param x
     */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * set y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * set size
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}
    /**
	 * @return object (class) name
	 */
	public String getPlantName() {
		return String.format(this.getClass().getSimpleName());
	}

	/**
	 * return plant size
	 */
	public int getSize() {
		return this.size;
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
	public int getIndexSeaPlant() {
		return this.indexSeaPlant;
	}
}
