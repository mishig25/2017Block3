/**
 * SphereShading.java.
 */

import javax.swing.*;
import java.awt.*;

public class SphereShading{

    // main method of SphereShading program

    public static void main(String[] args){

    // init windows
    JFrame myFrame = new JFrame();
    Canvas myCanvas = new Canvas();
    Panel sliders = new Panel(myCanvas);

		myFrame.setTitle("Sphere Shading");
		myFrame.setSize(500,500);

		//Sets the window to close when upper right corner clicked.
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Must use getContentPane() with JFrame.
    myFrame.add(sliders,BorderLayout.EAST);
		myFrame.add(myCanvas,BorderLayout.CENTER);
		myFrame.pack(); //resizes to preferred size for components.
		myFrame.setResizable(false);
		myFrame.setVisible(true);

  }
} // SphereShading
