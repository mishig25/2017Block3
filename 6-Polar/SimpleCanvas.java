
/**
 * SimpleCanvas.java
 *
 * Part of the basic graphics Template.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;


public class SimpleCanvas extends JPanel{

    Color myColor;
    HashMap state;
    String[] functionNames;
    double[] theta;

    public SimpleCanvas (){
  		//The following is another way to guarantee correct size.
  		setPreferredSize(new Dimension(300,300));
  		setBackground(Color.white);
      myColor = Color.red;

      functionNames = getFunctionNames();
      state = new HashMap();
      state.put("func",functionNames[0]);
      state.put("scale",1);
      state.put("a",1);

      theta = new double[361];
      for(int thetaDegree=0; thetaDegree<=360; thetaDegree++){
        double rad = Math.toRadians(thetaDegree);
        theta[thetaDegree] = rad;
      }

    }

    public void paintComponent(Graphics g){

  		super.paintComponent(g);  //without this no background color set.

  		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
  		g2d.translate(getWidth()/2,getHeight()/2);

  		g2d.setPaint(myColor);

      // draw based on state
      if(state != null){
        // theta 0 - 360

        double[] theta = new double[361];
        for(int thetaDegree=0; thetaDegree<=360; thetaDegree++){
          double rad = Math.toRadians(thetaDegree);
          theta[thetaDegree] = rad;
        }
        //get output
        ArrayList<double[]> outs = polarFunc(functionNames,(String)state.get("func"),theta,(int)state.get("a"));
        for(double[] out: outs){
          Path2D.Double path = null;
          for(int i=0; i<=360; i++){
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

  public ArrayList<double[]> polarFunc(String[]functionNames,String type, double[] inp, int a){
    double[] out1 = new double[361];
    double[] out2 = new double[361];
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
          out1[i] = a*(1+Math.cos(i));
        }
        for(int i=0; i<inp.length; i++){
          out2[i] =a*(1+2*Math.cos(i));
        }
        result.add(out2);
        return result;
      case "r = 1+2cos(4*theta) and r = 1+2cos(3*theta)":
        for(int i=0; i<inp.length; i++){
          out1[i] = Math.cos(inp[i]);
        }
        return result;
    }
    return result;
  }

  //  cos(3*theta)
  public double func1(double theta){
     return Math.cos(theta);
  }

  // a*theta
  public double func2(double theta){
    double a = 1.0;
    return a*theta;
  }

  public void update(){
    repaint();
  }

  //
  public double func3(double theta){
    double a = 1.0;
    return a*(1+Math.cos(theta));
  }

  //
  public double func32(double theta){
    double a = 1.0;
    return a*(1+2*Math.cos(theta));
  }

  // func 4

  //
  public double func5(double theta){
    return 1+2*Math.cos(4*theta);
  }

  public double func52(double theta){
    return 1+2*Math.cos(3*theta);
  }

  public String[] getFunctionNames(){
    String func1 = "r = cos(3*theta)";
    String func2 = "r = a(theta)";
    String func3 = "r = a(1+cos(theta)) and r = a(1+2cos(theta))";
    String func4 = "r = cos(3*theta)"; // TODO
    String func5 = "r = 1+2cos(4*theta) and r = 1+2cos(3*theta)";
    return new String[]{func1,func2,func3,func4,func5};
  }

}// SimpleCanvas
