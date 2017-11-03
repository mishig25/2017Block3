
/**
 * Polar.java
 *
 */

import javax.swing.*;
import java.awt.*;

public class Polar
{

    // main function of Polar program
    public static void main(String[] args){

		JFrame myFrame = new JFrame();
    Canvas myCanvas = new Canvas();
    Panel sliders = new Panel(myCanvas);

		myFrame.setTitle("Polar Coordinates");
		myFrame.setSize(300,300);

		//Sets the window to close when upper right corner clicked.
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    myFrame.add(sliders,BorderLayout.EAST);
		myFrame.add(myCanvas,BorderLayout.CENTER);
		myFrame.pack(); //resizes to preferred size for components.
		myFrame.setResizable(false);
		myFrame.setVisible(true);

  }// end main

} // Polar
