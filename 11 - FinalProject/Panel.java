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
	JSlider sSlider,ySlider,xSlider; // slider for moving light source
	JLabel rlabel0,rlabel1,rlabel2,rlabel3; // label

	// current instance of canvas so that
	// we can tell canvas to update on user input
	Canvas myCanvas;

 	public Panel(Canvas _myCanvas){

		myCanvas = _myCanvas;
		setLayout(new GridLayout(1,3,30,10));

		// initialize slider for changing X value
    sSlider = new JSlider(JSlider.HORIZONTAL, 0,200,0);
    xSlider = new JSlider(JSlider.HORIZONTAL,-200,200,0);
    ySlider = new JSlider(JSlider.HORIZONTAL,-200,200,0);
    JSlider[] sliders = {sSlider,xSlider,ySlider};
    for(JSlider s: sliders) initSlider(s);

		// initialize labels
    rlabel0 = new JLabel("PARTICLE SYSTEM");
    rlabel1 = new JLabel("Speed:");
    rlabel2 = new JLabel("Force X:");
    rlabel3 = new JLabel("Force Y:");

		// create JPanel and add all the components to it
		JPanel r = new JPanel();
		r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));

    // add components to the parent JPanel
    for(JComponent component: new JComponent[]{rlabel0,rlabel1,sSlider,rlabel2,xSlider,
      rlabel3,ySlider}) r.add(component);

		add(r); // add the newly created JPanel to this class

  }//end contructor

  // initialize sliders
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
	}//end stateChanged

}// Panel
