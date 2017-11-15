/**
 * Particle.java
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Particle{
  // instance vars
  Ellipse2D.Double shape; // shape for drawing
  Color color1,color2; // colors
  double[] direction; // direction vector
  double x,y; // position
  double size = 8.0; // size of shape
  Random ran; // random number generator
  double speed; // movement speed

  public Particle(double _x, double _y, Random _ran, double _speed, Color _color1,Color _color2,String emtype){
    // init instance vars
    x = _x;
    y = _y;
    ran = _ran;
    if(emtype == "Plane") x = ranDouble(x-200.0,x+200.0);
    shape = new Ellipse2D.Double(x,y,size,size);
    color1 = _color1;
    color2 = _color2;
    double speed = _speed;
    // generate direction vector
    double xD = ranDouble(-speed,speed);
    double yD = ranDouble(-speed,speed);
    direction = new double[]{xD,yD};
  }// end constructor

  public void draw(Graphics2D g2d){
    // get color
    Color c = getColor();
    g2d.setColor(c);
    g2d.fill(shape); // draw shape
  }// end draw

  // for updateing instance vars based on emitter settings
  public void update(double xforce, double yforce){
    double r1 = ranDouble(-.2,.2);
    double r2 = ranDouble(-.2,.2);
    direction[0] += r1;
    direction[1] += r2;
    direction[0] += xforce;
    direction[1] += yforce;
    x += direction[0];
    y += direction[1];
    size -= .2;
    shape.setFrame(x,y,size,size);
  }// end update

  // generate random double in range [min,max]
  private double ranDouble(double min,double max){
    double val = min + (max - min) * ran.nextDouble();
    return val;
  }// end ranDouble

  // get color value based on size and color1, color2
  private Color getColor(){
    // get r,g,b values of color1,color2
    double r1 = color1.getRed(), g1 = color1.getGreen(), b1 = color1.getBlue();
    double r2 = color2.getRed(), g2 = color2.getGreen(), b2 = color2.getBlue();
    // interpolate between colors
    double l = size/8.0;
    double ll = 1.0 - l;
    double r3 = l*r1 + ll*r2;
    double g3 = l*g1 + ll*g2;
    double b3 = l*b1 + ll*b2;
    // return new color
    return new Color((int)r3,(int)g3,(int)b3);
  }// end getColor

}// end Particle
