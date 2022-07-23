package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Aquarium.AnimalFactory;
import Aquarium.Fish;
import Aquarium.Hungry;
import Aquarium.Immobile;
import Aquarium.Jellyfish;
import Aquarium.PlantFactory;
import Aquarium.Singleton;
import Aquarium.Swimmable;
import Aquarium.JPanelDecorator;

/**
 * generates the main panel
 * @author 
 *
 */
public class AquaPanel extends JPanel implements ActionListener,Observer{
	
	public JFrame frame;
	String[] columnNames = {"Animal","Color","Size","Hor.speed","Ver.speed","Eat counter"}; 
	JTable myTable;
	JButton addAnimal, sleep, wakeUp, reset, food, info, exit,addPlant,DuplicateAnimal,decorator;
	JScrollPane sp;
	int counter = 0;
    public HashSet<Swimmable> set = new HashSet();
    public HashSet<Immobile> setPlant = new HashSet();
    Boolean flag = false;
    Boolean foodFlag = false;
    Boolean wormFlag = false;
	public AddAnimalDialog ADW = null;
	int plantSizeValue = 0;
	Swimmable animal = null;
	Immobile plant = null;
	public static int animalCounter = 0;
	public static int plantCounter = 0;
	JPanelDecorator JPanelDecorator;
	public static int foodCounter = 0;
	

	/**
	 * panel constructor
	 */
	public AquaPanel() {
    	setLayout(null);
    	
		//add buttons in the bottom of the main panel
		addAnimal = new JButton("Add Animal");
		addAnimal.setBounds(0, 408, 102, 30);
		add(addAnimal);
		addAnimal.addActionListener(this);
		
		DuplicateAnimal = new JButton("Duplicate Animal");
		DuplicateAnimal.setBounds(804, 408, 130, 30);
		add(DuplicateAnimal);
		DuplicateAnimal.addActionListener(this);
		
		sleep = new JButton("Sleep");
		sleep.setBounds(100, 408, 102, 30);
		add(sleep);
		sleep.addActionListener(this);

		wakeUp = new JButton("Wake Up");
		wakeUp.setBounds(202, 408, 102, 30);
		add(wakeUp);
		wakeUp.addActionListener(this);
		
		reset = new JButton("Reset");
		reset.setBounds(302, 408, 102, 30);
		add(reset);
		reset.addActionListener(this);
		
		food = new JButton("Food");
		food.setBounds(402, 408, 102, 30);
		add(food);
		food.addActionListener(this);
		
		addPlant = new JButton("Add Plant");
		addPlant.setBounds(702, 408, 102, 30);
		add(addPlant);
		addPlant.addActionListener(this);
		
		info = new JButton("Info");
		info.setBounds(502, 408, 102, 30);
		add(info);
		info.addActionListener(this);
		
		exit = new JButton("Exit");
		exit.setBounds(602, 408, 102, 30);
		add(exit);
		exit.addActionListener(this);
		
		decorator = new JButton("Decorator");
		decorator.setBounds(934, 408, 102, 30);
		add(decorator);
		decorator.addActionListener(this);
		
    }
    
