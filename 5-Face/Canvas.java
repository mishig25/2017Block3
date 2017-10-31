
/**
 * Canvas.java
 * Draws Graphic objects
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

class FaceElement{
  public Shape shape;
  public Color fillColor;
  public Color outlineColor;
  public FaceElement(Shape _shape, Color _fillColor, Color _outlineColor){
    shape = _shape;
    fillColor = _fillColor;
    outlineColor = _outlineColor;
  }
  // public applyTransform(){
  //
  // }
}

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
  public void drawFace(){
    Ellipse2D.Double face = new Ellipse2D.Double(50,50,100,100);
    FaceElement faceElement = new FaceElement(face,paleColor,null);
    shapes.add(faceElement);
  }

  public void drawEye(){
    Ellipse2D.Double eye = new Ellipse2D.Double(70,70,20,20);
    Color eyeColor = Color.white;
    FaceElement eyeElement = new FaceElement(eye,eyeColor,null);
    shapes.add(eyeElement);
  }

  public void drawEyePupil(){
    Ellipse2D.Double eyePupil = new Ellipse2D.Double(74,74,7,7);
    Color eyePupilColor = Color.black;
    FaceElement eyePupilElement = new FaceElement(eyePupil,eyePupilColor,null);
    shapes.add(eyePupilElement);
  }

  public void drawEyeBrow(){
    Path2D.Double eyeBrow = new Path2D.Double();
    eyeBrow.moveTo(200,200);
    eyeBrow.lineTo(200,230);
    eyeBrow.quadTo(250,200,300,230);
    eyeBrow.lineTo(300,200);
    eyeBrow.quadTo(250,170,200,200);
    FaceElement eyeBrowElement = new FaceElement(eyeBrow,Color.black,null);
    shapes.add(eyeBrowElement);
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
    FaceElement earElement = new FaceElement(ear,paleColor,null);
    shapes.add(earElement);
  }

  public void drawNose(){
    Path2D.Double nose = new Path2D.Double();
    nose.moveTo(80,150);
    nose.quadTo(60,20,30,150);
    nose.closePath();
    FaceElement noseElement = new FaceElement(nose,paleColor,null);
    shapes.add(noseElement);
    // AffineTransform t = new AffineTransform();
    // t.scale(2,2);
    // // t.rotate(Math.toRadians(50));
    // nose.transform(t);
    // // g2d.setColor(paleColor);
    // // g2d.fill(nose);
  }

  public void drawMouth(){
    Path2D.Double mouth = new Path2D.Double();
    mouth.moveTo(50,50);
    mouth.lineTo(50,80);
    mouth.quadTo(95,100,150,80);
    mouth.lineTo(150,50);
    mouth.quadTo(95,70,50,50);
    FaceElement mouthElement = new FaceElement(mouth,Color.white,Color.black);
    shapes.add(mouthElement);
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
    switch(type){
      case "Face": drawFace(); break;
      case "Eye": drawEye(); break;
      case "Eye Pupil": drawEyePupil(); break;
      case "Eyebrow": drawEyeBrow(); break;
      case "Ear": drawEar(); break;
      case "Nose": drawNose(); break;
      case "Mouth": drawMouth(); break;
    }
  }

  public void transformShape(String type){
    // System.out.println(type);
    if(shapes.size()>0){
      FaceElement lastElement = shapes.remove(shapes.size() - 1);
      AffineTransform t = new AffineTransform();

      // apply transform
      switch(type){
        case "Left": t.translate(-5,0); break;
        case "Right": t.translate(5,0); break;
        case "Up": t.translate(0,-5); break;
        case "Down": t.translate(0,5); break;
        case "Rot Left": drawEar(); break;
        case "Rot Right": drawNose(); break;
        case "Scale Up": drawMouth(); break;
        case "Scale Down": drawMouth(); break;
      }
      Shape shape = lastElement.shape;
      Shape transformedShape = t.createTransformedShape(shape);
      shapes.add(new FaceElement(transformedShape,paleColor,null));
    }
  }

}// Canvas
