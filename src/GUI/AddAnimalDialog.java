package GUI;
import java.awt.GridBagConstraints;
import javax.swing.*;

/**
 * generates the box dialogs
 * @author 
 *
 */
public class AddAnimalDialog extends JDialog{
	
	
	public JFrame frame;
	public JPanel panel;
	public int animalType,sizeValue,verSpeedValue,horSpeedValue,foodFreqValue;
	public JComboBox colorBox;
	public String colorValue;
	GridBagConstraints gbc = new GridBagConstraints();
	
	/**
	 * constructor
	 */
	public AddAnimalDialog(){
		
		//animal type selection
		Object[] options = {"Fish","Jellyfish"};
		animalType = JOptionPane.showOptionDialog(frame,"Select an animal to add","Add Animal Window",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		
		//animal size selection
		JSlider animalSize = new JSlider(JSlider.HORIZONTAL, 20, 320,20);  
		animalSize.setMinorTickSpacing(10);  
		animalSize.setMajorTickSpacing(50);  
		animalSize.setPaintTicks(true);  
		animalSize.setPaintLabels(true);
		JOptionPane.showMessageDialog(null, animalSize, "Select animal size",JOptionPane.QUESTION_MESSAGE);
		sizeValue = animalSize.getValue();
		
		//vertical speed selection
		JSlider verSpeed = new JSlider(JSlider.HORIZONTAL, 1, 10,1);  
		verSpeed.setMinorTickSpacing(1);  
		verSpeed.setMajorTickSpacing(1);  
		verSpeed.setPaintTicks(true);  
		verSpeed.setPaintLabels(true);
		JOptionPane.showMessageDialog(null, verSpeed, "Select vertical speed",JOptionPane.QUESTION_MESSAGE);
		verSpeedValue = verSpeed.getValue();
		
		//horizontal speed selection
		JSlider horSpeed = new JSlider(JSlider.HORIZONTAL, 1, 10,1);  
		horSpeed.setMinorTickSpacing(1);  
		horSpeed.setMajorTickSpacing(1);  
		horSpeed.setPaintTicks(true);  
		horSpeed.setPaintLabels(true);
		JOptionPane.showMessageDialog(null, horSpeed, "Select horizontal speed",JOptionPane.QUESTION_MESSAGE);
		horSpeedValue = horSpeed.getValue();
		
		//select the Frequency of the feeding
		JSlider foodFreq = new JSlider(JSlider.HORIZONTAL, 30, 100,30);  
		foodFreq.setMinorTickSpacing(5);  
		foodFreq.setMajorTickSpacing(10);  
		foodFreq.setPaintTicks(true);  
		foodFreq.setPaintLabels(true);
		JOptionPane.showMessageDialog(null, foodFreq,"Select the frequency of the feeding",JOptionPane.QUESTION_MESSAGE);
		foodFreqValue = foodFreq.getValue();
		
		//color selection
		String[] color = {"Black","Red", "Blue", "Green","Cyan","Orange","Yellow","Magenta","Pink"};
		colorBox = new JComboBox(color);
		colorBox.setEditable(false);
		JOptionPane.showMessageDialog(null, colorBox, "Select animal color",JOptionPane.QUESTION_MESSAGE);
		colorValue = colorBox.getSelectedItem().toString();
	}
}
