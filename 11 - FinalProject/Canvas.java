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

public class Canvas extends JPanel implements MouseListener{

  Emitter em;
  HashMap state;

  public Canvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.black);
    addMouseListener(this);

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
    state.put("emfr",50);
    state.put("emtype","Point");
    state.put("x",0);
    state.put("y",0);
  }

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    // transformation on the entire set
		g2d.translate(getWidth()/2,getHeight()/2);

    em.emit();
    em.removeSmallParticles();
    em.update(g2d);
	}

  public void mouseClicked(MouseEvent e){
    int y = e.getY()-getHeight()/2;
    int x = e.getX()-getWidth()/2;
    state.put("x",x);
    state.put("y",y);
  }

   //Empty methods to satisfy MouseListener interface
  public void mouseEntered(MouseEvent e){}

  public void mouseExited(MouseEvent e){}

  public void mousePressed(MouseEvent e){}

  public void mouseReleased(MouseEvent e){}

}// Canvas
