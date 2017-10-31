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
	JButton left,right,up,down;
	JButton rotateLeft,rotateRight;
	JButton scaleUp,scaleDown;
	JButton createBtn;

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
		state.put("chosenElement","Face");

		// add new buttons here
		createBtn = new JButton("Create");

		left = new JButton("Left");
		right = new JButton("Right");
		up = new JButton("Up");
		down = new JButton("Down");

		rotateLeft = new JButton("Rot Left");
		rotateRight = new JButton("Rot Right");
		scaleUp = new JButton("Scale Up");
		scaleDown = new JButton("Scale Down");

		JButton[] btns = {left,right,up,down,rotateLeft,rotateRight,scaleUp,scaleDown,createBtn};
		for(JButton btn: btns) btn.addActionListener(this);

		// create JPanel and add all the components to it
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(9,3,1,1));

		JComboBox chooseElement = new JComboBox(new String[] {"Face","Eye","Eye Pupil","Eyebrow","Ear","Nose","Mouth"});
		chooseElement.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
					String chosenElement = (String)chooseElement.getSelectedItem();
					state.put("chosenElement",chosenElement);
					myCanvas.update(state);
		    }
		});

		panel.add(new JLabel("Choose:",SwingConstants.CENTER));
		panel.add(chooseElement);
		panel.add(createBtn);

		panel.add(new JPanel());
		panel.add(new JLabel("Translate",SwingConstants.CENTER));
		panel.add(new JPanel());

		panel.add(new JPanel());
		panel.add(up);
		panel.add(new JPanel());

		panel.add(left);
		panel.add(new JPanel());
		panel.add(right);

		panel.add(new JPanel());
		panel.add(down);
		panel.add(new JPanel());

		panel.add(new JPanel());
		panel.add(new JLabel("Rotate",SwingConstants.CENTER));
		panel.add(new JPanel());

		panel.add(rotateLeft);
		panel.add(new JPanel());
		panel.add(rotateRight);

		panel.add(new JPanel());
		panel.add(new JLabel("Scale",SwingConstants.CENTER));
		panel.add(new JPanel());

		panel.add(scaleDown);
		panel.add(new JPanel());
		panel.add(scaleUp);

		add(panel);

		myCanvas.update(state);
  }//end contructor

	// listen for changes in values of sliders
  public void stateChanged(ChangeEvent ev){
		System.out.println("here");
    // // get lambda value
		// float lambdaVal = (float)lambdaSlider.getValue();
		// lambdaVal /= 100;
		// // get number of row col value
		// int rowcolVal = rowcolSlider.getValue();
		// // update state
		// state.put("lambda",lambdaVal);
		// state.put("rowcolVal",rowcolVal);
		// // render updates
		// updateStateGUI();
	}//end stateChanged

	public void updateStateGUI(){
		// render changes in "state"
		// String mode = (String)state.get("mode");
		// char[] arr = mode.toCharArray();
		// if(arr[0] == 'S'){
		// 	rlabel3.setVisible(false);
		// 	rowcolSlider.setVisible(false);
		// 	state.put("rowcolVal",1);
		// 	rowcolSlider.setValue(1);
		// }else{
		// 	rlabel3.setVisible(true);
		// 	rowcolSlider.setVisible(true);
		// }
		// toggle.setText(mode);
		//
		// // call canvas to re-create squares based on
		// // changes in "state"
		//
	}//end updateStateGUI

	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		myCanvas.btnClicked(btn.getText());
		// System.out.println(btn.getText());
	}

}// Panel
