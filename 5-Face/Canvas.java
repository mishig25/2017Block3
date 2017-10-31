
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
  HashMap state; // current state
  ArrayList<FaceElement> shapes = new ArrayList<>();

  Color paleColor = new Color(250,220,188);

  // constructor
  public Canvas (){
		//The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(400,400));
		setBackground(Color.white);
  }// end constructor

  public void paintComponent(Graphics g){

		super.paintComponent(g);  //without this no background color set.
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
    g2d.translate(getWidth()/3,getHeight()/3);

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
    int x = 0, y = 0, w=100, h=100;
    Ellipse2D.Double face = new Ellipse2D.Double(x,y,w,h);
    return new FaceElement(face,paleColor,Color.black);
  }

  private FaceElement drawEye(){
    int x = 20, y = 20, w=20, h=20;
    Ellipse2D.Double eye = new Ellipse2D.Double(x,y,w,h);
    Color eyeColor = Color.white;
    return new FaceElement(eye,eyeColor,null);
  }

  private FaceElement drawEyePupil(){
    int x = 24, y = 24, w=7, h=7;
    Ellipse2D.Double eyePupil = new Ellipse2D.Double(x,y,w,h);
    Color eyePupilColor = Color.black;
    return new FaceElement(eyePupil,eyePupilColor,null);
  }

  private FaceElement drawEyeBrow(){
    int x = 18, y = 12;
    int val1=7,val2=13,val3=25;
    Path2D.Double eyeBrow = new Path2D.Double();
    eyeBrow.moveTo(x,y);
    eyeBrow.lineTo(x,y+val1);
    eyeBrow.quadTo(x+val2,y,x+val3,y+val1);
    eyeBrow.lineTo(x+val3,y);
    eyeBrow.quadTo(x+val2,y-val1,x,y);
    return new FaceElement(eyeBrow,Color.black,null);
  }

  private FaceElement drawEar(String side){
    Path2D.Double ear = new Path2D.Double();
    ear.moveTo(50,60);
    ear.lineTo(50,90);
    ear.quadTo(100,75,50,60);
    ear.moveTo(55,65);
    FaceElement earElement = new FaceElement(ear,paleColor,Color.black);
    if(side == "left"){
      earElement.applyTransform("leftEar");
    }
    return earElement;
  }

  private FaceElement drawNose(){
    Path2D.Double nose = new Path2D.Double();
    nose.moveTo(80,150);
    nose.quadTo(60,20,30,150);
    nose.closePath();
    return new FaceElement(nose,paleColor,Color.black);
  }

  private FaceElement drawMouth(){
    Path2D.Double mouth = new Path2D.Double();
    mouth.moveTo(50,50);
    mouth.lineTo(50,80);
    mouth.quadTo(95,100,150,80);
    mouth.lineTo(150,50);
    mouth.quadTo(95,70,50,50);
    mouth.moveTo(50,65);
    mouth.quadTo(95,85,150,65);
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
    repaint();
  }

  public void createShape(String type){
    FaceElement newElement = new FaceElement(null,null,null);
    switch(type){
      case "Face": newElement = drawFace(); break;
      case "Eye": newElement = drawEye(); break;
      case "Eye Pupil": newElement = drawEyePupil(); break;
      case "Eyebrow": newElement = drawEyeBrow(); break;
      case "Right Ear": newElement = drawEar("right"); break;
      case "Left Ear": newElement = drawEar("left"); break;
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
