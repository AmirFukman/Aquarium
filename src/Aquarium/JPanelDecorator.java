package Aquarium;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GUI.AquaFrame;

/**
 * generate new panel with choices of colors for the fish
 * @author 
 *
 */
public class JPanelDecorator extends JFrame implements ActionListener{
	JButton button;
	JTable myTable;
	JFrame frame;
	/**
	 * constructor
	 */
	public JPanelDecorator() {
		
		//creates the frame
		frame = new JFrame("Panel Decorator");
		frame.setSize(500, 400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true);
		
		//create the panel
		JPanel panel = new JPanel();
		frame.add(panel);
		
		Object[][] data = new Object[AquaFrame.myPanel.set.size()][6];
		String[] components = {"Animal","Color","Size","Hor.speed","Ver.speed","Eat counter"};
		
		int j = 0;
		int eatCountSum = 0;
		for(Swimmable i: AquaFrame.myPanel.set) {
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
		JScrollPane sp = new JScrollPane(myTable);
		sp.setBounds(0, 0, 950, 410);
		myTable.setPreferredScrollableViewportSize(myTable.getPreferredSize());
		myTable.setFillsViewportHeight(true);
		panel.add(sp);
		sp.setVisible(true);
		
		//add change color button
		button = new JButton("Change color");
		panel.add(button);
		button.addActionListener(this);
	}
	
	
	/**
	 * perform different actions by button clicks
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(button)) {
			if(myTable.getSelectedRow() < 0) {
				JOptionPane.showMessageDialog(this,"Please choose an animal first from the table");  
			}else {
				int j = 0;
				for(Swimmable i:AquaFrame.myPanel.set) {
					if(j == myTable.getSelectedRow()) {
						Color color=JColorChooser.showDialog(this,"Select a color",Color.black); 
						MarineAnimalDecorator r = new MarineAnimalDecorator(i);
						r.PaintFish(color);
						break;
					}else {
						j++;
					}
				}
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
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

