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

		JComboBox functionsChooser = new JComboBox(functionNames);
			// add ActionListener to JComboBox
			functionsChooser.addActionListener (new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						String choseFunc = (String)functionsChooser.getSelectedItem();
						myCanvas.state.put("func",choseFunc);
						setScaleValue(choseFunc);
						scalingSlider.setValue(30);
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
		for(JComponent component: new JComponent[]{rlabel1,functionsChooser,rlabel2,
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
		myCanvas.update();
	}//end stateChanged

	public void setScaleValue(String choseFunc){
		int scaleFactor = 30;
		switch(choseFunc){
			case "r = cos(3*theta)": scaleFactor = 70; break;
			case "r = a(theta)": scaleFactor = 15; break;
		}
		scalingSlider.setValue(scaleFactor);
	}

	// ActionListener method
	public void actionPerformed(ActionEvent e) {
	}// end actionPerformed

}// Panel
