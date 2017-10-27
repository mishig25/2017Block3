/**
 * Panel.java
 * Handles GUI controls like sliders
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

class Panel extends JPanel implements ChangeListener{

	// sliders
	JSlider lambdaSlider, rowcolSlider;
  JToggleButton toggle;
	JLabel rlabel1,rlabel2,rlabel3;

	// state that will keep track of current state
	// like how many squares should be on the screen
	HashMap state;

	// current instance of canvas so that
	// we can tell canvas to update on user input
	Canvas myCanvas;

 	public Panel(Canvas _myCanvas){

		myCanvas = _myCanvas;

		setLayout(new GridLayout(1,3,30,10));

		// initial state
		state = new HashMap();
		state.put("lambda",(float)0.05);
		state.put("mode","SINGLE");
		state.put("rowcol","ROW");
		state.put("rowcolVal",1);

		// initialize slider for changing Lambda value
    lambdaSlider = new JSlider(JSlider.HORIZONTAL,5,95,5);
		lambdaSlider.setMajorTickSpacing(10);
		lambdaSlider.setMinorTickSpacing(5);
		Hashtable lambdaValStrings = new Hashtable();
		int label_val = 0;
		while (label_val < 91)lambdaValStrings.put( new Integer( label_val+=10 ),
			new JLabel(String.format("%.1f",(float)label_val/100)));
		lambdaSlider.setLabelTable( lambdaValStrings );

		// initialize labels
		rlabel1 = new JLabel("Choose Lambda:");
		rlabel2 = new JLabel("Choose mode:");
		rlabel3 = new JLabel("Choose row or col N:");

		// initialize JToggleButton for toggling between "SINGLE" or "PATTERN" mode
		toggle = new JToggleButton("SINGLE");
		toggle.addChangeListener(new ChangeListener() {
				// listen for changes in JToggleButton
        @Override
        public void stateChanged(ChangeEvent event) {
					String newVal = toggle.isSelected() ? "PATTERN" : "SINGLE";
					state.put("mode",newVal);
					updateStateGUI();
        }
    });

		// initialize slider for changing the number of desired col or row value
		rowcolSlider = new JSlider(JSlider.HORIZONTAL,1,31,1);
		rowcolSlider.setMajorTickSpacing(5);
		rowcolSlider.setMinorTickSpacing(1);
		for (JSlider slider: new JSlider[] {lambdaSlider,rowcolSlider}){
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			slider.addChangeListener(this);
		}

		// create JPanel and add all the components to it
		JPanel r = new JPanel();
		r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));
		for(JComponent component: new JComponent[]{rlabel1,lambdaSlider,rlabel2,
			toggle,rlabel3,rowcolSlider})r.add(component);

		add(r); // add the newly created JPanel to this class
		updateStateGUI(); // render updates

  }//end contructor

	// listen for changes in values of sliders
  public void stateChanged(ChangeEvent ev){
    // get lambda value
		float lambdaVal = (float)lambdaSlider.getValue();
		lambdaVal /= 100;
		// get number of row col value
		int rowcolVal = rowcolSlider.getValue();
		// update state
		state.put("lambda",lambdaVal);
		state.put("rowcolVal",rowcolVal);
		// render updates
		updateStateGUI();
	}//end stateChanged

	public void updateStateGUI(){
		// render changes in "state"
		String mode = (String)state.get("mode");
		char[] arr = mode.toCharArray();
		if(arr[0] == 'S'){
			rlabel3.setVisible(false);
			rowcolSlider.setVisible(false);
			state.put("rowcolVal",1);
			rowcolSlider.setValue(1);
		}else{
			rlabel3.setVisible(true);
			rowcolSlider.setVisible(true);
		}
		toggle.setText(mode);

		// call canvas to re-create squares based on
		// changes in "state"
		myCanvas.update(state);
	}//end updateStateGUI

}// Panel
