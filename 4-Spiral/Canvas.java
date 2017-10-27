
/**
 * Canvas.java
 * Draws Graphic objects
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class Canvas extends JPanel{

  // create vars
  Graphics2D g2d; // current graphics2d context
  HashMap state; // current state

  public Canvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(400,400));
		setBackground(Color.lightGray);
  }// end constructor

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		g2d = (Graphics2D)g; //cast so we can use JAVA2D.

    // get height and width of the canvas
    int w = getWidth();
    int h = getHeight();

    // create spiraling square(s)
    // based on "state"
    if(state!=null){
      // init vars
      int n_col = (int)state.get("rowcolVal"); // get number of row col value from state
      int size = w/n_col; // determine side length of a square
      boolean reverse = true; // for alternating between clockwise and counterclockwise directions

      // create squares
      for(int i=0; i<n_col; ++i){
        for(int j=0; j<n_col; ++j){
          int x = i*size; // determine x (leftmost point)
          int y = j*size; // determine y (uppermost point)
          float[] pts = {x,y,x+size,y,x+size,y+size,x,y+size}; // 4 (x,y) points for 4 vertices in a square

          // create squares
          Square new_sqr = new Square(pts,g2d,Color.red); // initialize the outermost square
          new_sqr.spiral(100,(float)state.get("lambda"),reverse,Color.yellow); // telling to create inner spiraling squares
          reverse = !reverse; // alternate between clockwise and counterclockwise
        }
        if(n_col%2==0) reverse = !reverse; // alternate between clockwise and counterclockwise
      }
    }
	}// end paintComponent

  // this method is being called from Panel.java class
  // to render changes based on GUI controls
  public void update(HashMap _state){
    state = _state;
    repaint();
  }// end update

}// Canvas
