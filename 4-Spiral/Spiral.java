/**
 * Spiral.java.
 */

 import java.awt.*;
 import javax.swing.*;

public class Spiral{

    // main method of spiral program

    public static void main(String[] args){

    // create canvas and GUI controls
		Canvas myCanvas = new Canvas();
		JFrame myFrame = new JFrame();
    Panel sliders = new Panel(myCanvas);
    Container cp = myFrame.getContentPane();

		myFrame.setTitle("Spiral");
		myFrame.setSize(500,500);
    // attach canvas and GUI controls to the window
    cp.add(myCanvas, BorderLayout.CENTER);
    cp.add(sliders,BorderLayout.EAST);

		//Sets the window to close when upper right corner clicked.
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Must use getContentPane() with JFrame.
		myFrame.pack(); //resizes to preferred size for components.
		myFrame.setResizable(false);
		myFrame.setVisible(true);
  }// end main

} // Spiral
