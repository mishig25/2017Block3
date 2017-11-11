
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
  int[] direction;
  int x=0,y=0;
  double size = 8.0;
  Random ran;

  public Particle(Random _ran){
    ran = _ran;
    shape = new Ellipse2D.Double(x,y,size,size);
    color = Color.blue;
    // generate vector
    int xD = ran.nextInt(11) - 5;
    int yD = ran.nextInt(11) - 5;
    direction = new int[]{xD,yD};

  }
  public void draw(Graphics2D g2d){
    g2d.setColor(color);
    g2d.fill(shape);
  }
  public void updatePosition(){
    int r1 = ran.nextInt(5) - 2;
    int r2 = ran.nextInt(5) - 2;
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
      part.updatePosition();
      part.draw(g2d);
    }
  }
}

public class SimpleCanvas extends JPanel{

  Emitter em;

  public SimpleCanvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(500,500));
		setBackground(Color.white);

    em = new Emitter();
    Timer timer = new Timer(1000/24, new ActionListener(){

        public void actionPerformed(ActionEvent evt){
            repaint();
        }
    });
    timer.start();
  }

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    // transformation on the entire set
		g2d.translate(getWidth()/2,getHeight()/2);

    em.emit(50);
    em.update(g2d);

	}

}// SimpleCanvas
