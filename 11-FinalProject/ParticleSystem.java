
/**
 * ParticleSystem.java.
 */

import javax.swing.*;
import java.awt.*;

public class ParticleSystem{

    //main method of ParticleSystem program

    public static void main(String[] args){
    // init different window components
    JFrame myFrame = new JFrame();
		Canvas myCanvas = new Canvas();
    GUIControls guiControls = new GUIControls(myCanvas);

		myFrame.setTitle("Particle System");
		myFrame.setSize(600,600);

		//Sets the window to close when upper right corner clicked.
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // add GUIControls and Canvas to the frame
    myFrame.add(guiControls,BorderLayout.EAST);
		myFrame.add(myCanvas,BorderLayout.CENTER);
		myFrame.pack(); //resizes to preferred size for components.
		myFrame.setResizable(false);
		myFrame.setVisible(true);

  }// end main

} // ParticleSystem
