/**
 * Panel.java
 * Handles GUI controls like sliders
 */

 import java.awt.*;
 import javax.swing.*;
 import javax.swing.event.*;
 import java.util.*;
 import java.awt.event.*;

class Panel extends JPanel implements ChangeListener, ActionListener{

	// sliders
	JSlider scalingSlider, constantSlider;
	JLabel rlabel1,rlabel2,rlabel3;

	// state that will keep track of current state
	// like how many squares should be on the screen
	HashMap state;

	// current instance of canvas so that
	// we can tell canvas to update on user input
	SimpleCanvas myCanvas;

 	public Panel(SimpleCanvas _myCanvas){

		myCanvas = _myCanvas;

		setLayout(new GridLayout(1,3,30,10));

		// initial state
		String[] functionNames = myCanvas.functionNames;

		// initialize slider for changing Lambda value
    scalingSlider = new JSlider(JSlider.HORIZONTAL,1,100,1);
		scalingSlider.setMajorTickSpacing(10);
		scalingSlider.setMinorTickSpacing(5);

		// initialize labels
		rlabel1 = new JLabel("Choose Function:");
		rlabel2 = new JLabel("Choose scaling factor:");
		rlabel3 = new JLabel("Choose constant a:");

		JComboBox chooseElement = new JComboBox(functionNames);
			// add ActionListener to JComboBox
			chooseElement.addActionListener (new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						String chosenElement = (String)chooseElement.getSelectedItem();
						myCanvas.state.put("func",chosenElement);
						myCanvas.update();
					}
			});

		// initialize slider for changing the number of desired col or row value
		constantSlider = new JSlider(JSlider.HORIZONTAL,1,11,1);
		constantSlider.setMajorTickSpacing(2);
		constantSlider.setMinorTickSpacing(1);
		for (JSlider slider: new JSlider[] {scalingSlider,constantSlider}){
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			slider.addChangeListener(this);
		}

		// create JPanel and add all the components to it
		JPanel r = new JPanel();
		r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));
		for(JComponent component: new JComponent[]{rlabel1,chooseElement,rlabel2,
			scalingSlider,rlabel3,constantSlider})r.add(component);

		add(r); // add the newly created JPanel to this class
		// updateStateGUI(); // render updates

  }//end contructor

	// listen for changes in values of sliders
  public void stateChanged(ChangeEvent ev){
    // get lambda value
		int scaleVal = scalingSlider.getValue();
		// get number of row col value
		int aVal = constantSlider.getValue();
		// update state
		myCanvas.state.put("scale",scaleVal);
		myCanvas.state.put("a",aVal);
		// render updates
		updateStateGUI();
	}//end stateChanged

	public void updateStateGUI(){
		// render changes in "state"
		// String mode = (String)state.get("mode");
		// char[] arr = mode.toCharArray();
		// if(arr[0] == 'S'){
		// 	rlabel3.setVisible(false);
		// 	constantSlider.setVisible(false);
		// 	state.put("rowcolVal",1);
		// 	constantSlider.setValue(1);
		// }else{
		// 	rlabel3.setVisible(true);
		// 	constantSlider.setVisible(true);
		// }

		// call canvas to re-create squares based on
		// changes in "state"
		myCanvas.update();
	}//end updateStateGUI

	// send updates to Canvas
	public void actionPerformed(ActionEvent e) {
		// JButton btn = (JButton)e.getSource(); // get which button was clicked
		// myCanvas.btnClicked(btn.getText()); // send command to Canvas
	}// end actionPerformed

}// Panel
