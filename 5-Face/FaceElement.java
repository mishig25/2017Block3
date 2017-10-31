/**
 * FaceElement.java
 * Draws Graphic objects
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class FaceElement{
  public Shape shape;
  public Color fillColor;
  public Color outlineColor;
  public FaceElement(Shape _shape, Color _fillColor, Color _outlineColor){
    shape = _shape;
    fillColor = _fillColor;
    outlineColor = _outlineColor;
  }
  public void applyTransform(String type){
    AffineTransform t = new AffineTransform();
    switch(type){
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
    shape = t.createTransformedShape(shape);
  }
  private void scale(AffineTransform t, Double valX, Double valY){
    float[] centerPoint = getCenterPoint();
    float x = centerPoint[0], y = centerPoint[1];

    t.translate(x, y);
    t.scale(valX,valY);
    t.translate(-x, -y);
  }
  private void rotate(AffineTransform t, int degree){
    float[] centerPoint = getCenterPoint();
    float x = centerPoint[0], y = centerPoint[1];

    t.translate(x, y);
    t.rotate(Math.toRadians(degree));
    t.translate(-x, -y);
  }
  private float[] getCenterPoint(){
    Rectangle2D bbox = shape.getBounds2D();
    float x = (float)bbox.getX();
    float y = (float)bbox.getY();
    float w = (float)bbox.getWidth();
    float h = (float)bbox.getHeight();
    x += w/2;
    y += h/2;
    return new float[]{x,y};
  }
}
