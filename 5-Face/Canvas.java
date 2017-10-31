
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
  ArrayList<FaceElement> shapes = new ArrayList<>(); // keep track of all active shapes

  Color paleColor = new Color(250,220,188); // defining color

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

    // draw all the shapes in "shapes" ArrayList
    for(FaceElement el: shapes){
      g2d.setColor(el.fillColor); // set color
      g2d.fill(el.shape); // fill shape
      // draw border if specified
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

  // get commands from Panel.java GUI
  public void btnClicked(String type){
    if(type == "Create"){
      // create new shape
      String chosenElement = (String) state.get("chosenElement");
      createShape(chosenElement);
    }else{
      // transform last shape
      transformShape(type);
    }
    repaint();
  }// end btnClicked

  // create shape based on argument
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
  }// end createShape

  // transform last shape based on argument
  public void transformShape(String type){
    if(shapes.size()>0){
      FaceElement lastElement = shapes.get(shapes.size() - 1);
      lastElement.applyTransform(type);
    }
  }// end transformShape

  // FUNCTIONS FOR ADDING SPECIFIC FACE ELEMENTS

  // add Face element to "shapes" arraylist
  private FaceElement drawFace(){
    int x = 0, y = 0, w=100, h=100;
    Ellipse2D.Double face = new Ellipse2D.Double(x,y,w,h);
    return new FaceElement(face,paleColor,Color.black);
  }// end drawFace

  // add Eye element to "shapes" arraylist
  private FaceElement drawEye(){
    int x = 20, y = 20, w=20, h=20;
    Ellipse2D.Double eye = new Ellipse2D.Double(x,y,w,h);
    Color eyeColor = Color.white;
    return new FaceElement(eye,eyeColor,null);
  }// end drawEye

  // add Eye Pupil element to "shapes" arraylist
  private FaceElement drawEyePupil(){
    int x = 24, y = 24, w=7, h=7;
    Ellipse2D.Double eyePupil = new Ellipse2D.Double(x,y,w,h);
    Color eyePupilColor = Color.black;
    return new FaceElement(eyePupil,eyePupilColor,null);
  }// end drawEyePupil

  // add Eyebrow element to "shapes" arraylist
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
  }// end drawEyeBrow

  // add Ear element to "shapes" arraylist
  private FaceElement drawEar(String side){
    int x=95,y=35;
    int val1=30,val2=50,val3=15;
    Path2D.Double ear = new Path2D.Double();
    ear.moveTo(x,y);
    ear.lineTo(x,y+val1);
    ear.quadTo(x+val2,y+val3,x,y);
    FaceElement earElement = new FaceElement(ear,paleColor,Color.black);
    if(side == "left") earElement.applyTransform("leftEar");
    return earElement;
  }// end drawEar

  // add Nose element to "shapes" arraylist
  private FaceElement drawNose(){
    int x=60,y=60;
    int val1=10,val2=75,val3=25;
    Path2D.Double nose = new Path2D.Double();
    nose.moveTo(x,y);
    nose.quadTo(x-val1,y-val2,x-val3,y);
    nose.closePath();
    return new FaceElement(nose,paleColor,Color.black);
  }// end drawNose

  // add Mouth element to "shapes" arraylist
  private FaceElement drawMouth(){
    int x=5,y=50;
    Path2D.Double mouth = new Path2D.Double();
    mouth.moveTo(x,y);
    mouth.lineTo(x,y+30);
    mouth.quadTo(x+45,y+50,x+100,y+30);
    mouth.lineTo(x+100,y);
    mouth.quadTo(x+45,y+20,x,y);
    mouth.moveTo(x,65);
    mouth.quadTo(x+45,y+35,x+100,y+15);
    FaceElement mouthElement = new FaceElement(mouth,Color.white,Color.black);
    for(int i=0;i<3;i++) mouthElement.applyTransform("Scale Down");
    return mouthElement;
  }// end drawMouth

}// Canvas
