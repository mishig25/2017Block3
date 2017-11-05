
/**
 * Pythagor.java.
 */

import javax.swing.*;

public class Pythagor{

    //For this type of program, event handling determines the path of
    //execution.  This main method "looks" like it sets up the frame
    //and then stops.

    public static void main(String[] args){
		SimpleCanvas myCanvas = new SimpleCanvas();
		JFrame myFrame = new JFrame();
		myFrame.setTitle("3D Cube");
		myFrame.setSize(500,500);

		//Sets the window to close when upper right corner clicked.
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Must use getContentPane() with JFrame.
		myFrame.add(myCanvas);
		myFrame.pack(); //resizes to preferred size for components.
		myFrame.setResizable(true);
		myFrame.setVisible(true);

  }
} // BasicDraw
