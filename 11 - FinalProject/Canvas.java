
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
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

class Particle{

  Ellipse2D.Double shape;
  Color color1,color2;
  double[] direction;
  double x=0,y=0;
  double size = 8.0;
  Random ran;
  double speed;

  public Particle(Random _ran, double _speed, Color _color1,Color _color2){
    ran = _ran;
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
    double l = size/13.0;
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

class Emitter{

  ArrayList<Particle> particles;
  Random ran;
  HashMap state;

  public Emitter(HashMap _state){
    ran = new Random();
    particles = new ArrayList();
    state = _state;
  }
  public void emit(int n){
    double speed = (double)state.get("speed");
    Color color1 = (Color)state.get("color1");
    Color color2 = (Color)state.get("color2");
    for(int i=0; i<n; i++){
      Particle particle = new Particle(ran,speed,color1,color2);
      particles.add(particle);
    }
  }
  public void update(Graphics2D g2d){
    double xforce = (double)state.get("xforce");
    double yforce = (double)state.get("yforce");
    for(Particle part: particles){
      part.update(xforce, yforce);
      part.draw(g2d);
    }
  }
  public void removeSmallParticles(){
    ArrayList<Particle> _particles = new ArrayList();
    for(Particle part: particles){
      if(part.size > 1.0) _particles.add(part);
      // TODO if they exited screen boundry
    }
    particles = _particles;
  }
}

public class Canvas extends JPanel{

  Emitter em;
  HashMap state;

  public Canvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.black);

    state = new HashMap();
    initState();

    em = new Emitter(state);

    Timer timer = new Timer(1000/24, new ActionListener(){
        public void actionPerformed(ActionEvent evt){ repaint(); }
    });
    timer.start();
  }

  public void initState(){
    state.put("speed",5.0);
    state.put("xforce",0.0);
    state.put("yforce",0.0);
    state.put("color1",Color.white);
    state.put("color2",Color.white);
  }

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    // transformation on the entire set
		g2d.translate(getWidth()/2,getHeight()/2);

    em.emit(100);
    em.removeSmallParticles();
    em.update(g2d);
	}

}// Canvas
