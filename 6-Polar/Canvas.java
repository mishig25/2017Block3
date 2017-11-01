
/**
 * Canvas.java
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;


public class Canvas extends JPanel{

    HashMap state; // current state
    String[] functionNames; // list of functions to plot
    double[] theta; // radians from 0 to 2Pi

    // constructor
    public Canvas (){
  		//The following is another way to guarantee correct size.
  		setPreferredSize(new Dimension(300,300));
  		setBackground(Color.white);

      // init vars
      functionNames = getFunctionNames();
      state = new HashMap();
      state.put("func",functionNames[0]);
      state.put("scale",70);
      state.put("a",1);
      theta = new double[361];
      for(int degree=0; degree<=360; degree++)
        theta[degree] = Math.toRadians(degree);

    }// end constructor

    public void paintComponent(Graphics g){

  		super.paintComponent(g);  //without this no background color set.

  		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
  		g2d.translate(getWidth()/2,getHeight()/2);
  		g2d.setPaint(Color.red);

      // draw based on state
      if(state != null){
        ArrayList<double[]> outs = polarFunc((String)state.get("func"),theta,(int)state.get("a"));
        for(double[] out: outs){
          Path2D.Double path = null;
          for(int i=0; i<theta.length; i++){
            double r = out[i];
            r *= (int)state.get("scale");
            double angle = theta[i];
              double x = r*Math.cos(angle), y = r*Math.sin(angle);
              if(path == null){
                path = new Path2D.Double();
                path.moveTo(x,y);
              }else{
                path.lineTo(x,y);
              }
          }
          g2d.draw(path);
        }
      }

	 }

  // calculate output based on chose function
  public ArrayList<double[]> polarFunc(String type, double[] inp, int a){
    double[] out1 = new double[theta.length];
    double[] out2 = new double[theta.length];
    ArrayList<double[]> result = new ArrayList<>();
    result.add(out1);
    switch(type){
      case "r = cos(3*theta)":
        for(int i=0; i<inp.length; i++){
          out1[i] = Math.cos(inp[i]);
        }
        return result;
      case "r = a(theta)":
        for(int i=0; i<inp.length; i++){
          out1[i] = a*(inp[i]);
        }
        return result;
      case "r = a(1+cos(theta)) and r = a(1+2cos(theta))":
        for(int i=0; i<inp.length; i++){
          out1[i] = a*(1+Math.cos(inp[i]));
        }
        for(int i=0; i<inp.length; i++){
          out2[i] =a*(1+2*Math.cos(inp[i]));
        }
        result.add(out2);
        return result;
      case "r = 1+2cos(4*theta) and r = 1+2cos(3*theta)":
        for(int i=0; i<inp.length; i++){
          out1[i] = 1+2*Math.cos(4*inp[i]);
        }
        for(int i=0; i<inp.length; i++){
          out2[i] = 1+2*Math.cos(3*inp[i]);
        }
        result.add(out2);
        return result;
    }
    return result;
  }

  // to repaint
  public void update(){
    repaint();
  }

  // returns list of available functions
  public String[] getFunctionNames(){
    String func1 = "r = cos(3*theta)";
    String func2 = "r = a(theta)";
    String func3 = "r = a(1+cos(theta)) and r = a(1+2cos(theta))";
    String func4 = "r = 1+2cos(4*theta) and r = 1+2cos(3*theta)";
    return new String[]{func1,func2,func3,func4};
  }

}// SimpleCanvas
