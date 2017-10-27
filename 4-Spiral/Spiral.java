
/**
 * Pythagor.java.
 */

 import java.awt.*;
 import javax.swing.*;

public class Spiral{

    //For this type of program, event handling determines the path of
    //execution.  This main method "looks" like it sets up the frame
    //and then stops.

    public static void main(String[] args){

		Canvas myCanvas = new Canvas();
		JFrame myFrame = new JFrame();
    Panel sliders = new Panel(myCanvas);
    Container cp = myFrame.getContentPane();

		myFrame.setTitle("Spiral");
		myFrame.setSize(500,500);
    cp.add(myCanvas, BorderLayout.CENTER);
    cp.add(sliders,BorderLayout.EAST);

		//Sets the window to close when upper right corner clicked.
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Must use getContentPane() with JFrame.
		myFrame.pack(); //resizes to preferred size for components.
		myFrame.setResizable(false);
		myFrame.setVisible(true);

  }
} // BasicDraw
