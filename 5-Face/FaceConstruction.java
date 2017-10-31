/**
 * FaceConstruction.java.
 */

 import java.awt.*;
 import javax.swing.*;

public class FaceConstruction{

    // main method of FaceConstruction program

    public static void main(String[] args){

    // create window, canvas and GUI controls
    JFrame myFrame = new JFrame();
		Canvas myCanvas = new Canvas();
    Panel GUI = new Panel(myCanvas);

		myFrame.setTitle("Face Construction");
		myFrame.setSize(500,500);
    // attach canvas and GUI controls to the window
    myFrame.add(myCanvas, BorderLayout.CENTER);
    myFrame.add(GUI,BorderLayout.EAST);

		//Sets the window to close when upper right corner clicked.
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Must use getContentPane() with JFrame.
		myFrame.pack(); //resizes to preferred size for components.
		myFrame.setResizable(false);
		myFrame.setVisible(true);
  }// end main

} // FaceConstruction
