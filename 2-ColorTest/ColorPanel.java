/**
 * ColorPanel.java
 * by Mishig Davaadorj
 *
 * Draws Three Ellipses representing RGB.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class ColorPanel extends JPanel{
  // three ellipses for R,G,B
  Ellipse2D.Double ellipseRed;
  Ellipse2D.Double ellipseGreen;
  Ellipse2D.Double ellipseBlue;
  // intersecting areas
  Area redGreen;
  Area redBlue;
  Area blueGreen;
  Area redGreenBlue;
  // current color values inferred from sliders
  int red = 0;
  int green = 0;
  int blue = 0;

  // constructor
  public ColorPanel(){
    //  set panel options
    setPreferredSize(new Dimension(600,600));
    setBackground(Color.lightGray);

    // initialize shapes
    int diameter = 90;
    ellipseRed = new Ellipse2D.Double(0,0,diameter,diameter);
    ellipseGreen = new Ellipse2D.Double(60,0,diameter,diameter);
    ellipseBlue = new Ellipse2D.Double(30,52,diameter,diameter);

    redGreen = new Area(ellipseRed);
    redGreen.intersect(new Area(ellipseGreen));

    redBlue = new Area(ellipseRed);
    redBlue.intersect(new Area(ellipseBlue));

    blueGreen = new Area(ellipseBlue);
    blueGreen.intersect(new Area(ellipseGreen));

    redGreenBlue = new Area(redGreen);
    redGreenBlue.intersect(redBlue);
  }

  public void paintComponent(Graphics g){

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.

    // overall graphics content transformation
    g2d.translate(getWidth()/7,getHeight()/7);
    g2d.scale(3,3);

    // coloring shapes
    g2d.setPaint(new Color(red,0,0));
    g2d.fill(ellipseRed);

    g2d.setPaint(new Color(0,green,0));
    g2d.fill(ellipseGreen);

    g2d.setPaint(new Color(0,0,blue));
    g2d.fill(ellipseBlue);

    g2d.setPaint(new Color(red,green,0));
    g2d.fill(redGreen);

    g2d.setPaint(new Color(red,0,blue));
    g2d.fill(redBlue);

    g2d.setPaint(new Color(0,green,blue));
    g2d.fill(blueGreen);

    g2d.setPaint(new Color(red,green,blue));
    g2d.fill(redGreenBlue);
  }

  public void updateColorVals(String color, int val){
    // set color values
    switch(color){
      case "Red": red = val; break;
      case "Green": green = val; break;
      default: blue = val;
    }
    repaint(); // to see updates on Canvas
  }
}
