package Aquarium;

import java.awt.Color;

/**
 * save the state of sea creature or sea plant
 * @author 
 *
 */
public class Memento {
	private final Color colorState;
	private final int sizeState;
	private final int x_frontState;
	private final int y_frontState;
	private final int verSpeedState;
	private final int horSpeedState;
	private final int number;
	private final int x_dir;
	private final int y_dir;
	/**
	 * plant memento constructor, save the data of the current object state
	 * @param colorState
	 * @param sizeState
	 * @param x_frontState
	 * @param y_frontState
	 */
	public Memento(Color colorState, int sizeState, int x_frontState,int y_frontState,int number, int x_dir, int y_dir) {
		this.colorState = colorState;
		this.sizeState = sizeState;
		this.x_frontState = x_frontState;
		this.y_frontState= y_frontState;
		this.number = number;
		this.verSpeedState = 0;
		this.horSpeedState = 0;
		this.x_dir = x_dir;
		this.y_dir = y_dir;
	}
	

	/**
	 * constructor, the same as others but get the state parameters as array
	 * @param arrState
	 */
	public Memento(Object arrState[]) {
		if(arrState.length == 5) {
			this.colorState = (Color) arrState[0];
			this.sizeState = (int) arrState[1];
			this.x_frontState = (int) arrState[2];
			this.y_frontState= (int) arrState[3];
			this.number = (int) arrState[4];
			this.verSpeedState = 0;
			this.horSpeedState = 0;
			this.x_dir = 0;
			this.y_dir = 0;
		}else {
			this.colorState = (Color) arrState[0];
			this.sizeState = (int) arrState[1];
			this.x_frontState = (int) arrState[2];
			this.y_frontState= (int) arrState[3];
			this.verSpeedState = (int) arrState[4];
			this.horSpeedState = (int) arrState[5];
			this.number = (int) arrState[6];
			this.x_dir = (int) arrState[7];
			this.y_dir = (int) arrState[8];
			}
	}
	/**
	 * return array of states values in this order - color, size, x_front, Y_front, verSpeed, horSpeed
	 * @return
	 */
	public Object getState() {
		Object arrState[] = {this.colorState, this.sizeState, this.x_frontState,this.y_frontState,this.verSpeedState,this.horSpeedState,this.number,this.x_dir,this.y_dir};
		return arrState;
	}
	/**
	 * ver speed getter
	 * @return
	 */
	public int getVerSpeedState() {
		return verSpeedState;
	}
	
	/**
	 * get color
	 * @return
	 */
	public Color getColorState() {
		return colorState;
	}

	/**
	 * get size
	 * @return
	 */
	public int getSizeState() {
		return sizeState;
	}

	/**
	 * get x
	 * @return
	 */
	public int getX_frontState() {
		return x_frontState;
	}

	/**
	 * get y
	 * @return
	 */
	public int getY_frontState() {
		return y_frontState;
	}

	/**
	 * get hor speed
	 * @return
	 */
	public int getHorSpeedState() {
		return horSpeedState;
	}

	/**
	 * get number
	 * @return
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * return x direction
	 * @return
	 */
	public int getX_dir() {
		return x_dir;
	}

	/**
	 * return y direction
	 * @return
	 */
	public int getY_dir() {
		return y_dir;
	}
}
