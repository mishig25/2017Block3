/**
 * Particle.java
 *
 * Draws Three Rectangles to Form Pythagorean Triangle.
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

  Ellipse2D.Double shape;
  Color color1,color2;
  double[] direction;
  double x=0,y=0;
  double size = 8.0;
  Random ran;
  double speed;

  public Particle(Random _ran, double _speed, Color _color1,Color _color2,String emtype){
    ran = _ran;
    if(emtype == "Plane") x = ranDouble(-200.0,200.0);
    shape = new Ellipse2D.Double(x,y,size,size);
    // generate vector
    double speed = _speed;
    double xD = ranDouble(-speed,speed);
    double yD = ranDouble(-speed,speed);
    direction = new double[]{xD,yD};
    color1 = _color1;
    color2 = _color2;
  }

  public void draw(Graphics2D g2d){
    Color c = getColor();
    g2d.setColor(c);
    // get color
    g2d.fill(shape);
  }
  private Color getColor(){
    // get colors
    double r1 = color1.getRed(), g1 = color1.getGreen(), b1 = color1.getBlue();
    double r2 = color2.getRed(), g2 = color2.getGreen(), b2 = color2.getBlue();
    // interpolate between colors
    double l = size/8.0;
    double ll = 1.0 - l;
    double r3 = l*r1 + ll*r2;
    double g3 = l*g1 + ll*g2;
    double b3 = l*b1 + ll*b2;
    return new Color((int)r3,(int)g3,(int)b3);
  }
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
  }
  private double ranDouble(double min,double max){
    double val = min + (max - min) * ran.nextDouble();
    return val;
  }
}
