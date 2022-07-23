package GUI;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Aquarium.Caretaker;
import Aquarium.Fish;
import Aquarium.Immobile;
import Aquarium.Jellyfish;
import Aquarium.Memento;
import Aquarium.Swimmable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

/**
 * generates the frame, include main method
 * @author 
 *
 */
public class AquaFrame extends JFrame implements ActionListener{
	
	public static JFrame myframe;
	JMenuItem Exit,Image,Blue,None,Help,SaveObjectState,RestoreObjectState;
	JMenuBar z;
	JButton addAnimal, sleep, wakeUp, reset, food, info, exit;
	public static AquaPanel myPanel;
	private static Image background=null;
	private static final long serialVersionUID = 1L;
	private String[] barNameArr = {"File","Background","Help","Memento"};
	private JMenu[] barArr = {null,null,null,null};
	private static Boolean backgroundFlag = false;
	JTable myTable,myTablePlant;
	JScrollPane sp,spp;
	public static Caretaker caretaker = new Caretaker();
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		AquaFrame frame = new AquaFrame("my Aquarium");
		frame.setSize(1050, 500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true);
		
	}

	/**
	 * frame constructor
	 * @param string
	 */
	public AquaFrame(String string) {
		super(string);
		
		myframe = new JFrame();
		
		//Creates the menu objects
		for(int i =0; i < 4 ; i++) {
			barArr[i] = new JMenu(barNameArr[i]);  
		}
		
		//creates the menu items
		Exit = new JMenuItem("Exit");
		Exit.addActionListener(this);
		Image = new JMenuItem("Image");
		Image.addActionListener(this);
		Blue = new JMenuItem("Blue");
		Blue.addActionListener(this);
		None = new JMenuItem("None");
		None.addActionListener(this);
		Help = new JMenuItem("Help");
		Help.addActionListener(this);
		SaveObjectState = new JMenuItem("Save Object State");
		SaveObjectState.addActionListener(this);
		RestoreObjectState = new JMenuItem("Restore Object State");
		RestoreObjectState.addActionListener(this);
		
		//add the items to menu buttons
		barArr[0].add(Exit);
		barArr[1].add(Image);
		barArr[1].add(Blue);
		barArr[1].add(None);
		barArr[2].add(Help);
		barArr[3].add(SaveObjectState);
		barArr[3].add(RestoreObjectState);
		
		//add the menu items to the menu bar
		z=new JMenuBar();
		z.add(barArr[0]);
		z.add(barArr[1]);
		z.add(barArr[2]);
		z.add(barArr[3]);
		setJMenuBar(z);
		
		myPanel = new AquaPanel();
		
		add(myPanel);
	}
	
	/**
	 * perform different actions by button clicks
	 */
	public void actionPerformed(ActionEvent e) {
		//close the program
		if (e.getSource().equals(Exit) || e.getSource().equals(exit))
			System.exit(0);
		//change background to blue
		if (e.getSource().equals(Blue))		
		{
			myPanel.setBackground(Color.blue);
			background=null;
		}
		//set the background to default
		if (e.getSource().equals(None))
		{
			myPanel.setBackground(Color.getColor(null));
			background=null;
		}
		//show help massage box
		if (e.getSource().equals(Help)) {
			JOptionPane.showMessageDialog(this,"Home Work 3\nGUI @ Threads");//open a message dialog	
		}
		//put an image as background 
		if (e.getSource().equals(Image)) {
			background=new ImageIcon("background.jpg").getImage();
			myPanel.repaint();
		}
		//save state of an object
		if (e.getSource().equals(SaveObjectState)) {
			if(myPanel.set.isEmpty() && myPanel.setPlant.isEmpty()) {
				JOptionPane.showMessageDialog(this,"The aquarium is empty");
			}else {
				Object[][] data = new Object[myPanel.set.size() + myPanel.setPlant.size()][6];
				String[] components = {"Name","Color","Size","Hor.speed","Ver.speed","Eat counter"};
				
				int j = 0;
				for(Swimmable i: myPanel.set) {
					data[j][0] = i.getAnimalName();
					data[j][1] = returnColorName(i.getColor());
					data[j][2] = i.getSize();
					data[j][3] = Math.abs(i.getHorSpeed());
					data[j][4] = Math.abs(i.getVerSpeed());
					data[j][5] = i.getEatCount();
					j++;
				}
				for(Immobile i: myPanel.setPlant) {
					data[j][0] = i.getPlantName();
					data[j][1] = "Green";
					data[j][2] = i.getSize();
					data[j][3] = "Irrelevant";
					data[j][4] = "Irrelevant";
					data[j][5] = "Irrelevant";
					j++;
				}
				
				myTable = new JTable(data,components);
				
				sp = new JScrollPane(myTable);
				
				sp.setBounds(0, 0, 1040, 410);
				
				myPanel.add(sp);
				
				sp.setVisible(true);
				
				JOptionPane.showMessageDialog(this,"Please choose an Object to save");
				
				//action listener - wait for row selection
				myTable.setCellSelectionEnabled(true);
				ListSelectionModel cellSelectionModel = myTable.getSelectionModel();
			    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			    
			    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			        public void valueChanged(ListSelectionEvent e) {
			        	if (! e.getValueIsAdjusting()){
			        		//if plant
			        		if(data[myTable.getSelectedRow()][3] == "Irrelevant") {
			        			int j = 0;
			        			for(Immobile i: myPanel.setPlant) {
			        				if(myTable.getSelectedRow()- myPanel.set.size() != j) {
			        					j++;
			        				}else {
			        					Object arr[] = {returnColorNumber("Green"),i.getSize(),i.getX(),i.getY(),i.getIndexSeaPlant(),0,0};
			        					i.setState(arr);
			        					Memento memento = i.createMemento();
			        					caretaker.addMemento(memento);
			        					break;
			        				}
			        			}
			        		}else {//if animal
			        			int j = 0;
			        			for(Swimmable i: myPanel.set) {
			        				if(myTable.getSelectedRow() != j) {
			        					j++;
			        				}else {
			        					Object arr[] = {i.getColor(),i.getSize(),i.getX_front(),i.getY_front(),i.getVerSpeed(),i.getHorSpeed(),i.getSeaCreatureNumber(),i.getX_dir(),i.getY_dir()};
			        					i.setState(arr);
			        					Memento memento = i.createMemento();
			        					caretaker.addMemento(memento);
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
		if (e.getSource().equals(RestoreObjectState)) {
			if(caretaker.getPlantStateList().size() == 0 && caretaker.getAnimalStateList().size() == 0) {
				JOptionPane.showMessageDialog(this,"There are zero states saved");
			}else {
				Object[][] data = new Object[caretaker.getPlantStateList().size() + caretaker.getAnimalStateList().size()][6];
				String[] components = {"Name","number"};
				
				int j = 0;
				for(Swimmable i: myPanel.set) {
					if(i.getstate() != null) {
						data[j][0] = i.getAnimalName();
						data[j][1] = i.getSeaCreatureNumber();
						j++;
					}
				}
				for(Immobile i: myPanel.setPlant) {
					if(i.getstate() != null) {
						data[j][0] = i.getPlantName();
						data[j][1] = i.getIndexSeaPlant();
						j++;
					}
				}
				
				myTable = new JTable(data,components);
				
				sp = new JScrollPane(myTable);
				
				sp.setBounds(0, 0, 1040, 410);
				
				myPanel.add(sp);
				
				sp.setVisible(true);
				
				JOptionPane.showMessageDialog(this,"Please choose an Object to restore");
				
				//action listener - wait for row selection
				myTable.setCellSelectionEnabled(true);
				ListSelectionModel cellSelectionModel = myTable.getSelectionModel();
			    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			    
			    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			        public void valueChanged(ListSelectionEvent e) {
			        	if (! e.getValueIsAdjusting()){
			        		//if plant
			        		if(data[myTable.getSelectedRow()][0] == "Zostera" || data[myTable.getSelectedRow()][0] == "Laminaria") {
			        			int index = (int) data[myTable.getSelectedRow()][1];
			        			Memento current = caretaker.getMemento(index, data[myTable.getSelectedRow()][0].toString());
			        			
			        			for(Immobile i : myPanel.setPlant) {
			        				if(i.getIndexSeaPlant() == index) {
			        					i.setSize(current.getSizeState());
			        					i.setX(current.getX_frontState());
			        					i.setY(current.getY_frontState());
			        					break;
			        				}
			        			}
			        		}else {//if animal
			        			int index = (int) data[myTable.getSelectedRow()][1];
			        			Memento current = caretaker.getMemento(index,data[myTable.getSelectedRow()][0].toString());
			        			
			        			for(Swimmable i : myPanel.set) {
			        				if(i.getSeaCreatureNumber() == index) {
			        					i.setSize(current.getSizeState());
			        					i.setCol(current.getColorState());
			        					i.setX_front(current.getX_frontState());
			        					i.setY_front(current.getY_frontState());
			        					i.setVerSpeed(current.getVerSpeedState());
			        					i.setHorSpeed(current.getHorSpeedState());
			        					i.setX_dir(current.getX_dir());
			        					i.setY_dir(current.getY_dir());
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
	 * @return panel
	 */
	public static AquaPanel getPanel() {
		return myPanel;
	}
	/**
	 *  
	 * @return background
	 */
	public static Image getbackground()
	{
		return background;
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
	
}