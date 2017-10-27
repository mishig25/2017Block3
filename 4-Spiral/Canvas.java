
/**
 * Canvas.java
 *
 * Draws Three Rectangles to Form Pythagorean Triangle.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class Canvas extends JPanel{

  Graphics2D g2d;
  HashMap state;


  public Canvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(400,400));
		setBackground(Color.lightGray);
  }

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		g2d = (Graphics2D)g; //cast so we can use JAVA2D.

    int w = getWidth();
    int h = getHeight();

    // outer square
    if(state!=null){
      int n_col = (int)state.get("rowcolVal");
      int size = w/n_col;
      boolean reverse = true;
      for(int i=0; i<n_col; ++i){
        for(int j=0; j<n_col; ++j){
          int x = i*size;
          int y = j*size;
          float[] pts = {x,y,x+size,y,x+size,y+size,x,y+size};
          Square new_sqr = new Square(pts,g2d,Color.red);
          new_sqr.spiral(100,(float)state.get("lambda"),reverse,Color.yellow);
          reverse = !reverse;
        }
        if(n_col%2==0) reverse = !reverse;
      }
    }
	}

  public void update(HashMap _state){
    state = _state;
    repaint();
  }

}// Canvas
