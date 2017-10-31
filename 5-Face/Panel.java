/**
 * Panel.java
 * Handles GUI controls like sliders
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.event.*;

class Panel extends JPanel implements ActionListener{

	// buttons
	JButton left,right,up,down;
	JButton rotateLeft,rotateRight;
	JButton scaleUp,scaleDown;
	JButton createBtn;

	// state that will keep track of current state (which shape was the most recent)
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

		// initializing buttons
		createBtn = new JButton("Create");
		left = new JButton("Left");
		right = new JButton("Right");
		up = new JButton("Up");
		down = new JButton("Down");
		rotateLeft = new JButton("Rot Left");
		rotateRight = new JButton("Rot Right");
		scaleUp = new JButton("Scale Up");
		scaleDown = new JButton("Scale Down");

		// attaching ActionListener to all buttons
		JButton[] btns = {left,right,up,down,rotateLeft,rotateRight,scaleUp,scaleDown,createBtn};
		for(JButton btn: btns) btn.addActionListener(this);

		// create new panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(9,3,1,1));

		// create JComboBox for presenting a user with options
		// on what kind of Face Element will be created
		JComboBox chooseElement = new JComboBox(new String[] {"Face","Eye","Eye Pupil","Eyebrow","Ear","Nose","Mouth"});
		// add ActionListener to JComboBox
		chooseElement.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
					String chosenElement = (String)chooseElement.getSelectedItem();
					state.put("chosenElement",chosenElement);
					myCanvas.update(state);
		    }
		});

		// attaching buttons and labels to the newly created JPanel
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

	// send updates to Canvas
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource(); // get which button was clicked
		myCanvas.btnClicked(btn.getText()); // send to Canvas
	}// end actionPerformed

}// Panel
