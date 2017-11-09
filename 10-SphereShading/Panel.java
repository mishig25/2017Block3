/**
 * Panel.java
 * Handles GUI controls like sliders
 */

 import java.awt.*;
 import javax.swing.*;
 import javax.swing.event.*;
 import java.util.*;
 import java.awt.event.*;

class Panel extends JPanel implements ChangeListener{

  // instance vars
	JSlider xSlider; // slider for moving light source
	JLabel rlabel0; // label

	// current instance of canvas so that
	// we can tell canvas to update on user input
	Canvas myCanvas;

 	public Panel(Canvas _myCanvas){

		myCanvas = _myCanvas;
		setLayout(new GridLayout(1,3,30,10));

		// initialize slider for changing X value
    xSlider = new JSlider(JSlider.HORIZONTAL,-200,200,0);
    initSlider(xSlider);

		// initialize label
    rlabel0 = new JLabel("Move Light Source along X-axis");

		// create JPanel and add all the components to it
		JPanel r = new JPanel();
		r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));

    // add components to the parent JPanel
    for(JComponent component: new JComponent[]{rlabel0,xSlider}) r.add(component);

		add(r); // add the newly created JPanel to this class

  }//end contructor

  // initialize X value slider
  public void initSlider(JSlider slider){
    slider.setMajorTickSpacing(60);
    slider.setMinorTickSpacing(5);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.addChangeListener(this);
  }// end initSlider

	// listen for changes in value of xSlider
  public void stateChanged(ChangeEvent ev){
    // get scale value
		int xVal = xSlider.getValue();
		// update state
		myCanvas.state.put("cameraX",xVal);
		// render updates
		myCanvas.update();
	}//end stateChanged

}// Panel
