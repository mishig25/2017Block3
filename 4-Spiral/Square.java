/**
 * Square.java
 * Creates a spiraling square
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

class Square{

  // instance vars
  float[] pts; // coordinates for 4 vertices of a square
  Path2D.Double sqr; // square path
  Graphics2D g2d; // graphics content to draw to
  // alternating colors
  Color color1 = Color.yellow;
  Color color2 = Color.red;

  // constructor
  public Square(float[] _pts, Graphics2D _g2d, Color color){
    // set instance vars
    pts = _pts;
    g2d = _g2d;
    sqr = new Path2D.Double();
    // create square path based on "pts"
    int ind = 0;
    while(ind < 6){
      if(ind == 0) sqr.moveTo(pts[ind],pts[ind+=1]);
      else sqr.lineTo(pts[ind+=1],pts[ind+=1]);
    }
    sqr.closePath();
    // draw the square to the graphcis context
    g2d.setColor(color);
    g2d.fill(sqr);
  }// end constructor

  // create an inner square based on a given lambda value
  public Square createInnerSquare(float lambda, boolean reverse, Color color){
    float[] newPts = inner_sqr_pts(lambda,reverse); // calculate coordinates for the inner square
    Square new_sqr = new Square(newPts,g2d,color); // create the inner square
    return new_sqr;
  }// end createInnerSquare

  // create coordinates of an inner square based on a given lambda value
  float[] inner_sqr_pts(float lambda, boolean reverse){
    // initialize vars
    float[] pts_new = new float[8];
    int new_ind = -1;
    int current_ind = 0;
    int l = pts.length;
    if(reverse) lambda = 1-lambda;
    // create the points and add them to "pts_new" array
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
  }// end inner_sqr_pts

  // recursive function for creating spirals
  public void spiral(int count, float lambda, boolean reverse, Color color){
    // base case
    if(count == 0) return;

    // recursive case
    Square new_sqr2 = this.createInnerSquare(lambda, reverse, color); // create inner smaller square
    color = (color == color1) ? color2 : color1; // alternate the color
    count -= 1; // decrement the number of squares to be drawn
    new_sqr2.spiral(count,lambda,reverse,color); // recursive call
  }// end spiral

}// end Square
