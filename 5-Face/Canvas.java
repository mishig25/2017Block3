
/**
 * Canvas.java
 * Draws Graphic objects
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class Canvas extends JPanel{

  // create vars
  Graphics2D g2d; // current graphics2d context
  HashMap state; // current state
  Color paleColor = new Color(250,220,188);

  public Canvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(400,400));
		setBackground(Color.lightGray);
  }// end constructor

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		g2d = (Graphics2D)g; //cast so we can use JAVA2D.

    drawEyeBrow();


	}// end paintComponent

  // this method is being called from Panel.java class
  // to render changes based on GUI controls
  public void update(HashMap _state){
    state = _state;
    // repaint();
  }// end update

  // drawing shpaes
  public void drawFace(){
    Ellipse2D.Double face = new Ellipse2D.Double(50,50,100,100);
    g2d.setColor(paleColor);
    g2d.fill(face);
  }

  public void drawEye(){
    Ellipse2D.Double eye = new Ellipse2D.Double(70,70,20,20);
    Color eyeColor = Color.white;
    g2d.setColor(eyeColor);
    g2d.fill(eye);
  }

  public void drawEyePupil(){
    Ellipse2D.Double eyePupil = new Ellipse2D.Double(74,74,7,7);
    Color eyePupilColor = Color.black;
    g2d.setColor(eyePupilColor);
    g2d.fill(eyePupil);
  }

  public void drawEyeBrow(){
    Path2D.Double eyeBrow = new Path2D.Double();
    eyeBrow.moveTo(200,200);
    eyeBrow.lineTo(200,230);
    eyeBrow.quadTo(250,200,300,230);
    eyeBrow.lineTo(300,200);
    eyeBrow.quadTo(250,170,200,200);
    g2d.fill(eyeBrow);
  }

  public void drawEar(){
    Path2D.Double ear = new Path2D.Double();
    ear.moveTo(50,50);
    ear.quadTo(70,30,100,50);
    ear.quadTo(120,80,85,130);
    ear.quadTo(60,150,60,130);
    ear.closePath();
    g2d.setColor(paleColor);
    g2d.fill(ear);
  }

  public void drawNose(){
    Path2D.Double nose = new Path2D.Double();
    nose.moveTo(80,150);
    nose.quadTo(60,20,30,150);
    nose.closePath();
    g2d.setColor(paleColor);
    g2d.fill(nose);
  }

  public void drawMouth(){
    Path2D.Double mouth = new Path2D.Double();
    mouth.moveTo(50,50);
    mouth.lineTo(50,80);
    mouth.quadTo(95,100,150,80);
    mouth.lineTo(150,50);
    mouth.quadTo(95,70,50,50);
    g2d.setColor(Color.white);
    g2d.fill(mouth);
    g2d.setColor(Color.black);
    g2d.draw(mouth);
  }

}// Canvas
