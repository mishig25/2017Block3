
/**
 * BasicDraw.java
 *
 *
 * Template for beginning graphics programs.
 *
 */

import javax.swing.*;
import javax.swing.event.*;

public class FreeHandDraw
{

    //For this type of program, event handling determines the path of
    //execution.  This main method "looks" like it sets up the frame
    //and then stops.

    public static void main(String[] args){
		Canvas myCanvas = new Canvas();
		JFrame myFrame = new JFrame();
		myFrame.setTitle("Free Hand Draw");
		myFrame.setSize(600,400);

		//Sets the window to close when upper right corner clicked.
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Must use getContentPane() with JFrame.
		myFrame.add(myCanvas);
		myFrame.pack(); //resizes to preferred size for components.
		myFrame.setResizable(false);
		myFrame.setVisible(true);

    }
} // BasicDraw
