/**
 * FaceElement.java
 * FaceElement class for containing shape and color informatiom
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class FaceElement{

  // properties
  public Shape shape;
  public Color fillColor;
  public Color outlineColor;

  // constructor
  public FaceElement(Shape _shape, Color _fillColor, Color _outlineColor){
    shape = _shape;
    fillColor = _fillColor;
    outlineColor = _outlineColor;
  }// end constructor

  // apply transform based on argument String
  public void applyTransform(String type){
    AffineTransform t = new AffineTransform(); // init AffineTransform
    switch(type){
      // choose specific transform
      case "Left": t.translate(-5,0); break;
      case "Right": t.translate(5,0); break;
      case "Up": t.translate(0,-5); break;
      case "Down": t.translate(0,5); break;
      case "Rot Left": rotate(t,-10); break;
      case "Rot Right": rotate(t,10); break;
      case "Scale Up": scale(t,1.25,1.25); break;
      case "Scale Down": scale(t,.75,.75); break;
      case "leftEar": rotate(t,-180); break;
    }
    // update shpae property with the newly created shape from AffineTransform
    shape = t.createTransformedShape(shape);
  }// end applyTransform

  // scale the shape
  private void scale(AffineTransform t, Double valX, Double valY){
    // get bounding box center point
    float[] centerPoint = getCenterPoint();
    float x = centerPoint[0], y = centerPoint[1];
    // apply transformations (scale anchroing bounding box center point)
    t.translate(x, y);
    t.scale(valX,valY);
    t.translate(-x, -y);
  }// end scale

  // rotate the shape
  private void rotate(AffineTransform t, int degree){
    // get bounding box center point
    float[] centerPoint = getCenterPoint();
    float x = centerPoint[0], y = centerPoint[1];
    // apply transformations (rotate anchroing bounding box center point)
    t.translate(x, y);
    t.rotate(Math.toRadians(degree));
    t.translate(-x, -y);
  }// end rotate

  // get center points of the bounding box
  private float[] getCenterPoint(){
    Rectangle2D bbox = shape.getBounds2D();
    float x = (float)bbox.getX();
    float y = (float)bbox.getY();
    float w = (float)bbox.getWidth();
    float h = (float)bbox.getHeight();
    x += w/2;
    y += h/2;
    return new float[]{x,y};
  }// end getCenterPoint

}// end FaceElement
