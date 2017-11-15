/**
 * Canvas.java
 *
 * Draws Particles.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Canvas extends JPanel implements MouseListener,MouseMotionListener{
  // instance vars
  Emitter em; // emitter
  HashMap state; // for keeping track of emitter configs
  boolean mouseClick = false;

  public Canvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.black);
    addMouseListener(this);
    addMouseMotionListener(this);

    // init instance vars
    state = new HashMap();
    initState();
    em = new Emitter(state);

    // timer for calling paintComponent 24fps
    Timer timer = new Timer(1000/24, new ActionListener(){
        public void actionPerformed(ActionEvent evt){ repaint(); }
    });
    timer.start();
  }// end constructor

  // initial settings for the emitter
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

    em.emit(); // emit every frame
    em.removeSmallParticles(); // remove no longer needed particles
    em.update(g2d); // update particle positions, colors. etc.
	}

  //Methods to satisfy MouseListener interface
  public void mousePressed(MouseEvent e){ mouseClick = true; }
  public void mouseReleased(MouseEvent e){ mouseClick = false;}

  public void mouseDragged(MouseEvent e){
    if(mouseClick){
      int y = e.getY()-getHeight()/2;
      int x = e.getX()-getWidth()/2;
      state.put("x",x);
      state.put("y",y);
    }
  }

  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mouseMoved(MouseEvent e){}
  public void mouseClicked(MouseEvent e){}

}// Canvas
