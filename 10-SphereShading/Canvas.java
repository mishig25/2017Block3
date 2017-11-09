
/**
 * Canvas.java
 *
 * Shades a 3D sphere
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class Canvas extends JPanel{

  // instance vars
  HashMap state; // keep track of light position
  double[] pLight; // light position
  double[] pCamera; // camera/eye position

  public Canvas (){
		setPreferredSize(new Dimension(300,300));
		setBackground(Color.lightGray);
    // init vars
    pLight = new double[]{0,0,250};
    pCamera = new double[]{0,0,200};
    state = new HashMap();
    state.put("cameraX",0);
  }

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    // transformation on the entire set
		g2d.translate(getWidth()/2,getHeight()/2);

    // get light X position from GUI slider
    pLight[0] = (double)(int)state.get("cameraX");
    drawSphere(g2d);
	}

  public void drawSphere(Graphics2D g2d){

    double r = 100; // radius

    // calculate y points
    for(double y=-100; y<=100; y++){
      // calc x1, x2 points (x bounds)
      double x1 = (double)Math.sqrt(Math.pow(r,2)-Math.pow(y,2));
      double x2 = -x1;
      // calculate x points
      for(double x=x2; x<=x1; x++){
        // calculate z
        double z = (double)Math.sqrt(Math.pow(r,2)-Math.pow(y,2)-Math.pow(x,2));
        // find and normalize vector
        double[] n = normalize(new double[]{x,y,z});
        // pointCamera - point   unit vector v
        double[] v = normalize(new double[]{pCamera[0]-x,pCamera[1]-y,pCamera[2]-z});
        // pointCamera - point   unit vector L
        double[] l = normalize(new double[]{pLight[0]-x,pLight[1]-y,pLight[2]-z});

        double ndotL = n[0]*l[0]+n[1]*l[1]+n[2]*l[2];
        // reflected array r
        double[] ray = new double[3];
        for(int i=0;i<3;i++) ray[i] = 2*ndotL*n[i]-l[i];
        double rayDotV = ray[0]*v[0]+ray[1]*v[1]+ray[2]*v[2];

        // calculate intensities
        // i = ambient + diffuse + specular [0,1]
        // constants
        double iA=.3, iD=.4, iS=.3;
        double kA=.7, kD=.8, kS=.5;

        double I = iA*kA + iD*kD*ndotL + iS*kS*Math.pow(rayDotV,2);
        int ii = map(I);

        // paint the dot on canvas
        Rectangle2D.Double pixel = new Rectangle2D.Double(x,y,1,1);
        g2d.setColor(new Color(ii,ii,ii));
        g2d.fill(pixel);
      }
    }
  }

  // given a vector, it return normalized version of that vecotr
  public double[] normalize(double[] v){
    double l = Math.sqrt(Math.pow(v[0],2)+Math.pow(v[1],2)+Math.pow(v[2],2));
    for(int i=0;i<v.length;i++) v[i] = v[i]/l;
    return v;
  }// end normalize

  // map a value from range [0.15,0.68] tp [50,200]
  public int map(double value){
    double max = 0.68, min = 0.15;
    double newMax = 200.0, newMin = 50.0;
    double newvalue = (newMax-newMin)/(max-min)*(value-min)+newMin;
    return (int)newvalue;
  }// end map

  // let other classes call repaint
  public void update(){
    repaint();
  }// end update

}// Canvas
