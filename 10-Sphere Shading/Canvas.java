
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

  HashMap state;

  double[] pLight; // light position
  double[] pCamera; // camera/eye position

  public Canvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(300,300));
		setBackground(Color.lightGray);

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

    pLight[0] = (double)(int)state.get("cameraX");
    calcPoints(g2d);
	}

  public void calcPoints(Graphics2D g2d){

    double r = 100; // radius

    double max = -1, min = 100;
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
        // i = ambient + diffuse + specular [0,1]
        // constants
        double iA=.3, iD=.4, iS=.3;
        double kA=.7, kD=.8, kS=.5;

        double I = iA*kA + iD*kD*ndotL + iS*kS*Math.pow(rayDotV,2);
        int ii = map(I);
        // get max/min
        // if(I > max) max = I;
        // if(I < min) min = I;

        // paint the dot on canvas
        Rectangle2D.Double pixel = new Rectangle2D.Double(x,y,1,1);
        // System.out.println(normalizedI);
        g2d.setColor(new Color(ii,ii,ii));
        g2d.fill(pixel);
      }
    }
    // System.out.println(max+" "+min);
  }

  public double[] normlz(double[] v){
    double l = Math.sqrt(Math.pow(v[0],2)+Math.pow(v[1],2)+Math.pow(v[2],2));
    for(int i=0;i<v.length;i++) v[i] = v[i]/l;
    return v;
  }

  public int map(double value){
    double max = 0.68, min = 0.15;
    double newvalue = (200.0-50.0)/(max-min)*(value-min)+50.0;
    return (int)newvalue;
  }

  public void update(){
    repaint();
  }

}// Canvas
