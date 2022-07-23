package Aquarium;

import java.awt.Image;

import javax.swing.ImageIcon;


/**
 * Singleton class - prevent from creating multiple instances of worms 
 * @author 
 *
 */
public class Singleton {
	
	private static volatile Singleton instance = null;
	Image worm = null;
	
	/**
	 * A private Constructor prevents any other * class from instantiating.
	 */
	private Singleton(){
		worm = new ImageIcon("worm.png").getImage();
	}

	/**
	 * Static 'instance' method
	 * @return
	 */
	public static Singleton getInstance( ){
		if(instance == null) {
			synchronized(Singleton.class){
				if(instance == null) {
					instance = new Singleton();		
				}
			}
		}
		return instance;
	}
	
	/**
	 * "delete" the current instance of the worm
	 */
	public static void setInstanceToNull(){
		instance = null;
	}

	/**
	 * get worm 
	 * @return worm image
	 */
	public Image getWorm() {
		return worm;
	}
}
