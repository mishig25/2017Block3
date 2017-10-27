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

  float[] inner_sqr_pts(float lambda, boolean reverse){
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
  public Square createInnerSquare(float lambda, boolean reverse, Color color){
    float[] newPts = inner_sqr_pts(lambda,reverse);
    Square new_sqr = new Square(newPts,g2d,color);
    return new_sqr;
  }
  public void spiral(int count, float lambda, boolean reverse, Color color){
    if(count == 0) return;
    Square new_sqr2 = this.createInnerSquare(lambda, reverse, color);
    color = (color == color1) ? color2 : color1;
    count -= 1;
    new_sqr2.spiral(count,lambda,reverse,color);
  }
}
