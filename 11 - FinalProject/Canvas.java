
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
  Color color;
  double[] direction;
  double x=0,y=0;
  double size = 8.0;
  Random ran;
  double speed;

  public Particle(Random _ran, double _speed){
    ran = _ran;
    shape = new Ellipse2D.Double(x,y,size,size);
    color = Color.white;
    // generate vector
    double speed = _speed;
    double xD = ranDouble(-speed,speed);
    double yD = ranDouble(-speed,speed);
    direction = new double[]{xD,yD};

  }

  public void draw(Graphics2D g2d){
    g2d.setColor(color);
    g2d.fill(shape);
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
    for(int i=0; i<n; i++){
      Particle particle = new Particle(ran,speed);
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
    state.put("speed",5.0);
    state.put("xforce",0.0);
    state.put("yforce",0.0);

    em = new Emitter(state);

    Timer timer = new Timer(1000/24, new ActionListener(){
        public void actionPerformed(ActionEvent evt){ repaint(); }
    });
    timer.start();
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