	/**
	 * perform different actions by button clicks
	 */
    public void actionPerformed(ActionEvent e) {
    	//open an add decorator windows
    	if (e.getSource().equals(decorator)) {
    		if(set.size() ==0) {
    			JOptionPane.showMessageDialog(this,"The aquarium is empty");  
    		}else {
    			JPanelDecorator JPanelDecorator = new JPanelDecorator();
    		}
    	}
    	//add new animal to panel
		if (e.getSource().equals(addAnimal)) {
			
			if(set.size() >= 5) {
				JOptionPane.showMessageDialog(this,"You can add up to five animals");  
			}else {
				ADW = new AddAnimalDialog();

				AnimalFactory newAnimal = new AnimalFactory();
				
				if(ADW.animalType == 0) {
					animalCounter++;
					animal = (Swimmable) newAnimal.produceSeaCreature("Fish");
					animal.registerObserver(this);
				}else {
					animalCounter++;
					animal = (Swimmable) newAnimal.produceSeaCreature("JellyFish");
					animal.registerObserver(this);
				}
				this.set.add(animal);
				animal.start();
			}
		}
		//add new plant to panel
		if (e.getSource().equals(addPlant)) {
			
			if(setPlant.size() >= 5) {
				JOptionPane.showMessageDialog(this,"You can add up to five plants");  
			}else {
				
				//Plant type selection
				Object[] options = {"Zostera","Laminaria"};
				int plantType = JOptionPane.showOptionDialog(frame,"Select a plant to add","Add Plant Window",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
				
				
				//animal size selection
				JSlider plantlSize = new JSlider(JSlider.HORIZONTAL, 20, 320,20);  
				plantlSize.setMinorTickSpacing(10);  
				plantlSize.setMajorTickSpacing(50);  
				plantlSize.setPaintTicks(true);  
				plantlSize.setPaintLabels(true);
				JOptionPane.showMessageDialog(null, plantlSize, "Select plant size",JOptionPane.QUESTION_MESSAGE);
				plantSizeValue = plantlSize.getValue();
				
				PlantFactory newPlant = new PlantFactory();
				
				if(plantType == 0) {
					plantCounter++;
					plant = (Immobile) newPlant.produceSeaCreature("Zostera");
				}else {
					plantCounter++;
					plant = (Immobile) newPlant.produceSeaCreature("Laminaria");
				}
				this.setPlant.add(plant);
			}
			repaint();
		}
		//close the program
		if (e.getSource().equals(exit)) {
			for(Swimmable i: set) {
				i.stop();
			}
			this.set.clear();
			System.exit(0);
		}
			
		//delete all animals
		if (e.getSource().equals(reset)) {
			for(Swimmable i: set) {
				i.stop();
			}
			this.set.clear();
			this.setPlant.clear();
			AquaFrame.caretaker.getAnimalStateList().clear();
			AquaFrame.caretaker.getPlantStateList().clear();
			animalCounter = 0;
			foodCounter = 0;
			this.foodFlag = false;
			this.wormFlag = false;
			repaint();
		}
		//show hash table
		if (e.getSource().equals(info)) {
			
			counter++;
			if(counter % 2 != 0) {
				Object[][] data = new Object[set.size() + 1][6];
				String[] components = {"Animal","Color","Size","Hor.speed","Ver.speed","Eat counter"};
				
				int j = 0;
				int eatCountSum = 0;
				for(Swimmable i: set) {
					data[j][0] = i.getAnimalName();
					data[j][1] = returnColorName(i.getColor());
					data[j][2] = i.getSize();
					data[j][3] = Math.abs(i.getHorSpeed());
					data[j][4] = Math.abs(i.getVerSpeed());
					data[j][5] = i.getEatCount();
					eatCountSum = eatCountSum + i.getEatCount();
					j++;
				}
				
				data[set.size()][0] = "Total";
				data[set.size()][5] = eatCountSum;
				
				myTable = new JTable(data,components);
				sp = new JScrollPane(myTable);
				sp.setBounds(0, 0, 1040, 410);
				this.add(sp);
				sp.setVisible(true);	
			}else {
				sp.setVisible(false);
			}
		}
		//stop the animal movement
		if (e.getSource().equals(sleep)) {
			if(set.size() != 0) {
				for(Swimmable i : set) {
					i.setSuspend();
				}
			}
		}
		//renew the animal movement
		if (e.getSource().equals(wakeUp)) {
			if(set.size() != 0) {
				for(Swimmable i : set) {
					i.setResume();
				}
			}
		}
		//feed the animals
		if (e.getSource().equals(food)) {
			this.foodFlag = true;
			if(set.size() != 0) {
				this.wormFlag = true;
				for(Swimmable i :set) {
					i.hunger++;
				}
			}
		}
		//choose an animal to clone
		if (e.getSource().equals(DuplicateAnimal)) {
			if(set.size() == 0) {
				JOptionPane.showMessageDialog(this,"There are no animals to duplicate"); 
			}else if(set.size() >=5){
				JOptionPane.showMessageDialog(this,"The aquarium capacity has reached to the limit"); 
			}else {
				
				Object[][] data = new Object[set.size()][6];
				String[] components = {"Animal","Color","Size","Hor.speed","Ver.speed","Eat counter"};
				
				int j = 0;
				int eatCountSum = 0;
				for(Swimmable i: set) {
					data[j][0] = i.getAnimalName();
					data[j][1] = returnColorName(i.getColor());
					data[j][2] = i.getSize();
					data[j][3] = Math.abs(i.getHorSpeed());
					data[j][4] = Math.abs(i.getVerSpeed());
					data[j][5] = i.getEatCount();
					eatCountSum = eatCountSum + i.getEatCount();
					j++;
				}
				
				
				myTable = new JTable(data,components);
				sp = new JScrollPane(myTable);
				sp.setBounds(0, 0, 1050, 410);
				this.add(sp);
				sp.setVisible(true);	
				JOptionPane.showMessageDialog(this,"Please select animal to duplicate"); 
				
				myTable.setCellSelectionEnabled(true);
				ListSelectionModel cellSelectionModel = myTable.getSelectionModel();
			    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			    
			    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			        public void valueChanged(ListSelectionEvent e) {
			        	if (! e.getValueIsAdjusting()){
			        		int[] selectedRow = myTable.getSelectedRows();
					          
					          int j = 0;
					          for(Swimmable i: set) {
					        	  if(j != selectedRow[0]) {
					        		  j++;
					        		  continue;
					        	  }else{
					  				if(i.getClass().getName() == "Aquarium.Fish") {
					  					Fish newSwimmable = (Fish) i.clone();
					  					AquaFrame.myPanel.set.add(newSwimmable);
						  				newSwimmable.start();
						  				String[] buttons = { "size", "vertical speed", "horizontal speed", "color" };    
						  				int returnValue = JOptionPane.showOptionDialog(null, "Select one value to change", "Change animal values",JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[0]);
						  				if(returnValue == 0) {
						  					JSlider animalSize = new JSlider(JSlider.HORIZONTAL, 20, 320,20);
						  					animalSize.setMinorTickSpacing(10);  
						  					animalSize.setMajorTickSpacing(50);  
						  					animalSize.setPaintTicks(true);  
						  					animalSize.setPaintLabels(true);
						  					JOptionPane.showMessageDialog(null, animalSize, "Select animal size",JOptionPane.QUESTION_MESSAGE);
						  					newSwimmable.setSize(animalSize.getValue());
						  				}else if (returnValue == 1) {
						  					JSlider verSpeed = new JSlider(JSlider.HORIZONTAL, 1, 10,1);  
						  					verSpeed.setMinorTickSpacing(1);  
						  					verSpeed.setMajorTickSpacing(1);  
						  					verSpeed.setPaintTicks(true);  
						  					verSpeed.setPaintLabels(true);
						  					JOptionPane.showMessageDialog(null, verSpeed, "Select vertical speed",JOptionPane.QUESTION_MESSAGE);
						  					newSwimmable.setVerSpeed(verSpeed.getValue());
						  				}else if (returnValue == 2) {
						  					JSlider horSpeed = new JSlider(JSlider.HORIZONTAL, 1, 10,1);  
						  					horSpeed.setMinorTickSpacing(1);  
						  					horSpeed.setMajorTickSpacing(1);  
						  					horSpeed.setPaintTicks(true);  
						  					horSpeed.setPaintLabels(true);
						  					JOptionPane.showMessageDialog(null, horSpeed, "Select horizontal speed",JOptionPane.QUESTION_MESSAGE);
						  					newSwimmable.setHorSpeed(horSpeed.getValue());
						  				}else {
						  					String[] color = {"Black","Red", "Blue", "Green","Cyan","Orange","Yellow","Magenta","Pink"};
						  					JComboBox colorBox = new JComboBox(color);
						  					colorBox.setEditable(false);
						  					JOptionPane.showMessageDialog(null, colorBox, "Select animal color",JOptionPane.QUESTION_MESSAGE);
						  					String colorValue = colorBox.getSelectedItem().toString();
						  					newSwimmable.setCol(returnColorNumber(colorValue));
						  				}
						        		break;
					  				}else {
					  					Jellyfish newSwimmable = (Jellyfish) i.clone();
					  					AquaFrame.myPanel.set.add(newSwimmable);
						  				newSwimmable.start();
						  				String[] buttons = { "size", "vertical speed", "horizontal speed", "color" };    
						  				int returnValue = JOptionPane.showOptionDialog(null, "Select one value to change", "Change animal values",JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[0]);
						  				if(returnValue == 0) {
						  					JSlider animalSize = new JSlider(JSlider.HORIZONTAL, 20, 320,20);
						  					animalSize.setMinorTickSpacing(10);  
						  					animalSize.setMajorTickSpacing(50);  
						  					animalSize.setPaintTicks(true);  
						  					animalSize.setPaintLabels(true);
						  					JOptionPane.showMessageDialog(null, animalSize, "Select animal size",JOptionPane.QUESTION_MESSAGE);
						  					newSwimmable.setSize(animalSize.getValue());
						  				}else if (returnValue == 1) {
						  					JSlider verSpeed = new JSlider(JSlider.HORIZONTAL, 1, 10,1);  
						  					verSpeed.setMinorTickSpacing(1);  
						  					verSpeed.setMajorTickSpacing(1);  
						  					verSpeed.setPaintTicks(true);  
						  					verSpeed.setPaintLabels(true);
						  					JOptionPane.showMessageDialog(null, verSpeed, "Select vertical speed",JOptionPane.QUESTION_MESSAGE);
						  					newSwimmable.setVerSpeed(verSpeed.getValue());
						  				}else if (returnValue == 2) {
						  					JSlider horSpeed = new JSlider(JSlider.HORIZONTAL, 1, 10,1);  
						  					horSpeed.setMinorTickSpacing(1);  
						  					horSpeed.setMajorTickSpacing(1);  
						  					horSpeed.setPaintTicks(true);  
						  					horSpeed.setPaintLabels(true);
						  					JOptionPane.showMessageDialog(null, horSpeed, "Select horizontal speed",JOptionPane.QUESTION_MESSAGE);
						  					newSwimmable.setHorSpeed(horSpeed.getValue());
						  				}else {
						  					String[] color = {"Black","Red", "Blue", "Green","Cyan","Orange","Yellow","Magenta","Pink"};
						  					JComboBox colorBox = new JComboBox(color);
						  					colorBox.setEditable(false);
						  					JOptionPane.showMessageDialog(null, colorBox, "Select animal color",JOptionPane.QUESTION_MESSAGE);
						  					String colorValue = colorBox.getSelectedItem().toString();
						  					newSwimmable.setCol(returnColorNumber(colorValue));
						  				}
						        		break;
					  				}
					        	  }
					          }
					          sp.setVisible(false);
			        	}
			        }
			     });
			}
		}
	}
    

	/**
     * 
     * @return flag
     */
	public Boolean getFlag() {
		return flag;
	}
	
	/**
	 * set flag value 
	 * @param flag
	 */
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	/**
	 * 
	 * @return foodFlag
	 */
	public Boolean getFoodFlag() {
		return foodFlag;
	}
	
	/**
	 * set foodFlag
	 * @param foodFlag
	 */
	public void setFoodFlag(Boolean foodFlag) {
		this.foodFlag = foodFlag;
	}
	
	/**
	 * paint the objects
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(AquaFrame.getbackground() != null) {
			Dimension DM = getSize();
			g.drawImage(AquaFrame.getbackground(), 0, 0, DM.width, DM.height, this);
		}
		if(this.getWormFlag()) {
			Dimension DM = getSize();
			Image worm = Singleton.getInstance().getWorm();//creates one instance of worm and use the same worm each feeding!
			g.drawImage(worm, DM.width/2-25, DM.height/2-25, 50, 50 , this);
		}
		
		for(Swimmable i: set) {
			i.drawCreature(g);
		}
		
		for(Immobile i: setPlant) {
			i.drawCreature(g);
		}
	}

	/**
	 * 
	 * @return wormFlag
	 */
	public Boolean getWormFlag() {
		return wormFlag;
	}
	/**
	 * set worm Flag
	 * @param wormFlag
	 */
	public void setWormFlag(Boolean wormFlag) {
		this.wormFlag = wormFlag;
	}
	
	/**
	 * increase fish
	 * @param obj
	 */
	public void IncreaseFoodCount(Swimmable obj) {
		obj.eatInc();
	}
	
	/**
	 * get adw
	 * @return adw
	 */
    public AddAnimalDialog getADW() {
		return ADW;
	}
	
    /**
     * get plant size
     * @return plant size
     */
    public int getPlantSizeValue() {
		return plantSizeValue;
	}
    

    /**
     * get plant hashset
     * @return plant hashset
     */
	public HashSet<Immobile> getSetPlant() {
		return setPlant;
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
    /**
     * 
     * @param color
     * @return color name
     */
    public String returnColorName(Color color) {
    	if(color.equals(Color.black)) {
    		return "Black";
    	}else if(color.equals(Color.red)) {
    		return "Red";
    	}else if(color.equals(Color.blue)) {
    		return "Blue";
    	}else if(color.equals(Color.green)) {
    		return "Green";
    	}else if(color.equals(Color.cyan)) {
    		return "Cyan";
    	}else if(color.equals(Color.orange)) {
    		return "Orange";
    	}else if(color.equals(Color.yellow)) {
    		return "Yellow";
    	}else if(color.equals(Color.magenta)) {
    		return "Magenta";
    	}else if(color.equals(Color.pink)) {
    		return "Pink";
    	}else{
    		return "Costume color";
    	}
	}
    
	/**
	 * Implement update method of Observer interface.
	 */
	public void update(Observable o, Object arg) {
		JOptionPane.showMessageDialog(this,arg);
	}
}
