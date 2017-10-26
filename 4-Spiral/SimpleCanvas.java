
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

class Square{

  float[] pts;
  Path2D.Double sqr;
  Graphics2D g2d;

  public Square(float[] _pts, Graphics2D _g2d){
    pts = _pts;
    g2d = _g2d;
    sqr = new Path2D.Double();
    int ind = 0;
    while(ind < 6){
      if(ind == 0) sqr.moveTo(pts[ind],pts[ind+=1]);
      else sqr.lineTo(pts[ind+=1],pts[ind+=1]);
    }
    sqr.closePath();
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
  public Square smaller_square(float lambda, boolean reverse){
    float[] newPts = new_pts(lambda,reverse);
    Square new_sqr = new Square(newPts,g2d);
    return new_sqr;
  }
  public void spiral(int count, float lambda, boolean reverse, Color color1){
    if(count == 0) return;
    Square new_sqr2 = this.smaller_square((float)0.1, reverse);
    new_sqr2.draw(color1);
    color1 = (color1 == Color.yellow) ? Color.red : Color.yellow;
    count -= 1;
    new_sqr2.spiral(count,lambda,reverse,color1);
  }
}

public class SimpleCanvas extends JPanel{

  // shape variables
  Graphics2D g2d;
  Rectangle2D.Double myRect;
  Path2D.Double myTriganle;
  Path2D.Double myTriganle2;


  public SimpleCanvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(300,300));
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
    float[] pts = {0,0,200,0,200,200,0,200};
    Square new_sqr = new Square(pts,g2d);
    new_sqr.draw(Color.red);
    new_sqr.spiral(10,(float)0.4,false,Color.yellow);
	}

  public void paintShapeWithGradient(Graphics2D g2d, Shape shp, Color color1, Color color2){
    // get bounding box values
    Rectangle2D bbox = shp.getBounds2D();
    float x = (float)bbox.getX();
    float y = (float)bbox.getY();
    float w = (float)bbox.getWidth();
    float h = (float)bbox.getHeight();
    // create gradient and paint the shape
    GradientPaint rectGRad = new GradientPaint(0,y,color1,0,y+h,color2);
    g2d.setPaint(rectGRad);
    g2d.fill(shp);
  }

}// SimpleCanvas

// System.out.println(x1+" "+y1+" "+x2+" "+y2);
