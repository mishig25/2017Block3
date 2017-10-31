
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
  ArrayList<FaceElement> shapes = new ArrayList<>();

  public Canvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(400,400));
		setBackground(Color.lightGray);
  }// end constructor

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		g2d = (Graphics2D)g; //cast so we can use JAVA2D.

    for(FaceElement el: shapes){
      g2d.setColor(el.fillColor);
      g2d.fill(el.shape);
      if(el.outlineColor != null){
        g2d.setColor(el.outlineColor);
        g2d.draw(el.shape);
      }
    }

	}// end paintComponent

  // this method is being called from Panel.java class
  // to render changes based on GUI controls
  public void update(HashMap _state){
    state = _state;
  }// end update

  // drawing shpaes
  private FaceElement drawFace(){
    Ellipse2D.Double face = new Ellipse2D.Double(50,50,100,100);
    return new FaceElement(face,paleColor,null);
  }

  private FaceElement drawEye(){
    Ellipse2D.Double eye = new Ellipse2D.Double(70,70,20,20);
    Color eyeColor = Color.white;
    return new FaceElement(eye,eyeColor,null);
  }

  private FaceElement drawEyePupil(){
    Ellipse2D.Double eyePupil = new Ellipse2D.Double(74,74,7,7);
    Color eyePupilColor = Color.black;
    return new FaceElement(eyePupil,eyePupilColor,null);
  }

  private FaceElement drawEyeBrow(){
    Path2D.Double eyeBrow = new Path2D.Double();
    eyeBrow.moveTo(200,200);
    eyeBrow.lineTo(200,230);
    eyeBrow.quadTo(250,200,300,230);
    eyeBrow.lineTo(300,200);
    eyeBrow.quadTo(250,170,200,200);
    return new FaceElement(eyeBrow,Color.black,null);
  }

  private FaceElement drawEar(){
    Path2D.Double ear = new Path2D.Double();
    ear.moveTo(50,50);
    ear.quadTo(70,30,100,50);
    ear.quadTo(120,80,85,130);
    ear.quadTo(60,150,60,130);
    ear.closePath();
    g2d.setColor(paleColor);
    g2d.fill(ear);
    return new FaceElement(ear,paleColor,null);
  }

  private FaceElement drawNose(){
    Path2D.Double nose = new Path2D.Double();
    nose.moveTo(80,150);
    nose.quadTo(60,20,30,150);
    nose.closePath();
    return new FaceElement(nose,paleColor,null);
  }

  private FaceElement drawMouth(){
    Path2D.Double mouth = new Path2D.Double();
    mouth.moveTo(50,50);
    mouth.lineTo(50,80);
    mouth.quadTo(95,100,150,80);
    mouth.lineTo(150,50);
    mouth.quadTo(95,70,50,50);
    return new FaceElement(mouth,Color.white,Color.black);
  }

  public void btnClicked(String type){
    if(type == "Create"){
      String chosenElement = (String) state.get("chosenElement");
      createShape(chosenElement);
    }else{
      // transformation
      transformShape(type);
    }
    Rectangle myRect = new Rectangle(0,0,100,100);
    g2d.setColor(Color.red);
    g2d.fill(myRect);
    repaint();
  }

  public void createShape(String type){
    FaceElement newElement = new FaceElement(null,null,null);
    switch(type){
      case "Face": newElement = drawFace(); break;
      case "Eye": newElement = drawEye(); break;
      case "Eye Pupil": newElement = drawEyePupil(); break;
      case "Eyebrow": newElement = drawEyeBrow(); break;
      case "Ear": newElement = drawEar(); break;
      case "Nose": newElement = drawNose(); break;
      case "Mouth": newElement = drawMouth(); break;
    }
    shapes.add(newElement);
  }

  public void transformShape(String type){
    if(shapes.size()>0){
      FaceElement lastElement = shapes.get(shapes.size() - 1);
      lastElement.applyTransform(type);
    }
  }

}// Canvas
