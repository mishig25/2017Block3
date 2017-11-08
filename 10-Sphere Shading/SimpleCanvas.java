
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
    System.out.println("done");
	}

  public void calcPoints(){

    double r = 100; // radius
    double[] pLight = {0,0,250}; // light position
    double[] pCamera = {0,0,200}; // camera/eye position

    // calculate y points
    for(double y=-100; y<=100; y++){
      // calc x1, x2 points (x bounds)
      double x1 = (double)Math.sqrt(Math.pow(r,2)-Math.pow(y,2));
      double x2 = -x1;
      // got x
      for(double x=x2; x<=x1; x++){
        // calculate z
        double z = (double)Math.sqrt(Math.pow(r,2)-Math.pow(y,2)-Math.pow(x,2));
        // find and normalize vector
        double[] n = normlz(new double[]{x,y,z});
        // do light calculations

        // pointCamera - point   unit vector v
        double[] v = normlz(new double[]{pCamera[0]-x,pCamera[1]-y,pCamera[2]-z});
        // pointCamera - point   unit vector L
        double[] l = normlz(new double[]{pLight[0]-x,pLight[1]-y,pLight[2]-z});

        double ndotL = n[0]*l[0]+n[1]*l[1]+n[2]*l[2];
        // reflected array r
        double[] ray = new double[3];
        for(int i=0;i<3;i++) ray[i] = 2*ndotL*n[i]-l[i];
        double rayDotV = ray[0]*v[0]+ray[1]*v[1]+ray[2]*v[2];

        // calculate intensities

      }
    }

  }

  public double[] normlz(double[] v){
    double l = Math.sqrt(Math.pow(v[0],2)+Math.pow(v[1],2)+Math.pow(v[2],2));
    for(int i=0;i<v.length;i++) v[i] = v[i]/l;
    return v;
  }

}// SimpleCanvas
