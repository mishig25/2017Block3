
/**
 * SimpleCanvas.java
 *
 * Draws Three Rectangles to Form Pythagorean Triangle.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class SimpleCanvas extends JPanel{

  public SimpleCanvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(300,300));
		setBackground(Color.lightGray);


  }

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    // transformation on the entire set
		g2d.translate(getWidth()/2,getHeight()/2);
    calcPoints();
	}

  public void calcPoints(){

    int r = 100; // radius
    int[] pLight = {0,0,250}; // light position
    int[] pCamera = {0,0,200}; // camera/eye position

    // calculate y points
    for(int y=-100; y<=100; y++){
      // calc x1, x2 points (x bounds)
      int x1 = (int)Math.sqrt(Math.pow(r,2)-Math.pow(y,2));
      int x2 = -x1;
      // got x
      for(int x=x2; x<=x1; x++){
        // calculate z
        int z = (int)Math.sqrt(Math.pow(r,2)-Math.pow(y,2)-Math.pow(x,2));
        // find and normalize vector
        double l = Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
        double[] n = {x,y,z};
        for(double val: n) val = val/l;
        //
      }
    }

  }

}// SimpleCanvas
