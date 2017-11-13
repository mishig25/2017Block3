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
    state.put("emfr",50);
    state.put("emtype","Point");
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

}// Canvas
