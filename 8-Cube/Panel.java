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

	// sliders and labels
	JSlider degreeSlider;
	JLabel rlabel0,rlabel1,rlabel2;

	// current instance of canvas so that
	// we can tell canvas to update on user input
	Canvas myCanvas;

 	public Panel(Canvas _myCanvas){

		myCanvas = _myCanvas;
		setLayout(new GridLayout(1,3,30,10));

		// initialize slider for changing scale value
    degreeSlider = new JSlider(JSlider.HORIZONTAL,-180,180,0);
		degreeSlider.setMajorTickSpacing(60);
		degreeSlider.setMinorTickSpacing(5);

		// initialize labels
    rlabel0 = new JLabel("Choose Render Mode:");
		rlabel1 = new JLabel("Choose Axis:");
		rlabel2 = new JLabel("Choose degree to rotate:");

    // options for choosing different functions
    String[] axes = {"Z-axis","X-axis","Y-axis"};
		JComboBox functionsChooser = new JComboBox(axes);
			// add ActionListener to JComboBox
			functionsChooser.addActionListener (new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						String chosenAxis = (String)functionsChooser.getSelectedItem();
						myCanvas.state.put("axis",chosenAxis);
						degreeSlider.setValue(0);
						myCanvas.update();
					}
			});

    // render mode
    String[] renderModes = {"Wireframe","Solid"};
    JComboBox renderChooser = new JComboBox(renderModes);
    // add ActionListener to JComboBox
    renderChooser.addActionListener (new ActionListener () {
          public void actionPerformed(ActionEvent e) {
            String chosenAxis = (String)functionsChooser.getSelectedItem();
            myCanvas.state.put("renderMode",chosenAxis);
            myCanvas.update();
          }
      });

		// initialize slider for constant a
		for (JSlider slider: new JSlider[] {degreeSlider}){
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			slider.addChangeListener(this);
		}

		// create JPanel and add all the components to it
		JPanel r = new JPanel();
		r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));
		for(JComponent component: new JComponent[]{rlabel0,renderChooser,rlabel1,functionsChooser,rlabel2,
			degreeSlider}) r.add(component);

		add(r); // add the newly created JPanel to this class
		// updateStateGUI(); // render updates

  }//end contructor

	// listen for changes in values of sliders
  public void stateChanged(ChangeEvent ev){
    // get scale value
		int degreeVal = degreeSlider.getValue();
		// update state
		myCanvas.state.put("degree",degreeVal);
		// render updates
		myCanvas.update();
	}//end stateChanged

	// ActionListener method
	public void actionPerformed(ActionEvent e) {
	}// end actionPerformed

}// Panel
