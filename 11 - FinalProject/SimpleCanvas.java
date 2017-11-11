
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

class Particle{

  Ellipse2D.Double shape;
  Color color;
  double[] direction;
  double x=0,y=0;
  double size = 8.0;
  Random ran;

  public Particle(Random _ran){
    ran = _ran;
    shape = new Ellipse2D.Double(x,y,size,size);
    color = Color.white;
    // generate vector
    double xD = ran.nextInt(11) - 5;
    double yD = ran.nextInt(11) - 5;
    yD = 20;
    // scaling factor
    double s = 100;
    // xD /= s; yD /= s;
    direction = new double[]{xD,yD};

  }
  public void draw(Graphics2D g2d){
    g2d.setColor(color);
    g2d.fill(shape);
  }
  public void update(){
    double r1 = ran.nextInt(5) - 2;
    double r2 = ran.nextInt(5) - 2;
    direction[0] += r1;
    direction[1] += r2;
    x += direction[0];
    y += direction[1];
    size -= .2;
    shape.setFrame(x,y,size,size);
  }
}

class Emitter{

  ArrayList<Particle> particles;
  Random ran;

  public Emitter(){
    ran = new Random();
    particles = new ArrayList();
  }
  public void emit(int n){
    for(int i=0; i<n; i++){
      Particle particle = new Particle(ran);
      particles.add(particle);
    }
  }
  public void update(Graphics2D g2d){
    for(Particle part: particles){
      part.update();
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

public class SimpleCanvas extends JPanel{

  Emitter em;

  public SimpleCanvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.black);

    em = new Emitter();
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

}// SimpleCanvas
