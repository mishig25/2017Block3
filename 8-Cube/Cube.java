
/**
 * Cube.java.
 */

import javax.swing.*;
import java.awt.*;

public class Cube{

    //For this type of program, event handling determines the path of
    //execution.  This main method "looks" like it sets up the frame
    //and then stops.

    public static void main(String[] args){

		Canvas myCanvas = new Canvas();
		JFrame myFrame = new JFrame();
    Panel sliders = new Panel(myCanvas);

		myFrame.setTitle("3D Cube");
		myFrame.setSize(500,500);

		//Sets the window to close when upper right corner clicked.
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Must use getContentPane() with JFrame.
    myFrame.add(sliders,BorderLayout.EAST);
		myFrame.add(myCanvas,BorderLayout.CENTER);
		myFrame.pack(); //resizes to preferred size for components.
		myFrame.setResizable(true);
		myFrame.setVisible(true);

  }
} // BasicDraw
