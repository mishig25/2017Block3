
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

class Square{

  float[] pts;
  Path2D.Double sqr;
  Graphics2D g2d;
  Color color1 = Color.yellow;
  Color color2 = Color.red;

  public Square(float[] _pts, Graphics2D _g2d, Color color){
    pts = _pts;
    g2d = _g2d;
    sqr = new Path2D.Double();
    int ind = 0;
    while(ind < 6){
      if(ind == 0) sqr.moveTo(pts[ind],pts[ind+=1]);
      else sqr.lineTo(pts[ind+=1],pts[ind+=1]);
    }
    sqr.closePath();
    g2d.setColor(color);
    g2d.fill(sqr);
  }
  public void draw(Color color){
    g2d.setColor(color);
    g2d.fill(sqr);
  }
  float[] new_pts(float lambda, boolean reverse){
    float[] pts_new = new float[8];
    int new_ind = -1;
    int current_ind = 0;
    int l = pts.length;
    if(reverse) lambda = 1-lambda;
    while(current_ind < 8){
      float x1 = pts[(current_ind)];
      float y1 = pts[(current_ind+1)%l];
      float x2 = pts[(current_ind+2)%l];
      float y2 = pts[(current_ind+3)%l];
      current_ind += 2;
      // new values
      float new_x = (float)(lambda*x2) + (float)((1.0-lambda)*x1);
      float new_y = (float)(lambda*y2)+ (float)((1.0-lambda)*y1);
      pts_new[new_ind+=1] = new_x;
      pts_new[new_ind+=1] = new_y;
    }
    return pts_new;
  }
  public Square smaller_square(float lambda, boolean reverse, Color color){
    float[] newPts = new_pts(lambda,reverse);
    Square new_sqr = new Square(newPts,g2d,color);
    return new_sqr;
  }
  public void spiral(int count, float lambda, boolean reverse, Color color){
    if(count == 0) return;
    Square new_sqr2 = this.smaller_square(lambda, reverse, color);
    color = (color == color1) ? color2 : color1;
    count -= 1;
    new_sqr2.spiral(count,lambda,reverse,color);
  }
}

public class Canvas extends JPanel{

  // shape variables
  Graphics2D g2d;
  Rectangle2D.Double myRect;
  Path2D.Double myTriganle;
  Path2D.Double myTriganle2;
  HashMap state;


  public Canvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(400,400));
		setBackground(Color.lightGray);

    // initialize shapes
    myRect = new Rectangle2D.Double();
    myTriganle = new Path2D.Double();
    myTriganle2 = new Path2D.Double();
  }

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		g2d = (Graphics2D)g; //cast so we can use JAVA2D.

    int w = getWidth();
    int h = getHeight();

    // outer square
    float[] pts = {0,0,400,0,400,400,0,400};
    Square new_sqr = new Square(pts,g2d,Color.red);
    if(state!=null)new_sqr.spiral(100,(float)state.get("lambda"),false,Color.yellow);
    
	}

  public void update(HashMap _state){
    state = _state;
    repaint();
  }

}// Canvas
