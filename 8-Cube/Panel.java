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
    int center = SwingConstants.CENTER;
    rlabel0 = new JLabel("Choose Render Mode:",center);
		rlabel1 = new JLabel("Choose Axis:",center);
		rlabel2 = new JLabel("Choose degree to rotate:",center);

    JPanel panelArbAxis = new JPanel();
    panelArbAxis.setVisible(false);

    // options for choosing different functions
    String[] axes = {"Z-axis","X-axis","Y-axis","Arbitrary-axis"};
		JComboBox functionsChooser = new JComboBox(axes);
			// add ActionListener to JComboBox
			functionsChooser.addActionListener (new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						String chosenAxis = (String)functionsChooser.getSelectedItem();
						myCanvas.state.put("axis",chosenAxis);
						degreeSlider.setValue(0);
						if(chosenAxis != axes[axes.length-1]){
              myCanvas.update();
              panelArbAxis.setVisible(false);
            }else{
              panelArbAxis.setVisible(true);
            }
					}
			});

    // render mode
    String[] renderModes = {"Wireframe","Solid"};
    JComboBox renderChooser = new JComboBox(renderModes);
    // add ActionListener to JComboBox
    renderChooser.addActionListener (new ActionListener () {
          public void actionPerformed(ActionEvent e) {
            String chosenAxis = (String)renderChooser.getSelectedItem();
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



    JTextField xField = new JTextField("1", 3);
    JTextField yField = new JTextField("1", 3);
    JTextField zField = new JTextField("0", 3);

		panelArbAxis.setLayout(new GridLayout(1,6,1,1));
    panelArbAxis.add(new JLabel("X:",SwingConstants.CENTER));
    panelArbAxis.add(xField);
    panelArbAxis.add(new JLabel("X:",SwingConstants.CENTER));
    panelArbAxis.add(yField);
    panelArbAxis.add(new JLabel("X:",SwingConstants.CENTER));
    panelArbAxis.add(zField);

    JButton chooseArbAxis = new JButton("ROTATE AROUND THIS AXIS");
    chooseArbAxis.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        myCanvas.state.put("arbX",Integer.valueOf(xField.getText()));
        myCanvas.state.put("arbY",Integer.valueOf(yField.getText()));
        myCanvas.state.put("arbZ",Integer.valueOf(zField.getText()));
        System.out.println(xField.getText());
        System.out.println(yField.getText());
        System.out.println(myCanvas.state.get("arbZ"));
      }
    });

    r.add(panelArbAxis);
    r.add(chooseArbAxis);

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
