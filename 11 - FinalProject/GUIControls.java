/**
 * Panel.java
 * Handles GUI controls like sliders
 */

 import java.awt.*;
 import javax.swing.*;
 import javax.swing.event.*;
 import java.util.*;
 import java.awt.event.*;

class GUIControls extends JPanel implements ChangeListener{

  // instance vars
	JSlider sSlider,ySlider,xSlider; // slider for moving light source
	JLabel rlabel0,rlabel1,rlabel2,rlabel3; // label
  JButton color1,color2;

	// current instance of canvas so that
	// we can tell canvas to update on user input
	Canvas myCanvas;

 	public GUIControls(Canvas _myCanvas){

		myCanvas = _myCanvas;
		setLayout(new GridLayout(1,3,30,10));

		// initialize slider for changing X value
    sSlider = new JSlider(JSlider.HORIZONTAL, 0,50,5);
    xSlider = new JSlider(JSlider.HORIZONTAL,-50,50,0);
    ySlider = new JSlider(JSlider.HORIZONTAL,-50,50,0);
    JSlider[] sliders = {sSlider,xSlider,ySlider};
    for(JSlider s: sliders) initSlider(s);

		// initialize labels
    rlabel0 = new JLabel("PARTICLE SYSTEM");
    rlabel1 = new JLabel("Speed:");
    rlabel2 = new JLabel("Force X:");
    rlabel3 = new JLabel("Force Y:");

    // init buttons
    color1 = new JButton("Choose Color1");
    color2 = new JButton("Choose Color2");
    initColorButton(color1,"color1");
    initColorButton(color2,"color2");

		// create JPanel and add all the components to it
		JPanel r = new JPanel();
		r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));

    // add components to the parent JPanel
    for(JComponent component: new JComponent[]{rlabel0,rlabel1,sSlider,rlabel2,xSlider,
      rlabel3,ySlider,color1,color2}) r.add(component);

		add(r); // add the newly created JPanel to this class

  }//end contructor

  // initialize sliders
  public void initSlider(JSlider slider){
    slider.setMajorTickSpacing(25);
    slider.setMinorTickSpacing(5);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.addChangeListener(this);
  }// end initSlider

	// listen for changes in value of xSlider
  public void stateChanged(ChangeEvent ev){
    // get slider values
		int xVal = xSlider.getValue();
    int yVal = ySlider.getValue();
    int sVal = sSlider.getValue();
		// update state
		myCanvas.state.put("speed",(double)sVal);
    myCanvas.state.put("xforce",(double)xVal);
    myCanvas.state.put("yforce",(double)yVal);
	}//end stateChanged

  public void initColorButton(JButton button,String label){
    button.addActionListener(new ButtonListener(button,myCanvas.state,label));
    button.setBackground(Color.white);
    button.setOpaque(true);
    button.setBorderPainted(false);
  }

  private class ButtonListener implements ActionListener {
    JButton button;
    HashMap state;
    String label;
    public ButtonListener(JButton _button,HashMap _state,String _label){
      button = _button;
      state = _state;
      label = _label;
    }
    public void actionPerformed(ActionEvent e) {
      Color color = JColorChooser.showDialog(null, "Choose a Color", button.getBackground());
      if (color != null){
        button.setBackground(color);
        state.put(label,color);
      }
    }
}

}// Panel
