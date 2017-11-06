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

  // instance vars
	JSlider degreeSlider; // slider for choosing degree
	JLabel rlabel0,rlabel1,rlabel2; // labels
  JTextField xField, yField, zField; // textFields for entering arbitrary axes

	// current instance of canvas so that
	// we can tell canvas to update on user input
	Canvas myCanvas;

 	public Panel(Canvas _myCanvas){

		myCanvas = _myCanvas;
		setLayout(new GridLayout(1,3,30,10));

		// initialize slider for changing scale value
    degreeSlider = new JSlider(JSlider.HORIZONTAL,-180,180,0);
    initSlider(degreeSlider);

		// initialize labels
    rlabel0 = new JLabel("Choose Render Mode:");
		rlabel1 = new JLabel("Choose Axis:");
    rlabel2 = new JLabel("Choose degree to rotate:");

    // init components related to arbitrary axis
    JPanel panelArbAxis = new JPanel();
    initPanelArbAxis(panelArbAxis);
    JLabel arbLabel = new JLabel("CLICK on the button to set axis");
    JButton chooseArbAxis = new JButton("ROTATE AROUND THIS AXIS");
    initArbButton(chooseArbAxis);
    JComponent[] arbComponents = {panelArbAxis,chooseArbAxis,arbLabel};
    for(JComponent c: arbComponents) c.setVisible(false);

    // options for choosing different functions
    String[] axes = {"Z-axis","X-axis","Y-axis","Arbitrary-axis"};
		JComboBox functionsChooser = new JComboBox(axes);
			// add ActionListener to JComboBox
			functionsChooser.addActionListener (new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						String chosenAxis = (String)functionsChooser.getSelectedItem();
						myCanvas.state.put("axis",chosenAxis); // update state
						degreeSlider.setValue(0);
            // update arb-axis components visibilities
						if(chosenAxis != axes[axes.length-1]){
              myCanvas.update();
              for(JComponent c: arbComponents) c.setVisible(false);
            }else{
              for(JComponent c: arbComponents) c.setVisible(true);
            }
					}
			});

    // JComboBox for choose between Wireframe and Solid
    String[] renderModes = {"Wireframe","Solid"};
    JComboBox renderChooser = new JComboBox(renderModes);
    // add ActionListener to JComboBox
    renderChooser.addActionListener (new ActionListener () {
          public void actionPerformed(ActionEvent e) {
            String chosenAxis = (String)renderChooser.getSelectedItem();
            // update state and re-render
            myCanvas.state.put("renderMode",chosenAxis);
            myCanvas.update();
          }
      });

		// create JPanel and add all the components to it
		JPanel r = new JPanel();
		r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));

    // add components to the parent JPanel
    for(JComponent component: new JComponent[]{rlabel0,renderChooser,rlabel1,functionsChooser,rlabel2,
			degreeSlider,panelArbAxis,panelArbAxis,arbLabel,chooseArbAxis}) r.add(component);

		add(r); // add the newly created JPanel to this class

  }//end contructor

  // initialize panel for choosing arb axis
  public void initPanelArbAxis(JPanel panel){
    // init text fields
    xField = new JTextField("1", 3);
    yField = new JTextField("1", 3);
    zField = new JTextField("0", 3);
    // add to JPanel
		panel.setLayout(new GridLayout(1,6,1,1));
    panel.add(new JLabel("X:",SwingConstants.CENTER));
    panel.add(xField);
    panel.add(new JLabel("X:",SwingConstants.CENTER));
    panel.add(yField);
    panel.add(new JLabel("X:",SwingConstants.CENTER));
    panel.add(zField);
  }// end initPanelArbAxis

  // initialize degree slider
  public void initSlider(JSlider slider){
    slider.setMajorTickSpacing(60);
    slider.setMinorTickSpacing(5);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.addChangeListener(this);
  }// end initSlider

	// listen for changes in values of degree slider
  public void stateChanged(ChangeEvent ev){
    // get scale value
		int degreeVal = degreeSlider.getValue();
		// update state
		myCanvas.state.put("degree",degreeVal);
		// render updates
		myCanvas.update();
	}//end stateChanged

  public void initArbButton(JButton chooseArbAxis){
    chooseArbAxis.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        // get values and update state
        myCanvas.state.put("arbX",Integer.valueOf(xField.getText()));
        myCanvas.state.put("arbY",Integer.valueOf(yField.getText()));
        myCanvas.state.put("arbZ",Integer.valueOf(zField.getText()));
        myCanvas.update();
      }
    });
  }

	// ActionListener method
	public void actionPerformed(ActionEvent e) {
	}// end actionPerformed

}// Panel
